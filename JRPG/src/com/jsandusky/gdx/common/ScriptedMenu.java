package com.jsandusky.gdx.common;

import java.io.StringReader;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.jsandusky.fscript.FSReflectionExtension;
import com.jsandusky.fscript.FScript;

public class ScriptedMenu extends Menu {
	ArrayList<Object> elements = new ArrayList<Object>();
	FScript script_;
	
	public ScriptedMenu(String script, int width, int height) {
		script_ = new FScript();
		script_.registerExtension(new FSReflectionExtension());
		try {
			script_.load(new StringReader(script));
			ArrayList params = new ArrayList();
			params.add(this);
			params.add(elements);
			params.add(width);
			params.add(height);
			script_.callScriptFunction("init", params);
		} catch (Exception ex) {
			
		}
	}
	
	@Override
	public void draw(Camera cam, Camera real, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf) {
		for (Object obj : elements) {
			if (obj instanceof Sprite) {
				
			}
		}
	}

	@Override
	public boolean routeTouchDown(float x, float y, int ptr) {
		return false;
	}

	@Override
	public boolean routeTouchUp(float x, float y, int ptr) {
		
		return false;
	}

	@Override
	public boolean routeTouchDrag(float x, float y, int ptr) {
		return false;
	}

	@Override
	public void dispose() {
	}
}
