package com.jsandusky.jrpg.mapeditor;
import com.jsandusky.jrpg.map.*;
import com.jsandusky.jrpg.model.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Json;
import com.jsandusky.gdx.common.*;
import java.util.*;

public class Editor
{
	public enum Mode {
		Paint,
		Erase,
		Rotate,
		Settings,
		Pieces,
		Special,
		None
	}
	
	public static interface SpecialPainter {
		public void doPaint(Square sq);
		public void doEmpty(Vector3 pt, GameMap map);
	}
	
	public void setPainter(SpecialPainter p) {
		sp = p;
	}
	SpecialPainter sp;
	boolean changed = false;
	Mode mode;
	GameMap map;
	int layer;
	Tile currentTile;
	String filename;
	TextureHandle currentTexture;
	
	public void setMode(Mode m) {
		mode = m;
	}
	public Mode getMode() {
		return mode;
	}
	
	public boolean hasChanges() {
		return changed;
	}
	
	public void clearChanges() {
		changed = false;
	}
	public void setTexture(TextureHandle tex) {
		currentTexture = tex;
		if (tex.name.contains("door")) {
			currentTile = new DoorTile();
		} else {
			currentTile = new BasicTile(tex.name);
		}
	}
	
	java.util.Stack<EditorAction> undoStack = new java.util.Stack<EditorAction>();
	java.util.Stack<EditorAction> redoStack = new java.util.Stack<EditorAction>();
	
	public void clearUndoRedo() {
		undoStack.clear();
		redoStack.clear();
	}
	
	public boolean canUndo() {
		return undoStack.size() > 0;
	}
	
	public boolean canRedo() {
		return redoStack.size() > 0;
	}
	
	public void undo() {
		EditorAction action = undoStack.pop();
		action.undo(map);
		redoStack.push(action);
	}
	public void redo() {
		EditorAction action = redoStack.pop();
		action.redo(map);
		undoStack.push(action);
	}
	
	void pushUndo(EditorAction action) {
		undoStack.push(action);
		redoStack.clear();
	}
	
	public Editor(GameMap map) {
		this.map = map;
	}
	
	public abstract static class EditorAction {
		public abstract void undo(GameMap map);
		public abstract void redo(GameMap map);
	}
	
	public EditorAction paint(float x, float y) {
		if (x < 0 || y < 0 || y >= map.getHeight() || x >= map.getHeight())
			return null;
		Square old = null;
		if (map.hasSquare((int)x,(int)y))
			old = map.getSquare((int)x,(int)y);
		Square paintSquare = new Square();
		if (!(this.currentTile instanceof BasicTile)) {
			if (old == null) { 
				//can't paint here
				return null;
			}
			paintSquare.Tiles.add(old.Tiles.get(0));
			paintSquare.Tiles.add(currentTile);
		} else {
			if (old != null) {
				for (int i = 0; i < old.Tiles.size(); ++i) {
					paintSquare.Tiles.add(old.Tiles.get(i));
				}
				paintSquare.Tiles.set(0,currentTile);
			} else {
				paintSquare.Tiles.add(currentTile);
			}
		}
		
		EditorAction action = new PaintAction(new Vector3(x,y,0), old, paintSquare);
		action.redo(map);
		pushUndo(action);
		return action;
	}
	
	public EditorAction rotateTile(float x, float y) {
		if (x < 0 || y < 0 || y >= map.getHeight() || x >= map.getHeight())
			return null;
		if (map.hasSquare((int)x,(int)y)) {
			Square sq = map.getSquare((int)x,(int)y);
			/*int curFacing = Facing.facingToIndex(sq.facing);
			int newFacing = curFacing + 1;
			if (newFacing > 3)
				newFacing = 0;
			EditorAction action = new RotateSquareAction(sq,curFacing,newFacing);
			action.redo(map);
			pushUndo(action);
			return action;*/
		}
		return null;
	}
	
	public ArrayList<TileSettings> getSettings(float x, float y) {
		if (x < 0 || y < 0 || y >= map.getHeight() || x >= map.getHeight())
			return null;
		if (map.hasSquare((int)x,(int)y)) {
			Square sq = map.getSquare((int)x,(int)y);
			return sq.getSettings();
		} return null;
	}
	
	public void setSettings(ArrayList<TileSettings> settings, float x, float y) {
		if (x < 0 || y < 0 || y >= map.getHeight() || x >= map.getHeight())
			return;
		if (map.hasSquare((int)x,(int)y)) {
			Square sq = map.getSquare((int)x,(int)y);
			ChangeSettingsAction action = new ChangeSettingsAction(sq,sq.getSettings(),settings);
			action.redo(map);
			pushUndo(action);
		}
	}
	
	public EditorAction changeLayer(int newLayer) {
		return null;
	}
	
	public EditorAction erase(float x, float y) {
		if (x < 0 || y < 0 || y >= map.getHeight() || x >= map.getHeight())
			return null;
		Square old = null;
		if (map.hasSquare((int)x,(int)y)) {
			old = map.getSquare((int)x,(int)y);
			EditorAction action = new PaintAction(new Vector3(x,y,0),old,null);
			action.redo(map);
			pushUndo(action);
			return action;
		} 
		return null;
	}
	
	public EditorAction setActiveTile() {
		return null;
	}
	
	
	public EditorAction shift(int dir) {
		EditorAction action = new ShiftAction(dir);
		action.redo(map);
		pushUndo(action);
		return action;
	}
	
	public EditorAction resize(int x, int y, int z) {
		EditorAction action = new ResizeAction(new Vector3(map.getWidth(), map.getHeight(),0), new Vector3(x,y,z));
		action.redo(map);
		pushUndo(action);
		return action;
	}
	
	public EditorAction saveToFile(String aFileName) {
		Json json = new Json();
		String jsonData = json.toJson(this.map);
		FileHandle fh = Gdx.files.local(aFileName);
		fh.writeString(jsonData, false);
		return null;
	}
	
	static class PaintAction extends EditorAction {
		Square old, newSq;
		Vector3 at;
		public PaintAction(Vector3 pt, Square o, Square n) {
			old = o;
			newSq = n;
			at = pt;
		}
		
		public void undo(GameMap map) {
			map.setSquare(old,(int)at.x,(int)at.y);
			Square sq = map.getSquare((int)at.x,(int)at.y);
			if (sq != null) {
				sq.loadTileResources(map.getResources());
			}
		}
		public void redo(GameMap map) {
			map.setSquare(newSq,(int)at.x,(int)at.y);
			Square sq = map.getSquare((int)at.x,(int)at.y);
			if (sq != null) {
				sq.loadTileResources(map.getResources());
			}
		}
	}
	
	static class ResizeAction extends EditorAction {
		Vector3 old, newSize;
		public ResizeAction(Vector3 oldDim, Vector3 newDim) {
				old = oldDim;
				newSize = newDim;
		}
		public void undo(GameMap map) {
			map.resize((int)old.x,(int)old.y);
		}
		public void redo(GameMap map) {
			map.resize((int)newSize.x,(int)newSize.y);
		}
	}
	
	static class ShiftAction extends EditorAction {
		int dir;
		public ShiftAction(int dir) {
			this.dir = dir;
		}
		public void undo(GameMap map) {
			/*if (dir == Facing.DirN) {
				map.shiftDown();
			} else if (dir == Facing.DirS) {
				map.shiftUp();
			} else if (dir == Facing.DirE) {
				map.shiftLeft();
			} else if (dir == Facing.DirW) {
				map.shiftRight();
			}*/
		}
		public void redo(GameMap map) {
			/*if (dir == Facing.DirN) {
				map.shiftUp();
			} else if (dir == Facing.DirS) {
				map.shiftDown();
			} else if (dir == Facing.DirE) {
				map.shiftRight();
			} else if (dir == Facing.DirW) {
				map.shiftLeft();
			}*/
		}
	}
	
	static class RotateSquareAction extends EditorAction {
		int oldDir, newDir;
		Square sq;
		public RotateSquareAction(Square square, int oldDir, int newDir) {
			this.oldDir = oldDir;
			this.newDir = newDir;
			sq = square;
		}
		public void undo(GameMap map) {
			//sq.facing = Facing.idxToFacing(oldDir);
		}
		public void redo(GameMap map) {
			//sq.facing = Facing.idxToFacing(newDir);
		}
	}
	
	static class ChangeSettingsAction extends EditorAction {
		ArrayList<TileSettings> settings;
		ArrayList<TileSettings> old;
		Square sq;
		
		public ChangeSettingsAction(Square square, ArrayList<TileSettings> old, ArrayList<TileSettings> newSettings) {
			settings = newSettings;
			this.old = old;
			sq = square;
		}
		
		public void undo(GameMap map) {
			sq.setSettings(old);
		}
		public void redo(GameMap map) {
			sq.setSettings(settings);
		}
	}
}
