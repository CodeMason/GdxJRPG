package com.jsandusky.jrpg;
import com.jsandusky.gdx.util.IDriver;
import com.jsandusky.gdx.common.Menu;

public class JRPGDriver extends IDriver
{
	public boolean keyDown(int p1)	{
		return false;
	}
	public boolean keyUp(int p1)	{
		return false;
	}
	public boolean keyTyped(char p1)	{
		return false;
	}
	public boolean touchDown(int p1, int p2, int p3, int p4)	{
		Menu[] m = this.menu.toArray(new Menu[menu.size()]);
		for (int i = m.length-1; i >= 0; --i) {
			if (m[i].routeTouchDown(p1,p2,p3)) {
				return true;
			}
		}
		return false;
	}
	public boolean touchUp(int p1, int p2, int p3, int p4)	{
		Menu[] m = this.menu.toArray(new Menu[menu.size()]);
		for (int i = m.length-1; i >= 0; --i) {
			if (m[i].routeTouchUp(p1,p2,p3)) {
				return true;
			}
		}
		return false;
	}
	public boolean touchDragged(int p1, int p2, int p3)	{
		Menu[] m = this.menu.toArray(new Menu[menu.size()]);
		for (int i = m.length-1; i >= 0; --i) {
			if (m[i].routeTouchDrag(p1,p2,p3)) {
				return true;
			}
		}
		return false;
	}
	public boolean mouseMoved(int p1, int p2)	{
		//??
		return false;
	}
	public boolean scrolled(int p1)	{
		return false;
	}
}
