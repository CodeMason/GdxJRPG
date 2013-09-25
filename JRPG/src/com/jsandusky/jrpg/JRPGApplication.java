package com.jsandusky.jrpg;
import com.badlogic.gdx.*;
import com.jsandusky.gdx.common.*;

/* Runtime application */
public class JRPGApplication implements ApplicationListener
{
	public InputMultiplexer input;
	Resources resources_;
	static JRPGApplication inst_;
	EngineSetup settings;
	
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
	
	public void create()
	{
		resources_ = new Resources();
		inst_ = this;
	}

	public void resize(int p1, int p2)
	{
	}

	public void render()
	{
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
}
