package com.jsandusky.util;

import java.util.ArrayList;

import com.jsandusky.gdx.util.Random;

public class MilNameBuilder {
	public static ArrayList<Integer> used_;
	
	//build a forward military divison type name
	//e.g. 101st, 202nd, 303rd, 404th, etc
	//in usage this would come out as something like 22nd Lyran Sabres
	public static String getName() {
		int i = 0;
		if (used_ == null)
			used_ = new ArrayList<Integer>();
		do {
			i = new Random(java.lang.System.currentTimeMillis()).randI(1,500);
		} while (used_.contains(i));
		String nm = "" + i;
		if (nm.charAt(nm.length()-1) == '1') {
			nm += "st";
		} else if (nm.charAt(nm.length()-1) == '2') {
			nm += "nd";
		} else if (nm.charAt(nm.length()-1) == '3') {
			nm += "rd";
		} else {
			nm += "th";
		}
		return nm;
	}
}
