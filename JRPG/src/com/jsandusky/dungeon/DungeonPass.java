package com.jsandusky.dungeon;

//Passes can be performed before or after the core dungeon generating algo is run
//	Use prepasses to place set items
//  Use postpasses to add thematic details like nested rooms or column strings
public interface DungeonPass {
	public void execute(RandomDungeon dungeon);
}
