package com.jsandusky.util;

import java.util.ArrayList;
import java.util.Stack;

public class GFixedCache<T extends Object> implements ICache<T> {
	ArrayList<T> pool_;
	Stack<Integer> free_;
	Class class_;
	
	public GFixedCache(int size, Class c) {
		pool_ = new ArrayList<T>(size);
		free_ = new Stack<Integer>();
		class_ = c;
		try {
			for (int i = 0; i < size; ++i) {
				T o = (T) class_.newInstance();
				pool_.add(o);
				free_.add(i);
			}
		} catch (Exception e) {
			
		}
	}
	
	@Override
	public T alloc() {
		if (!free_.isEmpty()) {
			int sub = free_.pop();
			return pool_.get(sub);
		} else {
			try {
				return (T)class_.newInstance();
			} catch (Exception e) {
			} 
		}
		return null;
	}
	
	@Override
	public void dealloc(T o) {
		if (pool_.contains(o))
			free_.push(pool_.indexOf(o));
	}
}
