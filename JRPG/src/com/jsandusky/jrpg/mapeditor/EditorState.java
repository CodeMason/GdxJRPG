package com.jsandusky.jrpg.mapeditor;
import com.jsandusky.jrpg.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.input.*;
import com.badlogic.gdx.*;
import com.jsandusky.jrpg.map.*;
import java.io.*;
import com.jsandusky.gdx.util.*;
import com.jsandusky.jrpg.model.*;

//Sets up a simple GameState object to be used by the editor
public class EditorState extends JRPGState implements Serializable
{
	GestureDetector detect;
	EditorInput input;
	
	public void dispose()
	{
		IDriver.getActive().removeMenu(EditorUI.class);
		
		((InputMultiplexer)Gdx.input.getInputProcessor()).removeProcessor(detect);
	}

	Camera cam;
	Editor editor;

	public Editor getEditor() {
		return editor;
	}
	
	public GameMap getMap() {
		return editor.map;
	}
	
	Database source;
	
	public EditorState(Camera cam, GameMap map, Database src) {
		source = src;
		this.cam = cam;
		editor = new Editor(map);
	}
	
	public void init() {
		input = new EditorInput(this,cam);
		detect = new GestureDetector(input);
		((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(detect);
		
		IDriver.getActive().pushMenu(new EditorUI(editor,source));
		IDriver.getActive().pushMenu(new ToolsPalette(editor));
	}
	
	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
		if (editor != null && editor.map != null)
			editor.map.draw(cam,realCam,dBatch,sBatch,bmf);
	}

	public void update()
	{
		//Nothing to do here?
	}

	public Camera getCamera()
	{
		return cam;
	}
}
