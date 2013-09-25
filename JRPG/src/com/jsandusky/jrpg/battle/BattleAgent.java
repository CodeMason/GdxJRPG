package com.jsandusky.jrpg.battle;

public abstract class BattleAgent
{
	public int Health;
	public int MaxHealth;
	public int Magic;
	public int MaxMagic;
	
	public abstract int getInitiative();
	public BattleAction getAction() {
		return null;
	}
}
