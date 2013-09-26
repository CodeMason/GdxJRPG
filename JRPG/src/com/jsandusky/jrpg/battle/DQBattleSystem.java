package com.jsandusky.jrpg.battle;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.decals.*;

//Don't see characters, front facing like FFMQ
//Sequential turns
public class DQBattleSystem extends BattleSystem
{	
	ArrayList<BattleAgent> finished = new ArrayList<BattleAgent>(6);
	ArrayList<BattleAction> actions = new ArrayList<BattleAction>();
	boolean intro = true;
	boolean planning;
	boolean playOut;
	boolean conclusion;
	BattleAction running;
	
//for lerping entry effects like the black screen and menus
	float introTime = 0f;
	static float introTotalTime = 4f;
	
	public DQBattleSystem(BattleState state) {
		super(state);
	}

	public void run() {
		if (intro) {
			//??
		}
		
		if (planning) {
			for (BattleAgent agt : State.agents) {
				if (!finished.contains(agt)) {
					BattleAction act = agt.getAction();
					if (act != null) {
						actions.add(act);
					}
					finished.add(agt);
				}
			}
			if (finished.size() == actions.size()) {
				playOut = true;
				planning = false;
				finished.clear();
				
				//TODO sort the action list
			}
		}
		
		if (playOut) {
			if (running == null && actions.size() > 0) {
				running = actions.get(0);
			} else if (running != null) {
				if (!running.playing()) {
					running = null;
				}
			} else {
				playOut = false;
				planning = true;			
			}
		}
		
		if (conclusion) {
			
		}
	}
	
	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
		//draw bg
		
		//draw characters
		
		if (running != null) {
			running.draw(cam,realCam,dBatch,sBatch,bmf);
		}
	}
	
	public void pushPlayerAction(BattleAction action) {
		actions.add(action);
		finished.add(action.getSource());
	}
}
