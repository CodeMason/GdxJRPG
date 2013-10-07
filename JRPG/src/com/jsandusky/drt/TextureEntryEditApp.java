package com.jsandusky.drt;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.input.*;
import com.badlogic.gdx.graphics.glutils.*;

/*
This gets fired up inside of a popup dialog for the main editor
*/
public class TextureEntryEditApp implements ApplicationListener
{
	TextureEntry editing;
	TextureEntry clone;
	GridPoint2 originPoint;
	GestureDetector detector;
	InputMultiplexer input;
	SpriteBatch sBatch;
	OrthographicCamera cam;
	ShapeRenderer sr;
	public TextureEntryEditApp(TextureEntry ent) {
		editing = ent;
		
		clone.Bounds = editing.Bounds;
		clone.ToolPath = editing.ToolPath;
		clone.TextureName = editing.TextureName;
	}
	
	public void create()
	{
		sBatch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		sr = new ShapeRenderer();
		
		//??load the texture region for it
	}

	public void resize(int p1, int p2)
	{
	}

	public void render()
	{
		cam.apply(Gdx.graphics.getGL10());
		Gdx.graphics.getGLCommon().glClearColor(0.6f,0.6f,0.6f,1);
		Gdx.graphics.getGLCommon().glClear(GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_COLOR_BUFFER_BIT);
		
		sBatch.begin();
		if (editing.Region != null)
			sBatch.draw(editing.Region,0,0,editing.Region.getRegionHeight(),editing.Region.getRegionWidth());
		sBatch.end();
		
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeRenderer.ShapeType.Line);
		sr.setColor(Color.PINK);
			//draw a cross that tells us where the origin is
			sr.line(editing.Bounds.Origin.x-2,editing.Bounds.Origin.y, 0, editing.Bounds.Origin.x+2, editing.Bounds.Origin.y,0);
			sr.line(editing.Bounds.Origin.x,editing.Bounds.Origin.y-2, 0, editing.Bounds.Origin.x, editing.Bounds.Origin.y+2,0);
		sr.end();
	}

	public void pause()
	{
	}

	public void resume()
	{
	}

	public void dispose()
	{
	}
	
	public class Control implements GestureDetector.GestureListener {
		TextureEntryEditApp app;
		public Control(TextureEntryEditApp app) {
			this.app = app;
		}
		public boolean touchDown(float p1, float p2, int p3, int p4){
			return false;
		}
		public boolean tap(float x, float y, int p3, int p4){
			Vector2 pt = new Vector2(x,Gdx.graphics.getHeight()-y);
			pt.sub(app.cam.position.x,app.cam.position.y);
			if (pt.x > 0 && pt.y > 0 && pt.x < app.editing.Region.getRegionWidth() && pt.y < app.editing.Region.getRegionHeight())
				app.editing.Bounds.Origin.set(pt);
			return true;
		}
		public boolean longPress(float p1, float p2){
			return false;
		}
		public boolean fling(float p1, float p2, int p3){
			return false;
		}
		public boolean pan(float x, float y, float deltaX, float deltaY){
			app.cam.position.x -= deltaX;
			app.cam.position.x -= deltaY;
			return true;
		}
		public boolean zoom(float p1, float p2){
			return false;
		}
		public boolean pinch(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4){
			return false;
		}
	};
}
