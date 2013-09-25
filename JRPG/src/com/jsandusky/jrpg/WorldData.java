package com.jsandusky.jrpg;
import com.jsandusky.jrpg.model.*;
import java.io.Serializable;
import java.util.HashMap;

public class WorldData implements Serializable
{
	//use these as little as necessary
	/* ie monster dies putInt("dudekilled",0)
		later in another map during load check the int and show/hide something
	*/
	HashMap<String,String> misc = new HashMap<String,String>();
	HashMap<String,Integer> ints = new HashMap<String,Integer>();
	
	public Party ActiveParty;
	
	public void putString(String name, String var) {
		misc.put(name,var);
	}
	
	public void putInt(String name, int i) {
		ints.put(name,i);
	}
	
	public String getString(String name, String def) {
		if (misc.containsKey(name))
			return misc.get(name);
		return def;
	}
	
	public int getInt(String name, int def) {
		if (ints.containsKey(name))
			return ints.get(name);
		return def;
	}
}
