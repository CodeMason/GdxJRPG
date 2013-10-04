package com.jsandusky.jrpg;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.*;

/* GestureListener that deals with map related input mostly */
public class InputHandler implements GestureDetector.GestureListener
{
	Camera cam;
	JRPGApplication app;	
	
	public InputHandler(JRPGApplication app, Camera cam) {
		this.cam = cam;
		this.app = app;
	}
	public boolean touchDown(float x, float y, int ptr, int p4){
		return false;
	}
	public boolean tap(float x, float y, int ptr, int p4)
	{
		//route to the app to pass to the state?
		
		return false;
	}
	public boolean longPress(float p1, float p2)
	{
		return false;
	}
	public boolean fling(float p1, float p2, int p3)
	{
		return false;
	}
	
	public boolean pan(float x, float y, float dx, float dy){
		cam.position.x -= dx;
		cam.position.y += dy;
		cam.update();
		return true;
	}
	
	public boolean zoom(float initialDist, float distance){
		final float factor = distance / initialDist;
		final float ratio = cam.viewportHeight / cam.viewportWidth;
		if (initialDist > distance) {
			cam.viewportWidth += factor*4;
			cam.viewportHeight = cam.viewportWidth * ratio;
		} else if (initialDist < distance) {
			cam.viewportWidth -= factor*4;
			cam.viewportHeight = cam.viewportWidth * ratio;
		}

		// clamps field of view to common angles...
		if (cam.viewportWidth < Gdx.graphics.getWidth()/4) 
			cam.viewportWidth = Gdx.graphics.getWidth()/4;
		if (cam.viewportWidth > Gdx.graphics.getWidth()*2) 
			cam.viewportWidth = Gdx.graphics.getWidth()*2;

		if (cam.viewportHeight < Gdx.graphics.getHeight()/4) 
			cam.viewportHeight = Gdx.graphics.getHeight()/4;
		if (cam.viewportHeight > Gdx.graphics.getHeight()*2) 
			cam.viewportHeight = Gdx.graphics.getHeight()*2;

		cam.update();

		return true;
	}
	public boolean pinch(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4)
	{
		return false;
	}
}
