package com.jsandusky.jrpg.mapeditor;
import com.jsandusky.gdx.common.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.jsandusky.gdx.util.*;

public class ToolsPalette extends Menu
{
	Skin skin;
	Stage stage;
	Window window;
	
	Button paint;
	Button rotate;
	Button erase;
	Button settings;
	Button pieces;
	Button special;
	
	Color defaultColor;
	
	Editor editor;
	
	public ToolsPalette(Editor editor) {
		this.editor = editor;
		stage = new Stage();
		((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(0,stage);
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		window = new Window("Tile Tools",skin);
		
		window.row().align(Align.left).fill(true,false);
		paint = new TextButton("Paint",skin);
		defaultColor = paint.getColor().cpy();
		paint.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent p1, Actor p2)
			{
				if (ToolsPalette.this.editor.getMode() == Editor.Mode.Paint)
					ToolsPalette.this.editor.setMode(Editor.Mode.None);
				else
					ToolsPalette.this.editor.setMode(Editor.Mode.Paint);
			}			
		});
		window.add(paint);
		
		
		window.row().align(Align.left).fill(true,false);
		rotate = new TextButton("Rotate",skin);
		rotate.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent p1, Actor p2)
			{
				if (ToolsPalette.this.editor.getMode() == Editor.Mode.Rotate)
					ToolsPalette.this.editor.setMode(Editor.Mode.None);
				else
					ToolsPalette.this.editor.setMode(Editor.Mode.Rotate);
			}			
		});
		window.add(rotate);
		
		window.row().align(Align.left).fill(true,false);
		erase = new TextButton("Erase",skin);
		erase.addListener(new ChangeListener() {
				public void changed(ChangeListener.ChangeEvent p1, Actor p2)
				{
					if (ToolsPalette.this.editor.getMode() == Editor.Mode.Erase)
						ToolsPalette.this.editor.setMode(Editor.Mode.None);
					else
						ToolsPalette.this.editor.setMode(Editor.Mode.Erase);
				}			
			});
		window.add(erase);
			
		window.row().align(Align.left).fill(true,false);
		settings = new TextButton("Settings",skin);
		settings.addListener(new ChangeListener() {
				public void changed(ChangeListener.ChangeEvent p1, Actor p2)
				{
					if (ToolsPalette.this.editor.getMode() == Editor.Mode.Settings)
						ToolsPalette.this.editor.setMode(Editor.Mode.None);
					else
						ToolsPalette.this.editor.setMode(Editor.Mode.Settings);
				}			
			});
		window.add(settings);
		
		window.row().align(Align.left).fill(true,false);
		pieces = new TextButton("Pieces",skin);
		pieces.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent event, Actor actor) {
				if (ToolsPalette.this.editor.getMode() == Editor.Mode.Pieces) {
					ToolsPalette.this.editor.setMode(Editor.Mode.None);
					IDriver.getActive().removeMenu(PiecesPalatte.class);
				} else {
					ToolsPalette.this.editor.setMode(Editor.Mode.Pieces);
					IDriver.getActive().pushMenu(new PiecesPalatte(ToolsPalette.this.editor));
				}
			}
		});
		window.add(pieces);
		
		window.row().align(Align.left).fillX();
		special = new TextButton("Special",skin);
		special.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (ToolsPalette.this.editor.getMode() == Editor.Mode.Special) {
					ToolsPalette.this.editor.setMode(Editor.Mode.None);
					IDriver.getActive().removeMenu(AccessoryPickerUI.class);
				} else {
					ToolsPalette.this.editor.setMode(Editor.Mode.Special);
					IDriver.getActive().pushMenu(new AccessoryPickerUI(ToolsPalette.this.editor));
				}
			}
		});
		window.add(special);
		
		window.pack();
		window.setPosition(0,0);
		window.setMovable(true);
		stage.setViewport(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,true);
		stage.addActor(window);
	}
	
	public boolean routeTouchDown(float x, float y, int ptr)
	{
		return false;
	}

	public boolean routeTouchUp(float x, float y, int ptr)
	{
		return false;
	}

	public boolean routeTouchDrag(float x, float y, int ptr)
	{
		return false;
	}

	void resetColors() {
		paint.setColor(defaultColor);
		rotate.setColor(defaultColor);
		erase.setColor(defaultColor);
		settings.setColor(defaultColor);
		pieces.setColor(defaultColor);
		special.setColor(defaultColor);
	}
	
	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
		resetColors();
		if (editor.getMode() == Editor.Mode.Paint) {
			paint.setColor(Color.GREEN);
		} else if (editor.getMode() == Editor.Mode.Rotate) {
			rotate.setColor(Color.GREEN);
		} else if (editor.getMode() == Editor.Mode.Erase) {
			erase.setColor(Color.GREEN);
		} else if (editor.getMode() == Editor.Mode.Settings) {
			settings.setColor(Color.GREEN);
		} else if (editor.getMode() == Editor.Mode.Pieces) {
			pieces.setColor(Color.GREEN);
		} else if (editor.getMode() == Editor.Mode.Special) {
			special.setColor(Color.GREEN);
		}
		
		stage.act();
		stage.draw();
	}

	public void dispose()
	{
		((InputMultiplexer)Gdx.input.getInputProcessor()).removeProcessor(stage);	
		stage.dispose();
		skin.dispose();
	}
}
