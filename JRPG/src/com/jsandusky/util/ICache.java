package com.jsandusky.util;

public interface ICache<T extends Object> {
	public T alloc();
	public void dealloc(T o);
}
