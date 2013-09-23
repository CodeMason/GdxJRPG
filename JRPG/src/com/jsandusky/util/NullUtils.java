package com.jsandusky.util;

import com.jsandusky.gdx.util.DataObject;

public class NullUtils {
	public static boolean isNull(final DataObject o) {
		return o == null || o._dbId == 0;
	}
	
	public static <T> T coalesce(final T value, final T ifNullValue) {
		return value == null ? ifNullValue : value;
	}
	
	public static boolean isNull(final String s) {
		if (s == null || s.length() == 0 || s.contentEquals("")) return true;
		else return false;
	}
	
	public static <T> T WhenNonZero(final int v, T newObj) {
		if (v > 0) {
			((DataObject)newObj)._dbId = v;
			return newObj;
		} return null;
	}
}
