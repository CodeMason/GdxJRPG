package com.jsandusky.jrpg.battle;
import com.jsandusky.jrpg.model.Skill;
import com.jsandusky.gdx.common.IDraw;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.decals.*;

public class BattleAction implements IDraw
{

	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
		
	}

	BattleAgent source;
	BattleAgent target;
	Skill skill;
	
	public BattleAction(BattleAgent source, BattleAgent target, Skill skill) {
		this.source = source;
		this.target = target;
		this.skill = skill;
	}
	
	public BattleAgent getSource() {return source;}
	public BattleAgent getTarget() {return source;}
	public Skill getSkill() {return skill;}
	
	public void startAction() {
		
	}
	
	public int getInitiativeMod() {
		return 0;
	}
	
	public boolean playing() {
		return false;
	}
}
