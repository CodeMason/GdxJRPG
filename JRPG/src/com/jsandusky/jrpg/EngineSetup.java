package com.jsandusky.jrpg;

/* controls different engine settings for things like battle and movement */
public class EngineSetup
{
	public enum BattleSystem {
		FFMQ,
		DQ,
		FFVI,
		NONE //action rpg/zelda-clone
	}
	
	public enum MoveMode {
		Orthogonal, //NESW
		Diagonals, //can move diagonally
		Micro //tweaked legend of zelda
	}
	
	public enum MonsterMode {
		MovePerScript, //only movable by scripts
		MoveRandomly //they're placed and they just move whereever the hell they feel like going
	}
	
	public enum PartyMode {
		None, //there are no parties, always just one hero - simplifies menus
		Collapse,
		Trail, //like a bad centipede
		Free //select active party member and control
	}
	
	public BattleSystem Battle;
	public MoveMode Movement;
	public MonsterMode MapMonsters;
	public PartyMode Party;
	
	
	public EngineSetup(BattleSystem bs, MoveMode mm, MonsterMode mons, PartyMode p) {
		this.Battle = bs;
		this.Movement = mm;
		this.MapMonsters = mons;
		this.Party = p;
	}
	
	public EngineSetup(BattleSystem bs, MoveMode mm, PartyMode p) {
		this(bs,mm, MonsterMode.MovePerScript,p);
	}
	
	public EngineSetup(BattleSystem bs, MoveMode mm) {
		this(bs,mm,MonsterMode.MovePerScript,PartyMode.Collapse);
	}
	
	public EngineSetup(BattleSystem bs) {
		this(bs, MoveMode.Orthogonal, MonsterMode.MovePerScript, PartyMode.Collapse);
	}
}
