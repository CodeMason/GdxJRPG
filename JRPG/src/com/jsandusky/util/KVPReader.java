package com.jsandusky.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class KVPReader {
	public static HashMap<String,String> readKVP(InputStream inputStream) {
		HashMap<String,String> ret = new HashMap<String,String>();
		BufferedReader bufRdr;
		try {
			bufRdr = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			int lineCt = 1;
			while ((line = bufRdr.readLine()) != null) {
				String[] terms = line.split("=");
				if (terms.length == 2) {
					ret.put(terms[0].trim().toLowerCase(), terms[1].trim());
				} else
					MsgLog.inst().error("Unexpected KVP at line " + lineCt + " of file");
				++lineCt;
			}
		} catch (Exception e) 
		{
			int i = 0;
			++i;
		}
		return ret;
	}
	
	public static float getFloat(HashMap<String,String> kvp, String key) {
		return Float.parseFloat(kvp.get(key));
	}
	
	public static int getInt(HashMap<String,String> kvp, String key) {
		return Integer.parseInt(kvp.get(key));
	}
}
