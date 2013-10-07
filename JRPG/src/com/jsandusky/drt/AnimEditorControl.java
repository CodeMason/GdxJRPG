package com.jsandusky.drt;
import com.badlogic.gdx.input.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.*;

//Handles the pane view animation touch / mouse controls controls
public class AnimEditorControl implements GestureDetector.GestureListener, InputProcessor
{	
	AnimEditorApp app;
	public AnimEditorControl(AnimEditorApp app) {
		this.app = app;
	}
	public boolean touchDown(float p1, float p2, int p3, int p4){
		return false;
	}
	public boolean tap(float x, float y, int p3, int p4){
		float ny = Gdx.graphics.getHeight() - y;
		Vector3 pt = new Vector3(x,ny,0);
		app.cam.project(pt);
		boolean hadSel = false;
		boolean chgSel = false;
		if (app.tools.currentKey != null) {
			for (Bone b : app.tools.currentKey.Bones) {
				//select this if it's not our selected bone
				if (b.Bounds.contains(pt.x,pt.y)) {
				 	if (b != app.tools.selectedBone && (app.tools.selectedBone == null || app.tools.selectedBone.SelfIndex < b.SelfIndex)) {
						chgSel = true;
						app.tools.selectedBone = b;
					} else {
						hadSel = true;
					}
				}
			}
			if (hadSel && !chgSel) { //tapped on sel without an overlap
				app.tools.selectedBone = null;
			}
		}
		return false;
	}
	public boolean longPress(float p1, float p2){
		return false;
	}
	public boolean fling(float p1, float p2, int p3){
		return false;
	}
	public boolean pan(float x, float y, float deltaX, float deltaY){
		if (app.tools.selectedBone != null) {
			if (app.mode_ == AnimEditorApp.Mode.Move) {
				app.tools.selectedBone.Position.add(deltaX,deltaY);
			} else if (app.mode_ == AnimEditorApp.Mode.Rotate) {
				//TODO: adjust dampening values
				if (deltaX < 0 && deltaY < 0)
					app.tools.selectedBone.Rotation -= (Math.min(deltaX,deltaY) * 1.0f);
				else
					app.tools.selectedBone.Rotation += (Math.max(deltaX,deltaY) * 1.0f);
			}
		}
		return false;
	}
	public boolean zoom(float initialDistance, float distance)	{
		final float factor = distance / initialDistance;
		return false;
	}
	public boolean pinch(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4){
		return false;
	}
	
	public boolean keyDown(int p1){	return false;}
	public boolean keyUp(int p1){return false;}
	public boolean keyTyped(char p1){return false;}
	public boolean touchDown(int p1, int p2, int p3, int p4){return false;}
	public boolean touchUp(int p1, int p2, int p3, int p4){return false;}
	public boolean touchDragged(int p1, int p2, int p3){return false;}
	public boolean mouseMoved(int p1, int p2){return false;}
	public boolean scrolled(int p1){return false;}
}
