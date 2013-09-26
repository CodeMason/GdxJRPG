package com.jsandusky.drt;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TextureBounds {
	public Rectangle Location;
	public Vector2 Origin;
	
	public TextureBounds(Rectangle loc, Vector2 o) {
		Location = loc;
		Origin = o;
	}
}
