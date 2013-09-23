package com.jsandusky.util;

public class FString {
	public static String Format(String str, Object ... args) {
		String ret = str;
		for (int i = 0; i < args.length; ++i) {
			ret = ret.replaceAll("%" + (i+1), args[i].toString());
		} 
		return ret;
	}
}
