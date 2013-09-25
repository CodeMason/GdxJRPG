package com.jsandusky.jrpg.battle;
import com.jsandusky.gdx.common.*;

//handles the abstraction of how turns are handled and such
public abstract class BattleSystem implements IDraw
{
	protected BattleState State;
	
	public BattleSystem(BattleState state) {
		State = state;
	}
	
	public abstract void run();
}
