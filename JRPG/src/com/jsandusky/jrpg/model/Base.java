package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.UUID;

public class Base implements Serializable
{
	public String Name;
	UUID id_ = UUID.randomUUID();
	
	public void setName(String name) {
		Name = name;
	}
	
	public String getName() {
		return Name;
	}
	
	public UUID getID() {
		return id_;
	}
	
	@Override
	public String toString() {
		if (Name != null && !Name.isEmpty())
			return Name;
		return id_.toString();
	}
}
