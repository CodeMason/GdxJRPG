package com.jsandusky.jrpg.battle;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.decals.*;

//sees the backs of the characters
//sequential turns, speed just determines order of play out
/* 
clone DQ system then add rendering of the hero sprites
*/
public class FFMQBattleSystem extends BattleSystem
{	
	public FFMQBattleSystem(BattleState state) {
		super(state);
	}
	
	public void run() {
	}
	
	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
		// TODO: Implement this method
	}
	
	public void pushPlayerAction(BattleAction action) {
		
	}
}
