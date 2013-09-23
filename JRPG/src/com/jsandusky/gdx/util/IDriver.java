package com.jsandusky.gdx.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.jsandusky.gdx.common.Menu;

//the 'Driver' is the mechanics mechanism by which core control is handled
public abstract class IDriver implements InputProcessor {
	static IDriver activeDriver;
	protected ConcurrentLinkedQueue<Menu> menu = new ConcurrentLinkedQueue<Menu>();
	//protected ArrayList< WeakReference<IUIController> > controllers = new ArrayList< WeakReference<IUIController> >();
	
	
	public IDriver activate() {
		activeDriver = this;
		return this;
	}
	
	public void activate(int menu) {}
	
	public static IDriver getActive() {
		return activeDriver;
	}
	
	public void pushMenu(Menu m) {
		for (Menu men : menu) {
			if (men.getClass() == m.getClass())
				return; //you are retarded!
		}
		menu.add(m);
	}
	
	public void setMenu(Menu m) {
		menu.clear();
		menu.add(m);
	}
	
	public void clearMenus() {
		for (Menu m : menu)
			m.dispose();
		menu.clear();
	}
	
	public void removeMenu(Class c) {
		for (Menu m : menu) {
			if (m.getClass() == c) {
				m.dispose();
				menu.remove(m);
			}
		}
	}
	
	public Menu getMenu(Class c) {
		for (Menu m : menu) {
			if (m.getClass() == c || m.getClass().isAssignableFrom(c))
				return m;
		} return null;
	}
	
	public void removeNotOf(Class c) {
		for (Menu m : menu) {
			if (m.getClass() != c) {
				m.dispose();
				menu.remove(m);
			}
		}
	}
	
	public void removeMenu(Menu m) {
		m.dispose();
		menu.remove(m);
	}
	
	public void drawUI(Camera cam, Camera real, DecalBatch db, SpriteBatch sb, BitmapFont bmf) {
		Iterator<Menu> mit = menu.iterator();
		ArrayList<Menu> temp = new ArrayList<Menu>();
		while (mit.hasNext())
			temp.add(mit.next());
		for (Menu m : temp)
			m.draw(cam, real, db, sb, bmf);
	}
}
