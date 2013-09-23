package com.jsandusky.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

public class MenuNinePatch extends NinePatch {
	private static MenuNinePatch instance;
	private MenuNinePatch() {
		//super(new Texture(Gdx.files.internal("data/menuskin.png")));
		super(new Texture(Gdx.files.internal("data/tex/menuskin.png")), 12,12,12,12);
	}
	public static MenuNinePatch getInstance() {
		if (instance == null)
			instance = new MenuNinePatch();
		return instance;
	}
}
