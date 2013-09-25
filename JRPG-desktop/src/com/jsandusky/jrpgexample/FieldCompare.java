package com.jsandusky.jrpgexample;

import java.lang.reflect.Field;
import java.util.Comparator;

import com.jsandusky.util.Spite;

public class FieldCompare implements Comparator<Field> {
	public int compare(Field lhs, Field rhs) {
		Spite la = lhs.getAnnotation(Spite.class);
		Spite ra = rhs.getAnnotation(Spite.class);
		if (la != null && ra != null) {
			int cValue = la.group().compareToIgnoreCase(ra.group());
			if (cValue != 0)
				return cValue;
		} else if (la != null) {
			return -1;
		} else if (ra != null) {
			return 1;
		}
		return lhs.getName().compareToIgnoreCase(rhs.getName());
	}
}