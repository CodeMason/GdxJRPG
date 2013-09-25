package com.jsandusky.jrpg.battle;
import com.jsandusky.jrpg.model.Hero;

public class BattleCharacter extends BattleAgent
{
	public BattleCharacter(Hero h) {
		hero = h;
	}
	public Hero hero; //??err nothing else?
	
	public int getInitiative() {
		return 0;
	}
}
