package com.jsandusky.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

public class ActiveButtonNinePatch extends NinePatch {
private static ActiveButtonNinePatch instance;
	
	private ActiveButtonNinePatch() {
		super(new Texture(Gdx.files.internal("data/activebutton.png")), 8,8,8,8);
	}
	public static ActiveButtonNinePatch getInstance() {
		if (instance == null)
			instance = new ActiveButtonNinePatch();
		return instance;
	}
}
