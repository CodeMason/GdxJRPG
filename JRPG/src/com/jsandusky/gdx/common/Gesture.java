package com.jsandusky.gdx.common;

import com.badlogic.gdx.math.Vector3;

public class Gesture {
	Vector3 point1, point2;
	int fingerCt_ = 0;
	float tapRadius_;
	float timeDown_;
	float longPressTime_ = 1000f;
	float lastTap_;
	
	public void update(float td) {
		if (fingerCt_ == 0)
			lastTap_ += td;
	}
	
	public void onDown(Vector3 pt, int ptr) {
		++fingerCt_;
	}
	
	public void onUp(Vector3 pt, int ptr) {
		--fingerCt_;
		if (fingerCt_ == 0) {
			
		}
	}
	
	public void onDrag(Vector3 pt, int ptr) {
	}
}
