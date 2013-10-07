package com.jsandusky.jrpg.map;
import com.jsandusky.jrpg.model.Reference;
import com.jsandusky.jrpg.model.NPC;
import com.jsandusky.jrpg.model.Database;

public class NPCActor extends GameActor
{
	Reference<NPC> NPC;
	
	public void load(Database db) {
		NPC.get(db);
	}
}
