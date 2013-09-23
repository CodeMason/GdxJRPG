package com.jsandusky.dungeon;

import java.util.ArrayList;

import com.jsandusky.gdx.common.TileMap;
import com.jsandusky.gdx.util.Random;

/*
 * Generates a random dungeon
 * 
 * Uses 'DungeonPass' types to do pre and post processing before/after
 * the core algoirthm
 */
public class RandomDungeon extends TileMap {
	
	int objects;
	int chanceRoom;
	int chanceCorridor;
	boolean useDoors = true;
	
	public static final int tileUnused = 1;// = 0;
	public static final int tileDirtWall = 1;// = 1; //not in use
	public static final int tileDirtFloor = 2;// = 2;
	public static final int tileStoneWall = 3;// = 3;
	public static final int tileCorridor = 4;// = 4;
	public static final int tileDoor = 5;// = 5;
	public static final int tileUpStairs = 6;// = 6;
	public static final int tileDownStairs = 7;// = 7;
	public static final int tileChest = 8;// = 8;
	public static final int tileDirty = 9;
	public static final int tileObjective = 10;
	public static final int tileEnemyEntrance = 11;
  	int maxRoomSizeX, maxRoomSizeY;
  	boolean roomsInRooms;
  	
  	ArrayList<DungeonPass> prePasses_ = new ArrayList<DungeonPass>();
  	ArrayList<DungeonPass> postPasses_ = new ArrayList<DungeonPass>();
	
  	Random rand_;
  	
  	public void addPrepass(DungeonPass pass) {
  		prePasses_.add(pass);
  	}
  	public void addPostProcess(DungeonPass pass) {
  		postPasses_.add(pass);
  	}
  	
  	public void setDoors(boolean val) {
  		useDoors = val;
  	}
	public boolean usingDoors() {
		return useDoors;
	}
  	
	public RandomDungeon(float x, float y, int roomSizeX, int roomSizeY, int complexity) {
		super(x, y);
		
		chanceRoom = 75;
		  chanceCorridor = 25;
		 
		  //our map
		  tile_map = null;
		 
		  //the old seed from the RNG is saved in this one
		  //oldseed = 0;
		 
		  //a list over tile types we're using
			maxRoomSizeX = roomSizeX;
			maxRoomSizeY = roomSizeY;
			roomsInRooms = false;
		 
		  //misc. messages to print
		  rand_ = new Random(java.lang.System.currentTimeMillis());
		
		generate((int)x,(int)y,complexity);
	}
	
	void generate(int inx, int iny, int inobj) {
		if (inobj < 1) 
			objects = 10;
		else 
			objects = inobj;

		// Adjust the size of the map if it's too small
		if (inx < 3) 
			xsize = 3;
		else 
			xsize = inx;

		if (iny < 3) 
			ysize = 3;
		else 
			ysize = iny;
		
		tile_map = new int[xsize*ysize];
		
		//start with making the "standard stuff" on the map
		for (int y = 0; y < ysize; y++) {
		  for (int x = 0; x < xsize; x++) {
			//ie, making the borders of unwalkable walls
			if (y == 0) setCell(x, y, tileStoneWall);
			else if (y == ysize-1) setCell(x, y, tileStoneWall);
			else if (x == 0) setCell(x, y, tileStoneWall);
			else if (x == xsize-1) setCell(x, y, tileStoneWall);

			//and fill the rest with dirt
			else setCell(x, y, tileUnused);
		  }
		}
		
		for (int i = 0; i < prePasses_.size(); ++i)
			prePasses_.get(i).execute(this);
		
		//start with making a room in the middle, which we can start building upon
		makeRoom(xsize/2, ysize/2, maxRoomSizeX, maxRoomSizeY, getRand(0,3));

		//keep count of the number of "objects" we've made
		int currentFeatures = 1; //+1 for the first room we just made

		//then we start the main loop
		for (int countingTries = 0; countingTries < 1000; countingTries++) {

			//check if we've reached our quota
			if (currentFeatures == objects)
			break;

			//start with a random wall
			int newx = 0;
			int xmod = 0;
			int newy = 0;
			int ymod = 0;
			int validTile = -1;

			//1000 chances to find a suitable object (room or corridor)..
			//(yea, i know it's kinda ugly with a for-loop... -_-')

			for (int testing = 0; testing < 1000; testing++) {
				newx = getRand(1, xsize-1);
				newy = getRand(1, ysize-1);
				validTile = -1;

				//System.out.println("tempx: " + newx + "\ttempy: " + newy);

				if (getCell(newx, newy) == tileDirtWall || getCell(newx, newy) == tileCorridor) {
				  //check if we can reach the place
				  if (getCell(newx, newy+1) == tileDirtFloor || getCell(newx, newy+1) == tileCorridor) {
					validTile = 0; //
					xmod = 0;
					ymod = -1;
				  }
				  else if (getCell(newx-1, newy) == tileDirtFloor || getCell(newx-1, newy) == tileCorridor) {
					validTile = 1; //
					xmod = +1;
					ymod = 0;
				  }

				  else if (getCell(newx, newy-1) == tileDirtFloor || getCell(newx, newy-1) == tileCorridor) {
					validTile = 2; //
					xmod = 0;
					ymod = +1;
				  }

				  else if (getCell(newx+1, newy) == tileDirtFloor || getCell(newx+1, newy) == tileCorridor) {
					validTile = 3; //
					xmod = -1;
					ymod = 0;
				  }

				  //check that we haven't got another door nearby, so we won't get alot of openings besides each other

				  if (validTile > -1) {
					if (getCell(newx, newy+1) == tileDoor) //north
					  validTile = -1;
					else if (getCell(newx-1, newy) == tileDoor)//east
					  validTile = -1;
					else if (getCell(newx, newy-1) == tileDoor)//south
					  validTile = -1;
					else if (getCell(newx+1, newy) == tileDoor)//west
					  validTile = -1;
				  }

				  //if we can, jump out of the loop and continue with the rest
				  if (validTile > -1) break;
				}
			}

			if (validTile > -1) {
				//choose what to build now at our newly found place, and at what direction
				int feature = getRand(0, 100);
				if (feature <= chanceRoom) { //a new room
					if (makeRoom((newx+xmod), (newy+ymod), maxRoomSizeX, maxRoomSizeY, validTile)) {
						currentFeatures++; //add to our quota
		    
						//then we mark the wall opening with a door
						if (useDoors) {
							setCell(newx, newy, tileDoor);
						} else {
							setCell(newx, newy, tileCorridor);
						}
		    
						//clean up infront of the door so we can reach it
						setCell((newx+xmod), (newy+ymod), tileDirtFloor);
					}
				} else if (feature >= chanceRoom) { //new corridor
					if (makeCorridor((newx+xmod), (newy+ymod), 6, validTile)) {
						//same thing here, add to the quota and a door
						currentFeatures++;
						if (useDoors)
							setCell(newx, newy, tileDoor);
						else
							setCell(newx, newy, tileCorridor);
					}
				}
			}
		}

		/*******************************************************************************
		  
		All done with the building, let's finish this one off
		  
		*******************************************************************************/

		//sprinkle out the bonusstuff (stairs, chests etc.) over the map
		int newx = 0;
		int newy = 0;
		int ways = 0; //from how many directions we can reach the random spot from
		int state = 0; //the state the loop is in, start with the stairs

		while (state != 10) {
		  for (int testing = 0; testing < 1000; testing++) {
			try {
			newx = getRand(1, xsize-1);
			newy = getRand(1, ysize-2); //cheap bugfix, pulls down newy to 0<y<24, from 0<y<25
			//System.out.println("x: " + newx + "\ty: " + newy);
			ways = 8; //the lower the better

			//check if we can reach the spot
			if (getCell(newx, newy+1) == tileDirtFloor || getCell(newx, newy+1) == tileCorridor) {
			  //north
			  if (getCell(newx, newy+1) != tileDoor)
				ways--;
			}
			if (getCell(newx+1, newy+1) == tileDirtFloor || getCell(newx+1, newy+1) == tileCorridor) {
			  //north-west
			  if (getCell(newx+1, newy+1) != tileDoor)
				ways--;
			}
			if (getCell(newx-1, newy+1) == tileDirtFloor || getCell(newx-1, newy+1) == tileCorridor) {
			  //north-east
			  if (getCell(newx-1, newy+1) != tileDoor)
				ways--;
			}

			if (getCell(newx-1, newy) == tileDirtFloor || getCell(newx-1, newy) == tileCorridor) {
			  //east
			  if (getCell(newx-1, newy) != tileDoor)
				ways--;
			}

			if (getCell(newx, newy-1) == tileDirtFloor || getCell(newx, newy-1) == tileCorridor) {
			  //south
			  if (getCell(newx, newy-1) != tileDoor)
				ways--;
			}
			if (getCell(newx+1, newy-1) == tileDirtFloor || getCell(newx+1, newy-1) == tileCorridor) {
			  //south west
			  if (getCell(newx+1, newy-1) != tileDoor)
				ways--;
			}
			if (getCell(newx-1, newy-1) == tileDirtFloor || getCell(newx-1, newy-1) == tileCorridor) {
			  //south east
			  if (getCell(newx-1, newy-1) != tileDoor)
				ways--;
			}

			if (getCell(newx+1, newy) == tileDirtFloor || getCell(newx+1, newy) == tileCorridor) {
			  //west
			  if (getCell(newx+1, newy) != tileDoor)
				ways--;
			}

			if (state == 0) {
			  if (ways == 0) {
				//we're in state 0, let's place a "upstairs" thing
				setCell(newx, newy, tileUpStairs);
				state = 1;
				break;
			  }
			} else if (state == 1) {
			  if (ways == 0) {
				//state 1, place a "downstairs"
				setCell(newx, newy, tileDownStairs);
				state = 10;
				break;
			  }
			}
			} catch (Exception ex) {
				
			}
		  }
		}

		//cycle through to clean up deadend corriders
		for (int num = 0; num < 10; ++num) {
			for (int x = 1; x < xsize-1; ++x) {
				for (int y = 1; y < ysize-1; ++y) {
					if (getCell(x,y) == tileCorridor) {
						int hitCt = 0;
						if ((getCell(x,y+1) == tileCorridor) || (getCell(x,y+1) == tileDoor))
							++hitCt;
						if ((getCell(x,y-1) == tileCorridor) || (getCell(x,y-1) == tileDoor))
							++hitCt;
						if ((getCell(x+1,y) == tileCorridor) || (getCell(x+1,y) == tileDoor))
							++hitCt;
						if ((getCell(x-1,y) == tileCorridor) || (getCell(x-1,y) == tileDoor))
							++hitCt;
						if (hitCt > 1)
							continue; //leave it
						setCell(x,y,tileUnused);
					} else if (getCell(x,y) == tileDoor) {
						int hitCt = 0;
						if ((getCell(x,y+1) == tileCorridor) || (getCell(x,y+1) == tileDirtFloor))
							++hitCt;
						if ((getCell(x,y-1) == tileCorridor) || (getCell(x,y-1) == tileDirtFloor))
							++hitCt;
						if ((getCell(x+1,y) == tileCorridor) || (getCell(x+1,y) == tileDirtFloor))
							++hitCt;
						if ((getCell(x-1,y) == tileCorridor) || (getCell(x-1,y) == tileDirtFloor))
							++hitCt;
						if (hitCt > 1)
							continue; //leave it
						int asWall = 0;
						if ((getCell(x,y+1) == tileDirtWall))
							++asWall;
						if ((getCell(x,y-1) == tileDirtWall))
							++asWall;
						if ((getCell(x+1,y) == tileDirtWall))
							++asWall;
						if ((getCell(x-1,y) == tileDirtWall))
							++asWall;
						setCell(x,y, asWall > 1 ? tileDirtWall : tileUnused);
					}
				}
			}
		}
		
		//clean up doors
		for (int x = 1; x < xsize-1; ++x) {
			for (int y = 1; y < ysize-1; ++y) {
				if (getCell(x,y) == tileDoor) {
					int ct = 0;
					int diagonal = 0;
					//number of directions leading from this door
					//Possible directions are horizontal = 2, vertical = 2
					//door should only have 1 direction
					//otherwise it's either at an intersection or packed into a corner
					int directions = 0;
					int lastDir = -1;
					if (getCell(x-1,y) != this.tileUnused && getCell(x-1,y) != this.tileDirtWall) {
						++ct;
						if (lastDir != 2)
							++directions;
						lastDir = 2;
					} 
					if (getCell(x+1,y) != this.tileUnused && getCell(x+1,y) != this.tileDirtWall) {
						++ct;
						if (lastDir != 2)
							++directions;
						lastDir = 2;
					} 
					if (getCell(x,y-1) != this.tileUnused && getCell(x,y-1) != this.tileDirtWall) {
						++ct;
						if (lastDir != 1)
							++directions;
						lastDir = 1;
					} 
					if (getCell(x,y+1) != this.tileUnused && getCell(x,y+1) != this.tileDirtWall) {
						++ct;
						if (lastDir != 1)
							++directions;
						lastDir = 1;
					}
					//If more than 2 walkable cardinal neighbors or has more than one axis
					//replace the door tile with a floor tile
					if (ct > 2 || directions > 1) {
						setCell(x,y,this.tileDirtFloor);
					}
				}
			}
		}
		
		for (int i = 0; i < postPasses_.size(); ++i) {
			postPasses_.get(i).execute(this);
		}
	}
	
	public int getRand(int min, int max) {
		return rand_.randI(min, max);
	}
	
	boolean makeCorridor(int x, int y, int length, int direction) {
		//define the dimensions of the corridor (er.. only the width and height..)
		int len = getRand(2, length);
		int floor = tileCorridor;
		int dir = 0;
		if (direction > 0 && direction < 4) dir = direction;

		int xtemp = 0;
		int ytemp = 0;

		// reject corridors that are out of bounds
		if (x < 0 || x > xsize) 
			return false;
		if (y < 0 || y > ysize) 
			return false;
		 
		switch(dir) {
		   
		  case 0: //north
			xtemp = x;

			// make sure it's not out of the boundaries
			for (ytemp = y; ytemp > (y-len); ytemp--) {
			  if (ytemp < 0 || ytemp > ysize) 
				  return false; //oh boho, it was!
			  if (getCell(xtemp, ytemp) != tileUnused) 
				  return false;
			}

			//if we're still here, let's start building
			for (ytemp = y; ytemp > (y-len); ytemp--) {
			  setCell(xtemp, ytemp, floor);
			}
			break;

		  case 1: //east
			ytemp = y;

			for (xtemp = x; xtemp < (x+len); xtemp++) {
			  if (xtemp < 0 || xtemp > xsize) 
				  return false;
			  if (getCell(xtemp, ytemp) != tileUnused) 
				  return false;
			}

			for (xtemp = x; xtemp < (x+len); xtemp++) {
			  setCell(xtemp, ytemp, floor);
			}
			break;

		  case 2: // south
			xtemp = x;

			for (ytemp = y; ytemp < (y+len); ytemp++) {
			  if (ytemp < 0 || ytemp > ysize) 
				  return false;
			  if (getCell(xtemp, ytemp) != tileUnused) 
				  return false;
			}

			for (ytemp = y; ytemp < (y+len); ytemp++) {
			  setCell(xtemp, ytemp, floor);
			}
			break;

		  case 3: // west
			ytemp = y;

			for (xtemp = x; xtemp > (x-len); xtemp--) {
			  if (xtemp < 0 || xtemp > xsize) 
				  return false;
			  if (getCell(xtemp, ytemp) != tileUnused) 
				  return false;
			}

			for (xtemp = x; xtemp > (x-len); xtemp--) {
			  setCell(xtemp, ytemp, floor);
			}
			break;
		  }

		//woot, we're still here! let's tell the other guys we're done!!
		return true;
	}
	
	boolean makeRoom(int x, int y, int xlength, int ylength, int direction) {
		//define the dimensions of the room, it should be at least 4x4 tiles (2x2 for walking on, the rest is walls)
	    int xlen = getRand(4, xlength);
	    int ylen = getRand(4, ylength);

	    //the tile type it's going to be filled with
	    int floor = tileDirtFloor; //jordgolv..
	    int wall = tileDirtWall; //jordv????gg

	    //choose the way it's pointing at
	    int dir = 0;
	    if (direction > 0 && direction < 4) 
	        dir = direction;

	    switch(dir) {

	    case 0: // north

	        //Check if there's enough space left for it
	        for (int ytemp = y; ytemp > (y-ylen); ytemp--) {
	            if (ytemp < 0 || ytemp > ysize) 
	                return false;
	            for (int xtemp = (x-xlen/2); xtemp < (x+(xlen+1)/2); xtemp++) {
	                if (xtemp < 0 || xtemp > xsize) 
	                    return false;
	                if (getCell(xtemp, ytemp) != tileUnused)
	                    return false; //no space left...
	            }
	        }

	        //we're still here, build
	        for (int ytemp = y; ytemp > (y-ylen); ytemp--) {
	            for (int xtemp = (x-xlen/2); xtemp < (x+(xlen+1)/2); xtemp++) {
	                //start with the walls
	                if (xtemp == (x-xlen/2)) 
	                    setCell(xtemp, ytemp, wall);
	                else if (xtemp == (x+(xlen-1)/2)) 
	                    setCell(xtemp, ytemp, wall);
	                else if (ytemp == y) 
	                    setCell(xtemp, ytemp, wall);
	                else if (ytemp == (y-ylen+1)) 
	                    setCell(xtemp, ytemp, wall);
	                //and then fill with the floor
	                else 
	                    setCell(xtemp, ytemp, floor);
	            }
	        }

	        break;

	    case 1: // east

	        for (int ytemp = (y-ylen/2); ytemp < (y+(ylen+1)/2); ytemp++) {
	            if (ytemp < 0 || ytemp > ysize) 
	                return false;
	            for (int xtemp = x; xtemp < (x+xlen); xtemp++) {
	                if (xtemp < 0 || xtemp > xsize) 
	                    return false;
	                if (getCell(xtemp, ytemp) != tileUnused)
	                    return false;
	            }
	        }

	        for (int ytemp = (y-ylen/2); ytemp < (y+(ylen+1)/2); ytemp++) {
	            for (int xtemp = x; xtemp < (x+xlen); xtemp++) {
	                if (xtemp == x) 
	                    setCell(xtemp, ytemp, wall);
	                else if (xtemp == (x+xlen-1)) 
	                    setCell(xtemp, ytemp, wall);
	                else if (ytemp == (y-ylen/2)) 
	                    setCell(xtemp, ytemp, wall);
	                else if (ytemp == (y+(ylen-1)/2)) 
	                    setCell(xtemp, ytemp, wall);
	                else 
	                    setCell(xtemp, ytemp, floor);
	            }
	        }

	        break;

	    case 2: // south

	        for (int ytemp = y; ytemp < (y+ylen); ytemp++) {
	            if (ytemp < 0 || ytemp > ysize) 
	                return false;
	            for (int xtemp = (x-xlen/2); xtemp < (x+(xlen+1)/2); xtemp++) {
	                if (xtemp < 0 || xtemp > xsize) 
	                    return false;
	                if (getCell(xtemp, ytemp) != tileUnused)
	                    return false;
	            }
	        }

	        for (int ytemp = y; ytemp < (y+ylen); ytemp++) {
	            for (int xtemp = (x-xlen/2); xtemp < (x+(xlen+1)/2); xtemp++) {
	                if (xtemp == (x-xlen/2)) 
	                    setCell(xtemp, ytemp, wall);
	                else if (xtemp == (x+(xlen-1)/2)) 
	                    setCell(xtemp, ytemp, wall);
	                else if (ytemp == y) 
	                    setCell(xtemp, ytemp, wall);
	                else if (ytemp == (y+ylen-1)) 
	                    setCell(xtemp, ytemp, wall);
	                else 
	                    setCell(xtemp, ytemp, floor);
	            }
	        }

	        break;

	    case 3: // west

	        for (int ytemp = (y-ylen/2); ytemp < (y+(ylen+1)/2); ytemp++) {
	            if (ytemp < 0 || ytemp > ysize) 
	                return false;
	            for (int xtemp = x; xtemp > (x-xlen); xtemp--) {
	                if (xtemp < 0 || xtemp > xsize) 
	                    return false;
	                if (getCell(xtemp, ytemp) != tileUnused)
	                    return false;
	            }
	        }

	        for (int ytemp = (y-ylen/2); ytemp < (y+(ylen+1)/2); ytemp++) {
	            for (int xtemp = x; xtemp > (x-xlen); xtemp--) {
	                if (xtemp == x) 
	                    setCell(xtemp, ytemp, wall);
	                else if (xtemp == (x-xlen+1)) 
	                    setCell(xtemp, ytemp, wall);
	                else if (ytemp == (y-ylen/2)) 
	                    setCell(xtemp, ytemp, wall);
	                else if (ytemp == (y+(ylen-1)/2)) 
	                    setCell(xtemp, ytemp, wall);
	                else 
	                    setCell(xtemp, ytemp, floor);
	            }
	        }

	        break;
	    }
	    //yay, all done
	    return true;
	}
	
	public int xSize() {return xsize;}
	public int ySize() {return ysize;}
	
	public void setCell(int x, int y, int type) {
		tile_map[x + xsize * y] = type;
	}
}
