package com.jsandusky.drt;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.io.Serializable;

public class TextureBounds implements Serializable {
	public Rectangle Location;
	public Vector2 Origin;
	
	public TextureBounds(Rectangle loc, Vector2 o) {
		Location = loc;
		Origin = o;
	}
}
