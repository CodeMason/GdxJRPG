package com.jsandusky.jrpg.battle;
import com.jsandusky.jrpg.model.Skill;
import com.jsandusky.gdx.common.IDraw;

public class BattleAction
{
	BattleAgent source;
	BattleAgent target;
	Skill skill;
	
	public BattleAction(BattleAgent source, BattleAgent target, Skill skill) {
		this.source = source;
		this.target = target;
		this.skill = skill;
	}
	
	public void doAction() {
		
	}
	
	public int getInitiativeMod() {
		return 0;
	}
	
	public boolean playing() {
		return false;
	}
}
