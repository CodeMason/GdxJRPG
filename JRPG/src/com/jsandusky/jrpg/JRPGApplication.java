package com.jsandusky.jrpg;
import com.badlogic.gdx.*;
import com.jsandusky.gdx.common.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/* Runtime application */
public class JRPGApplication implements ApplicationListener
{
	public InputMultiplexer input;
	JRPGState state;
	ResourceManager rm_;
	Resources resources_;
	static JRPGApplication inst_;
	EngineSetup settings;
	OrthographicCamera cam;
	SpriteBatch sBatch;
	FontSet fonts_;
	boolean paused_;
	
	EngineSetup getSettings() {
		return settings;
	}
	
	public Resources getResources() {
		return resources_;
	}
	
	public void clearResources() {
	}
	
	public JRPGApplication(EngineSetup setup) {
		settings = setup;
	}
	
	public static JRPGApplication inst() {
		return inst_;
	}
	
	public void create() {
		resources_ = new Resources();
		inst_ = this;
		
		cam = new OrthographicCamera();
		sBatch = new SpriteBatch();
	}
	public void resize(int x, int y) {
		//adjust camera viewport for screen resize
		cam.viewportWidth = x;
		cam.viewportHeight = y;
	}
	public void render() {
		sBatch.setProjectionMatrix(cam.combined);
		if (state != null) {
			if (!paused_) {
				//update
			}
			sBatch.begin();
				state.draw(cam,cam,null,sBatch,null);
			sBatch.end();
			if (paused_) {
				
			}
		} else {
			
		}
	}
	public void pause() {
		paused_ = true;
		//push pause menu
	}
	public void resume() {
		paused_ = true;
	}
	public void dispose() {
		if (rm_ != null)
			rm_.dispose();
		sBatch.dispose();
	}
}
