package com.jsandusky.gdx.common;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontCache {
	HashMap<String,BitmapFont> fonts_ = new HashMap<String,BitmapFont>();
	public void addFont(String name, BitmapFont font) {
		fonts_.put(name, font);
	}
	
	public BitmapFont getFont(String name, String fntRes, String fntImage) {
		if (fonts_.containsKey(name))
			return fonts_.get(name);
		BitmapFont newFont = new BitmapFont(Gdx.files.internal(fntRes), Gdx.files.internal(fntImage),false);
		fonts_.put(name,newFont);
		return newFont;
	}
}
