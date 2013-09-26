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
	
	//called by player UI to push a player's action
	//up to the battle system how this is handled
	public abstract void pushPlayerAction(BattleAction action);
}
