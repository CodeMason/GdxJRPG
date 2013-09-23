package com.jsandusky.util;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IRenderScheduled {
	public void run(Camera cam, SpriteBatch sb, BitmapFont bmf);
}
