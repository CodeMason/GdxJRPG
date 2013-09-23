package com.jsandusky.util;

import java.lang.reflect.Field;
import java.util.HashMap;

public class SizeOf {
	static HashMap<Class, Integer> sizes_;
	
	static void initSizes() {
		if (sizes_ == null) {
			sizes_ = new HashMap<Class,Integer>();
			
			sizes_.put(short.class, 2);
			sizes_.put(char.class, 2);
			sizes_.put(byte.class, 1);
			sizes_.put(boolean.class, 1);
			sizes_.put(int.class, 4);
			sizes_.put(float.class, 4);
			sizes_.put(long.class, 8);
			sizes_.put(double.class, 8);
		}
	}	

	public static int fieldSize(Field f) {
		if (f.getType().isEnum())
			return 4; //reference size to that enum value
		if (sizes_.containsKey(f.getType()))
			return sizes_.get(f.getType());
		return 0;			
	}

	public static int measureClass(Class c, boolean theoretical) { //ignore reference
		initSizes();
		int sz = 8; //8 bytes for Object shell
		Field[] fields = c.getDeclaredFields();
		for (Field f : fields) {
			int fSize = fieldSize(f);
			if (fSize > 0) {
				sz += fSize;
				continue;
			} else if (f.getType() == String.class) {
				//??? sz += 2 * String.length() + 4 + 8; //4 for object ref, 8 for string existence
			} else {
				if (theoretical) {
					sz += 4;
					sz += measureClass(f.getType(), theoretical);
				} else
					sz += 4; //object reference
			}	
		}
		return sz;
	}
}
