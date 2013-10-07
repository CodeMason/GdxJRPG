package com.jsandusky.jrpg.mapeditor;
import com.jsandusky.gdx.common.*;
import com.jsandusky.jrpg.model.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.util.*;

public class PiecesPalatte extends Menu
{
	Stage stage;
	Skin skin;
	Window window;
	Editor editor;
	
	ArrayList<Label> labels = new ArrayList<Label>();
	HashMap<Image,String> images = new HashMap<Image,String>();
	
	void setActiveLabel(String str) {
		for (Label lbl : labels) {
			if (lbl.getText().toString().equals(str)) {
				lbl.setColor(Color.GREEN);
			} else {
				lbl.setColor(Color.WHITE);
			}
		}
	}
	
	public PiecesPalatte(Editor editor) {
		this.editor = editor;
		
		stage = new Stage();
		((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(0,stage);
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		window = new Window("Piece Palette",skin);
		
		Table contents = new Table(skin);
		Image img = null;
		Label lbl = null;
		
//ERASER
		
		ScrollPane sp = new ScrollPane(contents,skin);
		sp.setFillParent(true);
		window.add(sp).expandX().expandY();
		window.setWidth(Gdx.graphics.getWidth()/6);
		window.setX(Gdx.graphics.getWidth()-window.getWidth());
		window.setHeight(400);
		stage.addActor(window);
	}

	public boolean routeTouchDown(float x, float y, int ptr)
	{
		// TODO: Implement this method
		return false;
	}

	public boolean routeTouchUp(float x, float y, int ptr)
	{
		// TODO: Implement this method
		return false;
	}

	public boolean routeTouchDrag(float x, float y, int ptr)
	{
		// TODO: Implement this method
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
