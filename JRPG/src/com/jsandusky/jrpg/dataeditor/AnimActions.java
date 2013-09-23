package com.jsandusky.jrpg.dataeditor;
import com.jsandusky.gdx.common.Menu;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.*;

public class AnimActions extends Menu
{
	Stage stage;
	Skin skin;
	AnimEditor editor;
	public AnimActions(AnimEditor editor) {
		this.editor = editor;
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("data/sys/uiskin.json"));
		
		Window window = new Window("Tools",skin);
		
		TextButton addSprite = new TextButton("Add Graphics",skin);
		TextButton copy = new TextButton("Copy Frame",skin);
		TextButton paste = new TextButton("Paste Frame",skin);
		TextButton addSnd = new TextButton("Add Sound",skin);
		TextButton delSel = new TextButton("Delete",skin);
		TextButton noSel = new TextButton("Deselect",skin);
		
		addSprite.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent p1, Actor p2)
			{
			}
		});
		
		copy.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent p1, Actor p2)
			{
			}
		});
		
		paste.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent p1, Actor p2)
			{
			}
		});
		
		addSnd.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent p1, Actor p2)
			{
			}
		});
		
		delSel.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent p1, Actor p2)
			{
			}
		});
		
		noSel.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent p1, Actor p2)
			{
			}
		});
		
		window.row();
		window.add(addSprite);
		window.row();
		window.add(copy);
		window.row();
		window.add(paste);
		window.row();
		window.add(addSnd);
		window.row();
		window.add(delSel);
		window.row();
		window.add(noSel);
		
		stage.addActor(window);
	}
	public boolean routeTouchDown(float x, float y, int ptr)	{
		return false;
	}
	public boolean routeTouchUp(float x, float y, int ptr)	{
		return false;
	}
	public boolean routeTouchDrag(float x, float y, int ptr)	{
		return false;
	}

	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
		stage.act();
		stage.draw();
	}

	public void dispose()
	{
		stage.dispose();
		skin.dispose();
	}
}
