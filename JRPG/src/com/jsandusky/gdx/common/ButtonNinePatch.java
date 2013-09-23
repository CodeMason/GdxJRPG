package com.jsandusky.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

public class ButtonNinePatch extends NinePatch {
	private static ButtonNinePatch instance;
	
	private ButtonNinePatch() {
		super(new Texture(Gdx.files.internal("data/button.png")), 8,8,8,8);
	}
	public static ButtonNinePatch getInstance() {
		if (instance == null)
			instance = new ButtonNinePatch();
		return instance;
	}
}
