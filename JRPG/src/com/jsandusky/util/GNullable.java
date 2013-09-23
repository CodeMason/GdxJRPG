package com.jsandusky.util;

public class GNullable<T> {
	T value_;
	
	public GNullable() {
		
	}
	
	public GNullable(T val) {
		set(val);
	}
	
	public boolean isNull() {
		return value_ == null;
	}
	
	public T value() {
		return value_;
	}
	
	public void set(T arg) {
		value_ = arg;
	}
	
	public void reset() {
		value_ = null;
	}
}
