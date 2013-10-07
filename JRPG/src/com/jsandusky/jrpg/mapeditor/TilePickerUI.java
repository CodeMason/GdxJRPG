package com.jsandusky.jrpg.mapeditor;
import com.jsandusky.gdx.common.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.*;
import java.util.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class TilePickerUI extends Menu
{
	Stage stage;
	String sourceAtlas;
	Skin skin;
	Resources res;
	Window window;
	ArrayList<TextureHandle> choices;
	HashMap<TextureHandle,Label> lblMap = new HashMap<TextureHandle,Label>();
	
	Editor editor;
	public TilePickerUI(Editor edit, Resources res, String targetAtlas) {
		sourceAtlas = targetAtlas;
		editor = edit;
		this.res = res;
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		stage = new Stage();
		((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(0,stage);
		stage.setViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),true);

		window = new Window("Palette",skin);
		
		Table table = new Table();
		//table.setFillParent(true);
		choices = res.getImageCache().getAll(targetAtlas);
		
		for (TextureHandle h : choices) {
			if (h.name.contains("sys_") || h.name.equals("defeat") || h.name.equals("victory") || h.name.contains("gen_") || h.name.equals("bulkheadhatch")) //do not display system tiles
				continue;			
			table.row();
			final TextureHandle local = h;
			final Image img = new Image();
			final Label lbl = new Label(h.name,skin);
			lblMap.put(local,lbl);
			
			img.setDrawable(new TextureRegionDrawable(h.region));
			img.setWidth(75);
			img.setHeight(75);
			img.getDrawable().setMinHeight(75);
			img.getDrawable().setMinWidth(75);
			img.setTouchable(Touchable.enabled);
			img.addListener(new com.badlogic.gdx.scenes.scene2d.InputListener() {
				boolean hadTouchDown = false;
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					if (event.getTarget() == img)
						hadTouchDown = true;
					return true;
				}
					
				public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
					if (hadTouchDown) {
						TilePickerUI.this.editor.setTexture(local);
					} else {
					}
					hadTouchDown = false;
				}
			});
			
			table.add(img);
			lbl.setTouchable(Touchable.enabled);
			lbl.addListener(new InputListener() {
				boolean hadTouchDown = false;
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					hadTouchDown = true;
					return true;
				}

				public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
					if (hadTouchDown) {
						TilePickerUI.this.editor.setTexture(local);
					} else {
					}
					hadTouchDown = false;
				}
			});
			table.add(lbl).expandX();
			
		}

		ScrollPane scroller = new ScrollPane(table,skin);
		scroller.setFillParent(true);
		
		//scroller.setScrollingDisabled(false,false);
		window.add(scroller).expandX().expandY().fill();
		window.setSize(Gdx.graphics.getWidth()/4,(Gdx.graphics.getHeight()/4)*3);
		window.setPosition(Gdx.graphics.getWidth()-window.getWidth(),0);
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
		for (TextureHandle h : lblMap.keySet()) {
			if (h == editor.currentTexture) {
				lblMap.get(h).setColor(Color.GREEN);
			} else {
				lblMap.get(h).setColor(Color.WHITE);
			}
		}
		window.invalidate();
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
