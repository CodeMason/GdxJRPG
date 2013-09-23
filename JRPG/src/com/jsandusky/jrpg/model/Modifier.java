package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.lang.reflect.*;

public class Modifier implements Serializable
{
	public String Modify; //reflection
	public int Flat;
	public float Percentile;
	
	public void apply(Hero h) {
		try {
			Field fld = h.getClass().getField(Modify);
			Object obj = fld.get(h);
			if (obj instanceof Integer) {
				if (Flat != 0) {
					Integer i = (Integer)obj;
					i += Flat;
					fld.set(h,i);
				} else if (Percentile != 0) {
					Integer i = (Integer)obj;
					i += (int)(i * Percentile);
					fld.set(h,i);
				}
			} else if (obj instanceof Float) {
				if (Flat != 0) {
					Float i = (Float)obj;
					i += Flat;
					fld.set(h,i);
				} else if (Percentile != 0) {
					Float i = (Float)obj;
					i += (i * Percentile);
					fld.set(h,i);
				}
			}
		} catch (Exception ex) {
			
		}
	}
	
	public void remove(Hero h) {
		try {
			Field fld = h.getClass().getField(Modify);
			Object obj = fld.get(h);
			if (obj instanceof Integer) {
				if (Flat != 0) {
					Integer i = (Integer)obj;
					i -= Flat;
					fld.set(h,i);
				} else if (Percentile != 0) {
					Integer i = (Integer)obj;
					i -= (int)(i * (1.0-Percentile));
					fld.set(h,i);
				}
			} else if (obj instanceof Float) {
				if (Flat != 0) {
					Float i = (Float)obj;
					i -= Flat;
					fld.set(h,i);
				} else if (Percentile != 0) {
					Float i = (Float)obj;
					i -= (i * (1.0f-Percentile));
					fld.set(h,i);
				}
			}
		} catch (Exception ex) {

		}
	}
}
