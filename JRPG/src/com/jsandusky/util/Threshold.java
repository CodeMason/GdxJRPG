package com.jsandusky.util;

import com.badlogic.gdx.math.Vector3;

public class Threshold {
	float dist;
	Vector3 start;
	boolean passed_ = false;
	
	public Threshold(Vector3 pt) {
		reset(pt);
	}
	public void reset(Vector3 pt) {
		start = pt;
		passed_ = false;
	}
	public boolean passed() {
		return passed_;
	}
	
	public void update(Vector3 pt) {
		if (start != null && start.dst(pt) > dist) {
			passed_ = true;
		} else if (start == null) {
			start = pt;
			passed_ = false;
		}
	}
}
