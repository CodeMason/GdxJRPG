package com.jsandusky.gdx.common;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class DragZone {
	public Rectangle Region;
	public boolean Vertical;
	Vector2 current;
	
	
	public void update(Vector2 pos) {
		if (current == null) {
			current = pos;
			return;
		}
		
	}
}
