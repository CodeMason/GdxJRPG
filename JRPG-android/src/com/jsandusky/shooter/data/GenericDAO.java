package com.jsandusky.shooter.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public abstract class GenericDAO<T> extends SQLiteOpenHelper {
	protected String TAG = "GenericDAO";
	public SQLiteDatabase db;
	protected String dName;
	protected String tName;
	protected String tCreate;
	protected String preQuery;
	protected String postQuery = "";
	protected String KEY_ID = "_id";

	public GenericDAO(Context ctx, String dbName, String tableCreate, String tableName, int ver) {
		super(ctx, dbName, null, ver);
		tCreate = tableCreate;
		dName = dbName;
		tName = tableName;
		try {
			db = getWritableDatabase();
			Cursor c = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
			if (c != null && c.getCount() > 0) {
				c.close();
			} else {
				c.close();
				try {
					db.execSQL(tCreate);
				} catch (SQLException se) {
				}
			}
		} catch (SQLiteException se) {
		}
	}
	
	@Override
	protected void finalize() {
		db.close();
	}

	protected abstract T mapRow(final Cursor c);

	protected abstract ContentValues mapValues(T obj);

	protected abstract int idOf(T obj);

	protected abstract void setID(T obj, long id);

	public void close() {
		db.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(tCreate);
		} catch (SQLException se) {
		} catch (IllegalArgumentException ie) {
		}
	}

	public ArrayList<T> getList(String query) {
		ArrayList<T> ret = new ArrayList<T>();
		Cursor c = db.rawQuery(preQuery + query + postQuery, null);
		if (c != null && c.getCount() > 0) {
			boolean first = true;
			//try {
				do {
					if (first) {
						c.moveToFirst();
						first = false;
					} else
						c.move(1);
					ret.add(mapRow(c));
				} while (!c.isLast());
			//} catch (Exception e) {

			//}
			c.close();
		}
		return ret;
	}

	public T getSingle(String query) {
		Cursor c = db.rawQuery(preQuery + query + postQuery, null);
		if (c != null && c.getCount() > 0) {
			c.moveToFirst();
			T ret = mapRow(c);
			c.close();
			return ret;
		}
		return null;
	}

	public long saveOrUpdate(T thing) {
		if (idOf(thing) <= 0) {
			long ret = db.insert(tName, null, mapValues(thing));
			setID(thing, ret);
			//update with the id to be the row id received from SQLite
			//KEY_ID is the internal sqlite row id
			db.beginTransaction();
			db.update(tName, mapValues(thing), "ROWID =" + idOf(thing), null);
			db.setTransactionSuccessful();
			db.endTransaction();
			return ret;
		} else {
			db.beginTransaction();
			db.update(tName, mapValues(thing), KEY_ID + "=" + idOf(thing), null);
			db.setTransactionSuccessful();
			db.endTransaction();
		}
		return idOf(thing);
	}

	public int deleteTable() {
		db.beginTransaction();
		int ret = db.delete(tName, "1", null);
		db.setTransactionSuccessful();
		db.endTransaction();
		return ret;
	}

	public int delete(T thing) {
		db.beginTransaction();
		int ret = db.delete(tName, KEY_ID + "=" + idOf(thing), null);
		db.setTransactionSuccessful();
		db.endTransaction();
		return ret;
	}

	public Cursor raw(String sql) {
		return db.rawQuery(sql, null);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}