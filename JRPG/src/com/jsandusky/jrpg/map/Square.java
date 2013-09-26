package com.jsandusky.jrpg.map;
import java.util.ArrayList;
import com.badlogic.gdx.math.*;
import java.io.Serializable;

public class Square implements Serializable
{
	public static int Layers = 4;
	public ArrayList<Tile> Tiles;
	public ArrayList<Event> Events;
	public GridPoint2 Position;
//computed at map load
	transient Square[] Neighbors;
	transient boolean blocked; //means a tile makes this blocked
	
	public Square(GridPoint2 pos) {
		Position = pos;
		Tiles = new ArrayList<Tile>(Layers);
		for (int i = 0; i < Layers; ++i) {
			Tiles.add(null);
		}
		Events = new ArrayList<Event>();
	}
	
	private Square() {
		
	}	
	
	public void putTile(Tile t, int layer) {
		Tiles.set(layer-1,t);
	}
	
	public Square cpy() {
		Square ret = new Square();
		for (Tile t : Tiles) {
			ret.Tiles.add(t.cpy());
		}
		return ret;
	}
}
