package com.jsandusky.jrpg;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.jsandusky.jrpg.map.*;
import com.jsandusky.jrpg.model.Database;
import com.jsandusky.gdx.common.ResourceManager;

/* Main functioning state - works with a game map */
public class WorldState extends JRPGState
{
	GameMap lastMap; //keep the last map on hand - to speed quick switches
	GameMap currentMap;
	WorldData worldData;
	Database db;
	ResourceManager res;
	Sprite loadScreenLogo;
	Sprite loadScreenBg;
	
	public void loadMap() {
		
	}
	
	public void update()
	{
	}

	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
		if (currentMap != null)
			currentMap.draw(cam,realCam,dBatch,sBatch,bmf);
	}

}
