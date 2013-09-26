package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.UUID;

/* It's basically like database foreign key */
public class Reference<T extends Base> implements Serializable
{
	Class clazz;
	public UUID id;
	transient T instance;
	
	public Reference(T object) {//initial birth
		clazz = object.getClass();
		id = object.getID();
		instance = object;
	}
	public Reference() {
		
	}
	
	public T get(Database data) {
		if (instance == null) {
			instance = (T)data.get(id);
		}
		return instance;
	}
	
	public T get() {
		if (instance != null)
			return instance;
		return null;
	}
}
