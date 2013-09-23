package com.jsandusky.dungeon;


//Adds random strings of columns sort of like Diablo 1's cathedral
public class ColumStringPostFX implements DungeonPass {

	@Override
	public void execute(RandomDungeon d) {
		//create a string of columns
		for (int i = 0; i < 40; ++i) {
			int dir = d.getRand(0,3);
			int randX = d.getRand(1, d.xSize() - 2);
			int randY = d.getRand(1, d.ySize() - 2);
			int x = randX, y = randY;
			int len = 0;
			while (d.getCell(x,y) == 2) {
				++len;
				++x;
			}
			if (d.getCell(x,y) == 5) //avoid boxing off doors
				continue;
			while (d.getCell(x,y) == 2) {
				--x;
			}
			if (d.getCell(x,y) == 5) //avoid boxing off doors
				continue;
			if (len <= 6)
				continue;
			while (len > 0) {
				d.setCell(randX, randY,1);
				randX+=2;
				len-=2;
			}
		}
	}

}
