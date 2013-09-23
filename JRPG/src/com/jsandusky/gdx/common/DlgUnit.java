package com.jsandusky.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class DlgUnit {
	public static Rectangle getDlgRect(int dim) {
		Rectangle r = new Rectangle();
		r.x = Gdx.graphics.getWidth() / dim;
		r.width = r.x * (dim - 2);
		r.y = Gdx.graphics.getHeight() / dim;
		r.height = r.y * (dim - 2);
		return r;
	}
	
	public static Rectangle getDlgRect(int dimX, int dimY) {
		Rectangle r = new Rectangle();
		r.x = Gdx.graphics.getWidth() / dimX;
		r.width = r.x * (dimX - 2);
		r.y = Gdx.graphics.getHeight() / dimY;
		r.height = r.y * (dimY - 2);
		return r;
	}
}
