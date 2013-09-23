package com.jsandusky.gdx.util;

public interface IUIController {
	public boolean routeTouchDown(float x, float y, int ptr);
	public boolean routeTouchUp(float x, float y, int ptr);
	public boolean routeTouchDrag(float x, float y, int ptr);
}
