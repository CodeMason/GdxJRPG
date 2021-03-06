package com.jsandusky.jrpg.battle;
import com.jsandusky.jrpg.*;
import com.jsandusky.jrpg.model.*;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.decals.*;

public class BattleState extends JRPGState
{	
	Party party;
	ArrayList<BattleAgent> agents = new ArrayList<BattleAgent>(6);
	BattleSystem battleSys;
	
	public BattleState(Party party, MonsterTeam enemies) {
		
		for (Reference<Monster> mons : enemies.Members) {
			Monster m = mons.get();
			agents.add(new BattleMonster(m));
		}
		
		for (Hero h : party.Heroes) {
			agents.add(new BattleCharacter(h));
		}
		
		EngineSetup setup = null;
		
		switch (setup.Battle) {
			case DQ:
				battleSys = new DQBattleSystem(this);
				break;
			case FFVI:
				battleSys = new FF3BattleSystem(this);
				break;
			case FFMQ:
				battleSys = new FFMQBattleSystem(this); 
				break;
			case NONE:
				//how the hell did you get here
				break;
		}
	}
	
	public void update()
	{
		battleSys.run();
	}

	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
		battleSys.draw(cam,realCam,dBatch,sBatch,bmf);
	}
}
