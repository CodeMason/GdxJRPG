package com.jsandusky.jrpg.map;
import java.util.*;
import java.io.*;

import com.jsandusky.jrpg.pathfinding.AStarPathFinder;
import com.jsandusky.jrpg.pathfinding.ManhattanHeuristic;
import com.jsandusky.jrpg.pathfinding.PathTileMap;
import com.jsandusky.jrpg.model.*;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.jsandusky.util.*;
import com.jsandusky.gdx.common.*;
import com.jsandusky.dungeon.RandomDungeon;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;
import java.io.*;
import com.badlogic.gdx.utils.Array;

public class GameMap implements IDraw, PathTileMap, Serializable
{
	public String fileName = "";
 	transient Resources res_;
	transient AStarPathFinder finder;
	transient ShapeRenderer shapeRender;
	transient Sprite voidTile;
	public boolean AllowFree = false;
	
	//Actors living on the map
	public ArrayList<GameActor> Actors = new ArrayList<GameActor>();
	
	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{		
		boolean doFullLighting = false;//platform == null ? true : platform.getLightingSetting() == 3;
		boolean doFlatLighting = false;//platform == null ? false : platform.getLightingSetting() == 2;
		if (doFullLighting) {
			iterator(new SquareSelector() {
			public boolean select(Square sq) {
				Vector3 thisPos = sq.getPosition();
				sq.drawLight_ = false;
				for (Tile t : sq.Tiles) {
					t.sprite.getVertices()[SpriteBatch.C1] = sq.lightColor_.toFloatBits();
					t.sprite.getVertices()[SpriteBatch.C2] = sq.lightColor_.toFloatBits();
					t.sprite.getVertices()[SpriteBatch.C3] = sq.lightColor_.toFloatBits();
					t.sprite.getVertices()[SpriteBatch.C4] = sq.lightColor_.toFloatBits();
				}
				if (sq.neighbors != null) {
					for (Square n : sq.neighbors) {
						Vector3 pos = n.getPosition();
						if (pos.x == thisPos.x+1) {
							if (pos.y == thisPos.y + 1) {
								for (Tile t : sq.Tiles) {
									t.sprite.getVertices()[SpriteBatch.C3] = n.lightColor_.toFloatBits();
								}
							} else if (pos.y == thisPos.y-1) {
								for (Tile t : sq.Tiles) {
									t.sprite.getVertices()[SpriteBatch.C4] = n.lightColor_.toFloatBits();
								}
							}
						} else if (pos.x == thisPos.x-1) {
							if (pos.y == thisPos.y + 1) {
								for (Tile t : sq.Tiles) {
									t.sprite.getVertices()[SpriteBatch.C2] = n.lightColor_.toFloatBits();
								}
							} else if (pos.y == thisPos.y-1) {
								for (Tile t : sq.Tiles) {
									t.sprite.getVertices()[SpriteBatch.C1] = n.lightColor_.toFloatBits();
								}
							}
						}
					}
				}
				return false;
			}
			});
		} else if (doFlatLighting) {
			iterator(new SquareSelector() {
				public boolean select(Square sq) {
					sq.drawLight_ = true;
					return false;
				}
			});
		}
		
		
		
		
		for (int x = 0; x < width; ++x) {
			if (x*75 > realCam.position.x + realCam.viewportWidth/2)
				continue;
			if (x*75 + 75 < realCam.position.x -  realCam.viewportWidth/2)
				continue;
			for (int y = 0; y < height; ++y) {
				if (y*75 > realCam.position.y + realCam.viewportHeight/2)
					continue;
				if (y*75+75 < realCam.position.y - realCam.viewportHeight/2)
					continue;
				if (squares[x][y] != null) {
					squares[x][y].draw(cam, realCam, dBatch, sBatch, bmf);
				} else if (drawGridLines) {
					voidTile.setPosition(x*75, y*75);
					voidTile.draw(sBatch);
				}
			}
		}
		sBatch.flush();
	}
	
	transient boolean drawGridLines = false;
	
	public void setDrawGridLines(boolean val) {
		drawGridLines = val;
	}
	
	Square[][] squares;
	int width = 0, height = 0;
	int tileSize = 75;

	public Resources getResources() {
		return res_;
	}

	public int getHeight () {
		return height;
	}

	public int getWidth () {
		return width;
	}

	public int getTileSize () {
		return tileSize;
	}

	public Square getSquare ( GridPoint2 p ) {
		if (p == null)
			return null;
		return getSquare( (int)p.x, (int)p.y);
	}

	public Square getSquare ( int i, int j) {
		return squares[i][j];
	}

	public boolean hasSquare ( GridPoint2 p ) {
		return hasSquare ( (int)p.x, (int)p.y);
	}

	public boolean hasSquare ( int i, int j) {
		if ( i < 0 || i >= width || j < 0 || j >= height) {
			return false;
		}
		return squares[i][j] != null;
	}

	public GameMap (Resources res) {
		res_ = res;
	}
	
	private GameMap() {
		
	}
	
	public void setResources(Resources res) {
		res_ = res;
	}

	public void initialize () {
		Square[] squares = squareList ();
		for ( int i = 0; i < squares.length; i++ ) {
			squares[i].initialize ();
		}
		setNeighbors ();
	}

	public String toString () {
		return "Width: " + width + " Height: " + height;
	}

	public void shiftLeft () {
		// Shift all the rows left
		for ( int j = 0; j < height; j++ ) {
			for ( int i = 0; i < width - 1; i++ ) {
				squares[i][j] = squares[i + 1][j];
			}
			squares[width - 1][j] = null;
		}
		setSquareIndices ();
	}

	public void shiftRight () {
		// Shift all the rows right
		for ( int j = 0; j < height; j++ ) {
			for ( int i = width - 2; i > 0; i-- ) {
				squares[i][j] = squares[i - 1][j];
			}
			squares[0][j] = null;
		}
		setSquareIndices ();
	}

	public void shiftDown () {
		// Shift all the rows down
		for ( int i = 0; i < width; i++ ) {
			// Square initial = squares[i][height-1];
			for ( int j = height - 2; j > 0; j-- ) {
				squares[i][j] = squares[i][j - 1];
			}
			squares[i][0] = null;
		}
		setSquareIndices ();
	}

	public void shiftUp () {
		// Shift all the rows up
		for ( int i = 0; i < width; i++ ) {
			for ( int j = 0; j < height - 1; j++ ) {
				squares[i][j] = squares[i][j + 1];
			}
			squares[i][height - 1] = null;
		}
		setSquareIndices ();
	}

	public void resize ( int x, int y) {

		// Change size
		Square[][] newSquares = new Square[x][y];

		if ( squares != null ) {
			// Copy as much as we can from the old
			for ( int xx = 0; xx < x; xx++ ) {
				for ( int yy = 0; yy < y; yy++ ) {
					newSquares[xx][yy] = null;
				}
			}

			int i = Math.min ( width, x );
			int j = Math.min ( height, y );
			for ( int xx = 0; xx < i; xx++ ) {
				for ( int yy = 0; yy < j; yy++ ) {
					newSquares[xx][yy] = squares[xx][yy];
				}
			}
		}
		width = x;
		height = y;
		squares = newSquares;
		setSquareIndices ();
		
		finder = new AStarPathFinder(this, 64, true);
	}

	public void setSquare ( Square s, int x, int y) {
		squares[x][y] = s;
		if ( s != null ) {
			squares[x][y].x = x;
			squares[x][y].y = y;
		}
	}

	public void addToSquare ( Tile t, int x, int y) {
		if ( squares[x][y] == null ) {
			squares[x][y] = new Square ();
		}
		squares[x][y].addTile ( t );
		squares[x][y].loadTileResources(res_);
	}
	
	public void createSquare(GridPoint2 pt) {
		Square sq = new Square();
		setSquare(sq,(int)pt.x,(int)pt.y);
	}

	public void loadTileImages () {
		// We've just been loaded, load all the images in all the tiles
		//load up whatever?
		//res_.getImageCache().loadRes("indoor_tiles","data/tex/indoortiles.pack");
		
		int i, j, k;
		for ( i = 0; i < width; i++ ) {
			for ( j = 0; j < height; j++ ) {
				if ( squares[i][j] != null ) {
					squares[i][j].loadTileResources(this.res_);
				}
			}
		}
		setSquareIndices ();
		
		voidTile = new Sprite(res_.getImageCache().getTexture("sys_void").region);
	}
	
	void cleanSquares() {
		for ( int i = 0; i < width; i++ ) {
			if (squares[i].length != height) {
				squares[i] = Arrays.copyOf(squares[i],width);
				for (int x = 0; x < squares[i].length; ++x) {
					//if (squares[i][x] == null)
						//??squares[i][x] = new Square[depth];
				}
			}
		}
	}
	
	void setSquareIndices () {
		for ( int i = 0; i < width; i++ ) {
			for ( int j = 0; j < height; j++ ) {
				if (squares[i][j] == null)
					continue;
				if ( squares[i][j] != null ) {
					squares[i][j].x = i;
					squares[i][j].y = j;
				}
			}
		}
	}

	// Set the neighbors of each square
	public void setNeighbors () {
		Iterator<Square> sit = iterator();
		while ( sit.hasNext () ) {
			Square square = sit.next ();
			ArrayList<Square> neighbors = new ArrayList<Square> ();
			int i, j, k;
			for ( i = -1; i < 2; i++ ) {
				for ( j = -1; j < 2; j++ ) {
					for ( k = -1; k < 2; k++ ) {
						if ( i == 0 && j == 0 && k == 0 ) {
							continue;
						}
						GridPoint2 offset = new GridPoint2( square.x + i, square.y + j);
						if ( hasSquare ( offset ) ) {
							Square n = getSquare ( offset );
							if ( n.isFloor () ) {
								neighbors.add ( n );
							}
						}
					}
				}
			}
			square.neighbors = neighbors.toArray ( new Square[] {} );
			// logger.debug ( "Setting " + square.neighbors.length + " neighbors of "
			// + square.getPosition() );
		}
	}

	public Iterator<Square> iterator() {
		return iterator(-1,selectAll);
	}
	
	public Iterator<Square> iterator ( SquareSelector selector ) {
		return iterator ( -1, selector );
	}

	public Iterator<Square> iterator ( int d, SquareSelector selector ) {
		return squareArrayList ( d, selector ).iterator ();
	}
	
	private transient SquareSelector selectAll = new SquareSelector() {
		public boolean select(Square sq) {return true;}
	};

	public Square[] squareList () {
		return squareList ( -1, selectAll );
	}

	public Square[] squareList ( int d ) {
		return squareList ( d, selectAll );
	}

	public Square[] squareList ( SquareSelector s ) {
		return squareList ( -1, s );
	}

	public Square[] squareList ( int d, SquareSelector selector ) {
		return squareArrayList ( d, selector ).toArray ( new Square[] {} );
	}

	public ArrayList<Square> squareArrayList ( int d, SquareSelector selector ) {
		setSquareIndices ();
		ArrayList<Square> a = new ArrayList<Square> ( 100 );
		int startk = d, endk = d;
		// logger.debug ( "Iterating from: " + startk + " to " + endk );
		for ( int i = 0; i < width; i++ ) {
			for ( int j = 0; j < height; j++ ) {
				// TODO
				try {
				if ( squares[i][j] != null ) {
					if ( selector.select ( squares[i][j] ) ) {
						a.add ( squares[i][j] );
					}
				}
				} catch (Exception ex) {}
			}
		}
		return a;
	}

	@Override
	public int getWidthInTiles() {
		return this.width;
	}

	@Override
	public int getHeightInTiles() {
		return this.height;
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		//??
	}

	@Override
	public boolean blocked(Object mover, int x, int y) {
		final boolean sq = hasSquare(x,y);
		if (sq == false)
			return true;
		//final int dir = Facing.getDirection(((Piece)mover).Position, new Vector3(x,y,0));
		//final int cost = ((Piece)mover).costToMove(dir);
		return false;
	}

	@Override
	public float getCost(Object mover, int sx, int sy, int tx, int ty) {
		return new Vector2(sx,sy).dst2(new Vector2(tx,ty));
	}
	
	public Pixmap getPixmap(String team) {
		this.cleanSquares();
		int pixW = 32;
		int pixH = 32;
		if (width > 32) {
			pixW = 64;
		}
		if (height > 32) {
			pixH = 64;
		}
		if (width > 64)
			pixW = 128;
		if (height > 64)
			pixH = 128;
		Pixmap ret = new Pixmap(pixW,pixH,Pixmap.Format.RGB565);
		ret.setColor(Color.BLACK);
		ret.fill();
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				if (hasSquare(x,y)) {
					Square sq = getSquare(x,y);
					boolean hasOpenDoor = false;
					boolean hasClosedDoor = false;
					boolean hasFriend = false;
					boolean hasEnemy = false;
					for (int i = 0; i < sq.Tiles.size(); ++i) {
						/*if (sq.Tiles.get(i) instanceof Door) {
							boolean open = ((Door)sq.Tiles.get(i)).isOpen();
							hasOpenDoor = open;
							hasClosedDoor = !open;
						}*/
						if (hasOpenDoor) {
							ret.drawPixel(x,y,Color.BLUE.toIntBits());
						} else if (hasClosedDoor) {
							ret.drawPixel(x,y,Color.ORANGE.toIntBits());
						} else {
							ret.drawPixel(x,y,Color.WHITE.toIntBits());
						}
					}
					if (hasFriend)
						ret.drawPixel(x,y,Color.GREEN.toIntBits());
					else if (hasEnemy)
						ret.drawPixel(x,y,Color.RED.toIntBits());
				} else {

				}
			}
		}
		return ret;
	}
	
	public static GameMap loadMap(String fn) {
		Json json = new Json();
		try {
			GameMap ret = json.fromJson(GameMap.class, Gdx.files.local(fn));
			return ret;
		} catch (Exception ex) {
			
		}
		return null;
	}
	public static GameMap loadMap(FileHandle fn) {
		Json json = new Json();
		//try {
			GameMap ret = json.fromJson(GameMap.class, fn);
			return ret;
		//} catch (Exception ex) {
			
		//}
		//return null;
	}
	
	public static class MapRecord {
		public Pixmap overhead;
		public GameMap map;
		public String name;
		public String desc;
	}
	
	public static ArrayList<MapRecord> getStockMaps() {
		ArrayList<MapRecord> ret = new ArrayList<MapRecord>();
		FileHandle fh = null;
		if (Gdx.app.getType() == ApplicationType.Android) {
			fh = Gdx.files.internal("data/maps"); 
		} else {
			fh = Gdx.files.internal("./bin/data/maps");
		}
		if (fh.exists())
			getMaps(fh,ret);
		return ret;
	}
	
	public static ArrayList<MapRecord> getCustomMaps() {
		ArrayList<MapRecord> ret = new ArrayList<MapRecord>();
		FileHandle fh = Gdx.files.local("");
		if (fh.exists())
			getMaps(fh,ret);
		return ret;
	}

	static void getMaps(FileHandle fh, ArrayList<MapRecord> recs)  {
		for (FileHandle f : fh.list()) {
			MapRecord rec = new MapRecord();
			GameMap map = loadMap(f);
			rec.map = map;
			rec.overhead = map.getPixmap("human");
			recs.add(rec);
		}
	}
	
	static Vector2 toMapCoords(Vector2 in) {
		return in.cpy().div(75);
	}
	
	static Vector2 toWorldCoords(Vector2 in) {
		return in.cpy().mul(75);
	}
}

