package com.jsandusky.jrpg.map;
import com.jsandusky.gdx.common.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import java.io.Serializable;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.math.*;
import com.jsandusky.jrpg.pathfinding.*;
import java.util.HashMap;

public class GameMap implements IDraw, Serializable, PathTileMap
{	
	public String FileName; //mtves.map
	public String ShortName; //mtves
	public String PrettyName;//Mt. Ves
	Color GlobalTint; //use to create the illusion of day night
	int width;
	int height;
	int tileDim;
	TextureHandle fogOverlay;
	float fogXSpeed;
	float fogYSpeed;
	transient boolean inEditor; //tells to draw void tiles, and show [E] squares and [*] named points
	
	Square[][] Tiles;
	Array<Actor> Actors = new Array<Actor>(32);
	Array<Overlay> Overlays = new Array<Overlay>();
	
	//These allows particular points on the map to have a names
		//this makes scripting easier to work with
		// eg. goTo(actor, "WaterWell") instead of goTo(actor,45,32) 
	HashMap<String,GridPoint2> NamedPoints = new HashMap<String,GridPoint2>();
	
	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
		//draw the first two layers
		for (int l = 0; l < 2; ++l) {
			for (int x = 0; x < width; ++x) {
				for (int y = 0; y < height; ++y) {
					if (Tiles[x][y] != null) {
						Tiles[x][y].Tiles.get(l).sprite.setPosition(x*tileDim,y*tileDim);
						Tiles[x][y].Tiles.get(l).sprite.draw(sBatch);
					}
				}
			}		
		}
		
		//draw entities
		for (Actor act : Actors) {
			//??draw the actor
		}
		
		//draw the last two layers
		for (int l = 2; l < 4; ++l) {
			for (int x = 0; x < width; ++x) {
				for (int y = 0; y < height; ++y) {
					if (Tiles[x][y] != null) {
						Tiles[x][y].Tiles.get(l).sprite.setPosition(x*tileDim,y*tileDim);
						Tiles[x][y].Tiles.get(l).sprite.draw(sBatch);
					}
				}
			}		
		}
		
		//Draw overlays last
		for (Overlay l : Overlays) {
			
		}
		
		if (inEditor) {
			//draw events
			//draw named points
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public GameMap(int width, int height, int tileDim) {
		this.width = width;
		this.height = height;
		this.tileDim = tileDim;
	}
	
	private GameMap() { //for serialization
		//??shoudl init with everything else
	}
	
	public void resize(int newWidth, int newHeight) {
		
	}
	
	public Square getSquare(GridPoint2 pt) {
		if (pt.x >= 0 && pt.y >= 0 && pt.x < width && pt.y < width)
			return Tiles[pt.x][pt.y];
		return null;
	}
	
	public GridPoint2 toMapCoords(Vector2 in) {
		return new GridPoint2(((int)in.x)/tileDim, ((int)in.y)/tileDim);
	}
	
	//will be lower left corner of square
	public Vector2 toScreenCoords(GridPoint2 in) {
		return new Vector2(in.x*((float)tileDim),in.y*((float)tileDim));
	}
	
	public Vector2 centerOf(GridPoint2 in) {
		return new Vector2(in.x*((float)tileDim) + tileDim/2,in.y*((float)tileDim) + tileDim/2);
	}
	
	public Array<Square> select(SquareSelector sel) {
		Array<Square> ret = new Array<Square>();
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < width; ++x) {
				if (Tiles[x][y] != null && sel.select(Tiles[x][y])) {
					ret.add(Tiles[x][y]);
				}
			}
		}
		return ret;
	}
	
	public void iterate(SquareSelector sel) {
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < width; ++x) {
				if (Tiles[x][y] != null) {
					sel.select(Tiles[x][y]);
				}
			}
		}
	}
	
	public int getWidthInTiles()
	{
		return width;
	}

	public int getHeightInTiles()
	{
		return height;
	}

	public void pathFinderVisited(int x, int y)
	{
	}

	public boolean blocked(Object mover, int x, int y)
	{
		return false;
	}

	public float getCost(Object mover, int sx, int sy, int tx, int ty)
	{
		return 1;
	}
}
