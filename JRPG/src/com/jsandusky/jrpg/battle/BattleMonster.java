package com.jsandusky.jrpg.battle;
import com.jsandusky.jrpg.model.Monster;

public class BattleMonster extends BattleAgent
{
	public BattleMonster(Monster m) {
		monster = m;
		Health = monster.Vitality;
		MaxHealth = monster.Vitality;
		Magic = monster.Magic;
		MaxMagic = monster.Magic;
	}

	public Monster monster;
	
	public float FadeTimer = 4f;
	
	public int getInitiative() {
		return monster.Speed;
	}
}
