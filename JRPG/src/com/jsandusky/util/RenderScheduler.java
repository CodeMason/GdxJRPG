package com.jsandusky.util;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderScheduler {
	ArrayList<IRenderScheduled> scheduled_ = new ArrayList<IRenderScheduled>(16);
	public RenderScheduler() {
	}
	public void run(Camera cam, SpriteBatch sb, BitmapFont bmf) {
		if (scheduled_.size() > 0) {
			scheduled_.get(0).run(cam, sb, bmf);
			scheduled_.remove(0);
		}
	}
	public void add(IRenderScheduled sched) {scheduled_.add(sched);}
	public void clear() {scheduled_.clear();}
}
