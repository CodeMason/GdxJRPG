package com.jsandusky.jrpg.map;
import java.util.*;

import com.jsandusky.jrpg.model.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;
import com.jsandusky.gdx.common.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.graphics.*;
import java.io.*;

public class Square implements IDraw, Serializable, Comparable
{
	public int compareTo(Object o)
	{
		Square s = (Square)o;
		if ( aStarCost < s.aStarCost ) { return 1; }
		if ( aStarCost == s.aStarCost ) { return 0; }
		return -1;
	}

	transient boolean drawLight_;
	transient Color lightColor_;
	
	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
		for (Tile t : Tiles) {
			if (t.sprite != null) {
				t.sprite.setPosition(this.x*75, this.y*75);
				if (tint != null) {
					if (tint.equals(Color.WHITE) && lightColor_ != null && drawLight_) {
						t.sprite.setColor(lightColor_);
					} else if (!tint.equals(Color.WHITE))
						t.sprite.setColor(tint);
				}
				/*if (facing.equals(Facing.FacingNorth))
					t.sprite.setRotation(0);
				if (facing.equals(Facing.FacingEast))
					t.sprite.setRotation(90);
				if (facing.equals(Facing.FacingSouth))
					t.sprite.setRotation(180);
				if (facing.equals(Facing.FacingWest))
					t.sprite.setRotation(270);*/
				t.sprite.draw(sBatch);
			}
		}
	}
	transient Color tint;
	
	public void setTint(Color t) {
		tint = t;
	}

	public ArrayList<Tile> Tiles = new ArrayList<Tile>();
	transient public Square[] neighbors;
	public String facing; //??
	
	public void addTile(Tile tile) {
		if (Tiles == null) {
			Tiles = new ArrayList<Tile>();
		}
		Tiles.add(tile);
	}
	
	public int x;
	public int y;
	public int z;
	public float aStarCost;
	transient public Square previous;
	public int fireLevel;
	
	public void decrementFlame() {
		if (fireLevel > 0)
			--fireLevel;
	}
	
	//Astar stuff
	transient public int weight;
	transient public int distance;
	transient public boolean processed;	
	
	public boolean isFloor() {
		return Tiles != null && Tiles.size() > 0;
	}
	
	public Vector3 getPosition() {
		return new Vector3(x,y,z);
	}
	
	public Square() {
		Tiles.add(null);
		Tiles.add(null);
		Tiles.add(null);
		Tiles.add(null);
		//facing = Facing.FacingNorth;
		neighbors = null;
	}

	public void initialize () {
		neighbors = null;
	}
	
	public void relax () {
		for ( int i = 0; i < neighbors.length; i++ ) {
			if ( !neighbors[i].processed ) {
				if ( neighbors[i].distance > ( this.distance + neighbors[i].weight ) ) {
					neighbors[i].distance = this.distance + neighbors[i].weight;
					neighbors[i].previous = this;
				}
			}
		}
	}
	
	//direction from the point to this square
	public int dirFrom(Vector3 pt) {
		boolean up = false;
		boolean left = false;
		boolean right = false;
		boolean down = false;
		if (pt.x > x)
			left = true;
		if (pt.y > y)
			down = true;
		if (pt.y < y)
			up = true;
		if (pt.x < x)
			right = true;
		/*if (left && down) {
			return Facing.DirSW;
		} else if (left && up) {
			return Facing.DirNW;
		} else if (left) {
			return Facing.DirW;
		} else if (right && down) {
			return Facing.DirSE;
		} else if (right && up) {
			return Facing.DirNE;
		} else if (right) {
			return Facing.DirE;
		} else if (up) {
			return Facing.DirN;
		} else if (down) {
			return Facing.DirS;
		}*/
		return -1;
	}
	
	public int blocksLOS () { return blocksLOS ( true ); }
	public int blocksLOS ( boolean flamesBlock ) {
		// If we are not a floor
		if ( !isFloor() ) 
			return 2;
		if ( hasDoor() && !isDoorOpen()) 
			return 2;
		for (Tile t : Tiles) {
			/*if (t.isObject()) {
				return 2;
			}*/
		}
		return 0;
	}
	
	public boolean hasDoor() {
		if (Tiles.size() == 1)
			return false;
		for (Tile t : Tiles) {
			if (t instanceof DoorTile) {
				return true;
			}
		}
		return false;
	}
	
	public boolean canWalkThrough() {
		if (hasDoor() && !isDoorOpen())
			return false;
		if (this.fireLevel > 0)
			return false;
		for (Tile t: Tiles) {
			/*if (t.isObject())
				return false;*/
		}
		return true;
	}
	
	public boolean isDoorOpen() {
		for (Tile t : Tiles) {
			if (t instanceof DoorTile) {
				return ((DoorTile)t).open;
			}
		}
		return false;
	}
	
	public DoorTile getDoor() {
		for (Tile t : Tiles) {
			if (t instanceof DoorTile) {
				return (DoorTile)t;
			}
		}
		return null;
	}
	public boolean canOpenDoor() {
		for (Tile t : Tiles) {
			if (t instanceof DoorTile) {
				return !((DoorTile)t).locked;
			}
		}
		return false;
	}
	
	public void loadTileResources(Resources res) {
		for (Tile t : Tiles)
			t.loadResources(res);
	}
	
	public ArrayList<TileSettings> getSettings() {
		ArrayList<TileSettings> ret = new ArrayList<TileSettings>();
		for (Tile t: Tiles) {
			TileSettings set = t.getSettings();
			if (set != null) {
				ret.add(set);
			}
		}
		return ret;
	}
	
	public void setSettings(ArrayList<TileSettings> settings) {
		for (Tile t : Tiles) {
			for (TileSettings set : settings) {
				t.setSettings(set);
			}
		}
	}
}
