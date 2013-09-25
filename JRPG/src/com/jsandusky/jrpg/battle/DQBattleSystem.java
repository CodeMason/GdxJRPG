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
	
	public DQBattleSystem(BattleState state) {
		super(state);
	}

	public void run() {
		if (intro) {
			
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
			}
		}
		
		if (playOut) {
			for (int i = 0; i < actions.size(); ++i) {
				if (!actions.get(i).playing()) {
					actions.remove(i);
					--i;
					break;
				}
				
				
				break;
			}
		}
	}
	
	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
	}
}
