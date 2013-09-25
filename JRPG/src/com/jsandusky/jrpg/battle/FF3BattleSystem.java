package com.jsandusky.jrpg.battle;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.decals.*;

//Characters from sideview
//timed action events based on character speed
//it's more like micro turns of 3 seconds
//the troops speed determines parts per 3 second with a max of 12 seconds
/*
35 speed = +35, ready at 100
	35 speed is ready in 3*3 seconds = 8.5 seconds
per second contribution is 35/3 
40 speed is ready in 9 seconds
50 speed is ready in 6 seconds
25 speed is ready in 12 seconds
10 speed, low start is set at 12 seconds
*/
public class FF3BattleSystem extends BattleSystem
{	
	public FF3BattleSystem(BattleState state) {
		super(state);
	}
	
	public void run() {
	}
	
	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
		// TODO: Implement this method
	}
}
