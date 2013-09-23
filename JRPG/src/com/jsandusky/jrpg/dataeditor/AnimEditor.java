package com.jsandusky.jrpg.dataeditor;
import com.jsandusky.gdx.common.Menu;
import com.jsandusky.jrpg.model.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.*;

public class AnimEditor extends Menu
{
	Animation anim;
	int onFrame = 0;
	Vector2 offset;
	Sprite soundIcon;
	Sprite centerPoint;
	GestureDetector detector_;
	Frame.FrameData selected;
	float zoom = 1f;
	
	public class Listener implements GestureDetector.GestureListener
	{
		AnimEditor editor;
		Sprite selectedSprite;
		
		public Listener(AnimEditor editor) {
			this.editor = editor;
		}
		public boolean touchDown(float p1, float p2, int p3, int p4)	{
			return false;
		}
		public boolean tap(float x, float y, int p3, int p4)	{
			Vector2 pt = new Vector2(x,Gdx.graphics.getHeight() - y);
			for (Frame.FrameData fd : editor.anim.Frames.get(editor.onFrame).FrameData) {
				fd.sprite.setPosition(fd.Offset.x - offset.x,fd.Offset.y - offset.y);
				fd.sprite.setScale(fd.Scale);
				
				if (fd.sprite.getBoundingRectangle().contains(pt)) {
					editor.selected = fd;
					return true;
				}
			}
			editor.selected = null;
			return false;
		}
		public boolean longPress(float p1, float p2)	{
			return false;
		}
		public boolean fling(float p1, float p2, int p3)	{
			return false;
		}
		public boolean pan(float x, float y, float delY, float delX)	{
			if (editor.selected == null) {
				editor.offset.x += delX;
				editor.offset.y += delY;
			} else {
				editor.selected.Offset.x += delY;
				editor.selected.Offset.y += delX;
			}
			return true;
		}
		public boolean zoom(float init, float newDist)	{
			zoom = init / newDist;
			return true;
		}
		public boolean pinch(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4)	{
			return false;
		}
	};
	
	public AnimEditor(Animation editing) {
		anim = editing;
		
	}	
	
	public void nextFrame() {
		++onFrame;
		onFrame = Math.min(onFrame,anim.Frames.size()-1);
	}
	
	public void prevFrame() {
		--onFrame;
		if (onFrame < 0)
			onFrame = 0;
	}
	
	
	public boolean routeTouchDown(float x, float y, int ptr)
	{
		return false;
	}

	public boolean routeTouchUp(float x, float y, int ptr)
	{
		return false;
	}

	public boolean routeTouchDrag(float x, float y, int ptr)
	{
		return false;
	}

	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
		centerPoint.setPosition(offset.x,offset.y);
		centerPoint.draw(sBatch);
		
		Frame frame = anim.Frames.get(onFrame);
		float soundY = Gdx.graphics.getHeight() - soundIcon.getHeight();
		for (Frame.FrameData fd : frame.FrameData) {
			fd.sprite.setPosition(fd.Offset.x - offset.x,fd.Offset.y - offset.y);
			fd.sprite.setScale(fd.Scale);
			fd.sprite.draw(sBatch);
			
			if (fd.Sound != null) {
				soundIcon.setPosition(0,soundY);
				soundIcon.draw(sBatch);
				soundY -= soundIcon.getHeight();
			}
		}
	}

	public void dispose()
	{
	}
}
