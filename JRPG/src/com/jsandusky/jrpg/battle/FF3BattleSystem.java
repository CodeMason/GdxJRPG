package com.jsandusky.jrpg.battle;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.*;
import java.util.HashMap;
import java.util.ArrayList;

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
	HashMap<BattleAgent,Integer> timers = new HashMap<BattleAgent,Integer>();
	float nextPushPoint = 1f;
	final float pointTime = 1f;
	ArrayList<BattleAction> actionsQueue = new ArrayList<BattleAction>();
	BattleAction running;
	
	public FF3BattleSystem(BattleState state) {
		super(state);
		for (BattleAgent agt : state.agents) {
			timers.put(agt,50);
		}
	}
	
	public void run() {
		nextPushPoint -= Gdx.graphics.getDeltaTime();
		if (nextPushPoint <= 0) {
			nextPushPoint = pointTime;
			for (BattleAgent agt : timers.keySet()) {
				Integer val = timers.get(agt);
				val += agt.getInitiative()/3;
				val = Math.min(100,val);
				timers.put(agt,val);
			}
		}
		for (BattleAgent agt : State.agents) {
			if (agt instanceof BattleMonster) {
				Integer val = timers.get(agt);
				if (val == 100) {
					BattleAction action = agt.getAction();
					if (action != null) {
						actionsQueue.add(action);
						val = 0;
					} else {
						val -= 20; //it came up with no action, make it wait
					}
					timers.put(agt,val);
				}
			}
		}
		if (running != null) {
			//currently playing action finished?
			if (!running.playing()) {
				actionsQueue.remove(0);
				running = null;
			}
		} else if (actionsQueue.size() > 0) {
			//start the action
			running = actionsQueue.get(0);
			running.startAction();
		}
	}
	
	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
		//draw battleground
		//draw characters unless skill animates character
		
		//an action is running, draw it
		if (running != null)
			running.draw(cam,realCam,dBatch,sBatch,bmf);
	}
	
	public void pushPlayerAction(BattleAction action) {
		actionsQueue.add(action);
	}
}
