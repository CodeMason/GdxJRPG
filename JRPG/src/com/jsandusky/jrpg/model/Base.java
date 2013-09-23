package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.UUID;

public class Base implements Serializable
{
	String name_;
	UUID id_ = UUID.randomUUID();
	
	public void setName(String name) {
		name_ = name;
	}
	
	public String getName() {
		return name_;
	}
	
	public UUID getID() {
		return id_;
	}
}
