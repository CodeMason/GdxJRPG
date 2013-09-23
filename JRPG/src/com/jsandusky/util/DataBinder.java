package com.jsandusky.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class DataBinder {
	public static Object Eval(Object src, String eval) {
		Object cur = src;
		String[] parts = eval.split(".");
		for (int i = 0; i < parts.length; ++i)
			cur = Eval_(cur, parts[i]);
		return cur;
	}
	
	static Object Eval_(Object cur, String eval) {
		int sub = -1;
		String adjName = eval;
		if (eval.contains("[")) {
			int brackIdx = eval.indexOf("[");
			int endIdx = eval.indexOf("]");
			if (brackIdx > 0 && endIdx > 0) {
				adjName = eval.substring(0, brackIdx-1);
				sub = Integer.parseInt(eval.substring(brackIdx+1, endIdx - brackIdx));
			}
		}
		Field[] fields = cur.getClass().getDeclaredFields();
		for (Field fld : fields) {
			if (fld.getName().equals(adjName)) {
				try {
					cur = fld.get(cur);
					if (cur != null && sub > -1 && cur.getClass().isArray())
						cur = Array.get(cur, sub);
					return cur;
				} catch (Exception ex) {
					
				}
			}
		}
		return null;
	}
}
