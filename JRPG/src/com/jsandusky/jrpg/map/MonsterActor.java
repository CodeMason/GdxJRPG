package com.jsandusky.jrpg.map;
import com.jsandusky.jrpg.model.Reference;
import com.jsandusky.jrpg.model.MapMonster;
import com.jsandusky.jrpg.model.Database;

public class MonsterActor extends GameActor
{
	public Reference<MapMonster> Monster;
	public boolean PaceLeftRight;
	public boolean PaceUpDown;
	
	public void load(Database db) {
		if (Monster != null)
			Monster.get(db);
	}
}
