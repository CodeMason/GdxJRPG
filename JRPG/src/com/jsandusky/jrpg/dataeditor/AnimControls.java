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

public class AnimControls extends Menu
{
	Stage stage;
	Skin skin;
	Label frameLbl;
	AnimEditor editor;
	
	public AnimControls(AnimEditor editor) {
		this.editor = editor;
		skin = new Skin(Gdx.files.internal("data/sys/uiskin.json"));
		
		Window window = new Window("Controls",skin);
		frameLbl = new Label("",skin);
		
		TextButton back = new TextButton("<<",skin);
		back.addListener(new ChangeListener() {
				public void changed(ChangeListener.ChangeEvent p1, Actor p2)
				{
					AnimControls.this.editor.prevFrame();
				}
			});
		TextButton fore = new TextButton(">>",skin);
		fore.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent p1, Actor p2)
			{
				AnimControls.this.editor.nextFrame();
			}
		});
		
		window.add(back);
		window.add(frameLbl);
		window.add(fore);
		
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
		frameLbl.setText(editor.onFrame + " of " + editor.anim.Frames.size());
		stage.act();
		stage.draw();
	}

	public void dispose()
	{
		stage.dispose();
		skin.dispose();
	}
	
}
