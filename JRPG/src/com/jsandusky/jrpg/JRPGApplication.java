package com.jsandusky.jrpg;
import com.badlogic.gdx.*;

/* Runtime application */
public class JRPGApplication implements ApplicationListener
{
	public InputMultiplexer input;
	static JRPGApplication inst_;
	
	public static JRPGApplication inst() {
		return inst_;
	}
	
	public void create()
	{
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
