package com.jsandusky.jrpg.mapeditor;
import com.jsandusky.gdx.common.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.jsandusky.jrpg.map.*;
import com.badlogic.gdx.*;
import com.jsandusky.jrpg.model.*;
import java.util.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class MapInfoUI extends Menu
{
	Stage stage;
	Window window;
	Skin skin;
	
	GameMap map;
	
	public MapInfoUI(GameMap mapIn) {
		map = mapIn;
		stage = new Stage();
		((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(0,stage);
		stage.setViewport(800,480,true);
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		window = new Window("Map Info",skin);
		window.setPosition(30,Gdx.graphics.getHeight()/2);
		window.setSize(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/2);
		
		Table table = new Table(skin);
		
		table.row();
		Label lbl = new Label("Name:",skin);
		table.add(lbl);

		table.row();table.add("");
		
		ScrollPane scroller = new ScrollPane(table,skin);
		window.add(scroller);
		window.pack();
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

	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
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
