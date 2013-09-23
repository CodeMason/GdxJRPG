package com.jsandusky.util;

import java.lang.reflect.Field;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CSVObjectReader {
	public static void processLine(Object o, String csvLine) {
		String[] fields = csvLine.split(",");
		for (int i = 0; i < fields.length; ++i) {
			String sVal = fields[i].trim();
			Field[] flds = o.getClass().getDeclaredFields();
			for (Field fld : flds) {
				try {
					CSVSource src = fld.getAnnotation(CSVSource.class);
					if (src != null && src.Column() == i) {
						if (fld.getType() == int.class) {
							fld.setInt(o, Integer.parseInt(sVal));
						} else if (fld.getType() == float.class) {
							fld.setFloat(o, Float.parseFloat(sVal));
						} else if (fld.getType() == String.class) {
							fld.set(o, sVal);
						} else if (fld.getType().isEnum()) {
							fld.set(o, Enum.valueOf((Class<? extends Enum>)fld.getType(), sVal));
						} else if (fld.getType() == Color.class) {
							String[] args = sVal.split("-");
							if (args.length == 3)
								fld.set(o, new Color(Float.parseFloat(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2]), 1));
							else if (args.length == 4)
								fld.set(o, new Color(Float.parseFloat(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2]), Float.parseFloat(args[3])));
						} else if (fld.getType() == Vector2.class){ 
							String[] args = sVal.split("-");
							if (args.length == 2)
								fld.set(o, new Vector2(Float.parseFloat(args[0]), Float.parseFloat(args[1])));
						} else if (fld.getType() == Vector3.class) { 
							String[] args = sVal.split("-");
							if (args.length == 3)
								fld.set(o, new Vector3(Float.parseFloat(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2])));
						}
					}
				} catch (Exception e) {}
			}
		}
	}
}
