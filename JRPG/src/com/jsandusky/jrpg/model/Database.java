package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Database implements Serializable
{
	public HashMap<UUID,Base> Objects;
	public transient HashMap<String,Base> ByName;
	
	//builds the ByName hashmap
	public void update() {
		if (ByName == null) {
			ByName = new HashMap<String,Base>();
		} else {
			ByName.clear();
		}
		for (Base b : Objects.values())
			ByName.put(b.getName(),b);
	}
	
	public ArrayList get(Class c) {
		ArrayList ret = new ArrayList();
		for (Object obj : Objects.values()) {
			if (obj.getClass().equals(c))
				ret.add(obj);
		}
		return ret;
	}
	
	public Object get(UUID id) {
		if (Objects.containsKey(id)) {
			return Objects.get(id);
		}
		return null;
	}
}
