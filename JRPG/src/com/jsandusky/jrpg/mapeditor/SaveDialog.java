package com.jsandusky.jrpg.mapeditor;
import com.jsandusky.gdx.common.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.jsandusky.jrpg.map.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Json;
import com.jsandusky.gdx.util.*;

public class SaveDialog extends Menu
{
	Stage stage;
	GameMap map;
	Dialog window;
	TextField fileName;
	Skin skin;
	
	public SaveDialog(GameMap mapIn) {
		stage = new Stage();
		stage.setViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),true);
		((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(0,stage);
		map = mapIn;
		
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		window = new Dialog("Save",skin) {
			@Override
			protected void result(Object val) {
				if ((Boolean)val) {
					String txt = SaveDialog.this.fileName.getText();
					if (txt.length() == 0)
						return;
					map.fileName = txt;
					Json json = new Json();
					FileHandle fh = Gdx.files.local(txt);
					String data = json.toJson(SaveDialog.this.map);
					fh.writeString(data,false);
				}
				IDriver.getActive().removeMenu(SaveDialog.this);
			}
		};
		
		Label lbl = new Label("Save changes to this map?",skin);
		window.getContentTable().add("Save as:");
		window.getContentTable().row();
		fileName = new TextField(map.fileName,skin);
		window.getContentTable().add(fileName);
		window.button("Save",true);
		window.button("Cancel",false);
		window.setPosition(Gdx.graphics.getWidth()/4-window.getWidth(), Gdx.graphics.getHeight()/4-window.getHeight());
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
