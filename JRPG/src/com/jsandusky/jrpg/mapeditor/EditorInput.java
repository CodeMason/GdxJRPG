package com.jsandusky.jrpg.mapeditor;
import com.badlogic.gdx.input.*;
import com.badlogic.gdx.math.*;
import com.jsandusky.jrpg.map.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import java.util.*;
import com.jsandusky.jrpg.model.*;
import com.jsandusky.gdx.util.*;

public class EditorInput implements GestureDetector.GestureListener
{
	Camera cam;
	EditorState editor;
	public EditorInput(EditorState editor,Camera cam) {
		this.editor = editor;
		this.cam = cam;
	}
	
	public void setMap(GameMap map) {
		//this.map = map;
	}

	public boolean touchDown(float p1, float p2, int p3, int p4)
	{
		// TODO: Implement this method
		return false;
	}

	public boolean tap(float x, float y, int pointer, int button)
	{
		Vector3 vec = new Vector3(x,y,0);
		cam.unproject(vec);
		//???TODO vec = GameState.MapCoords(vec);
		if (editor.getEditor().getMode() == Editor.Mode.Paint) {
			editor.getEditor().paint(vec.x,vec.y);
			return true;
		} else if (editor.getEditor().getMode() == Editor.Mode.Erase) {
			editor.getEditor().erase(vec.x,vec.y);
			return true;
		} else if (editor.getEditor().getMode() == Editor.Mode.Rotate) {
			editor.getEditor().rotateTile(vec.x,vec.y);
			return true;
		} else if (editor.getEditor().getMode() == Editor.Mode.Settings) {
			ArrayList<TileSettings> settings = editor.getEditor().getSettings(vec.x,vec.y);
			final Vector3 fVec = vec;
			if (settings != null && settings.size() > 0) {
				if (IDriver.getActive().getMenu(ReflectiveEditorMenu.class) == null) {
					IDriver.getActive().pushMenu(new ReflectiveEditorMenu(settings.toArray(), true) {
						public void onFinish(Object obj) {
							Object[] vals = (Object[])obj;
							ArrayList<TileSettings> settings = new ArrayList<TileSettings>();
							for (Object objs : vals) {
								if (objs instanceof TileSettings)
									settings.add(TileSettings.class.cast(objs));
							}
							editor.getEditor().setSettings(settings, fVec.x,fVec.y);
						}					
					});
				}
				return true;
			}
		} else if (editor.getEditor().getMode() == Editor.Mode.Pieces) {
			/*Piece p = editor.editor.maker.getPiece();
			if (editor.editor.map.hasSquare(vec)); {
				Square sq = editor.editor.map.getSquare(vec);
				sq.Pieces.clear();
				if (p != null) {
					sq.Pieces.add(p);
					p.Position = sq.getPosition().cpy();
					p.loadResources(editor.editor.map.getResources());
				}
			}*/
		} else if (editor.getEditor().getMode() == Editor.Mode.Special) {
			if (editor.editor.map.hasSquare((int)vec.x,(int)vec.y)) {
				editor.getEditor().sp.doPaint(editor.editor.map.getSquare((int)vec.x,(int)vec.y));
				editor.editor.map.getSquare((int)vec.x,(int)vec.y).loadTileResources(editor.editor.map.getResources());
			} else {
				editor.getEditor().sp.doEmpty(vec,editor.editor.map);
			}
		}
		return false;
	}

	public boolean longPress(float p1, float p2)
	{
		return false;
	}

	public boolean fling(float p1, float p2, int p3)
	{
		return false;
	}

	public boolean pan(float x, float y, float dx, float dy)
	{
		cam.position.x -= dx;
		cam.position.y += dy;
		cam.update();
		return false;
	}

	public boolean zoom(float initialDist, float distance)
	{
		float factor = distance / initialDist;

		float ratio = cam.viewportHeight / cam.viewportWidth;
		if (initialDist > distance) {
			cam.viewportWidth += factor*4;
			cam.viewportHeight = cam.viewportWidth * ratio;
		} else if (initialDist < distance) {
			cam.viewportWidth -= factor*4;
			cam.viewportHeight = cam.viewportWidth * ratio;
		}


		// clamps field of view to common angles...
		if (cam.viewportWidth < Gdx.graphics.getWidth()/4) 
			cam.viewportWidth = Gdx.graphics.getWidth()/4;
		if (cam.viewportWidth > Gdx.graphics.getWidth()*2) 
			cam.viewportWidth = Gdx.graphics.getWidth()*2;

		if (cam.viewportHeight < Gdx.graphics.getHeight()/4) 
			cam.viewportHeight = Gdx.graphics.getHeight()/4;
		if (cam.viewportHeight > Gdx.graphics.getHeight()*2) 
			cam.viewportHeight = Gdx.graphics.getHeight()*2;

		cam.update();
		
		return false;
	}

	public boolean pinch(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4)
	{
		// TODO: Implement this method
		return false;
	}
	
}
