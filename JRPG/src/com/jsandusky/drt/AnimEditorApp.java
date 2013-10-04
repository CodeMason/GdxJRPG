package com.jsandusky.drt;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.jsandusky.gdx.common.PixmapTextureAtlas;

public class AnimEditorApp implements ApplicationListener {
	ShapeRenderer sr;
	OrthographicCamera cam;
	Animation active;
	AnimationPlayer player;
	
	public enum Mode {
		None,
		Move,
		Rotate
	}
	Mode mode_ = Mode.None;
	
	public void setMode(Mode m) {
		mode_ = m;
	}
	
	HashMap<String,TextureAtlas.AtlasRegion> atlases = new HashMap<String,TextureAtlas.AtlasRegion>();
	ArrayList<PixmapTextureAtlas> rawAt = new ArrayList<PixmapTextureAtlas>();
	
	@Override
	public void create() {
		cam = new OrthographicCamera();
		sr = new ShapeRenderer();
		
		ArrayList<FileHandle> images = new ArrayList<FileHandle>();
		FileHandle fh = Gdx.files.internal("./bin/data/tex");
		if (fh.exists()) {
			for (FileHandle sub : fh.list()) {
				if (sub.extension().equals("pack")) {
					images.add(sub);
				}
			}
		}
		
		
		for (FileHandle i : images) {
			PixmapTextureAtlas at = new PixmapTextureAtlas(Gdx.files.internal(i.pathWithoutExtension() + ".png"),i);
			rawAt.add(at);
		}
		
		for (PixmapTextureAtlas at : rawAt) {
			TextureAtlas atlas = at.getAtlas();
			for (TextureAtlas.AtlasRegion region : atlas.getRegions()) {
				/*Pixmap pm = at.createPixmap(region.name);
				BufferedImage image = new BufferedImage(pm.getWidth(),pm.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
				for (int x = 0; x < pm.getWidth(); ++x) {
					for (int y = 0; y < pm.getHeight(); ++y) {
						int pix = pm.getPixel(x, y);
						image.setRGB(x, y, pix);
					}
				}*/
				
				atlases.put(region.name, region);
			}
		}
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void render() {
		Gdx.graphics.getGL10().glClearColor(0.7f, 0.7f, 0.7f, 1);
		Gdx.graphics.getGL10().glClear(GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_COLOR_BUFFER_BIT);
		
		cam.apply(Gdx.graphics.getGL10());
		sr.setProjectionMatrix(cam.combined);
		if (mode_ == Mode.Move)
			sr.setColor(Color.BLUE);
		else if (mode_ == Mode.Rotate) {
			sr.setColor(Color.BLACK);
		} else {
			sr.setColor(Color.RED);
		}
		sr.begin(ShapeType.Line);
			sr.line(new Vector2(0,-250), new Vector2(0,250));
			sr.line(new Vector2(-250,0), new Vector2(250,0));
		sr.end();
		
		//draw the current animation
	}
	
	public ArrayList<String> getTextures() {
		return null;
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	public HashMap<String,TextureAtlas.AtlasRegion> getAtlasRegions() {
		return atlases;
	}
	
	public ArrayList<PixmapTextureAtlas> getAtlases() {
		return rawAt;
	}
}
