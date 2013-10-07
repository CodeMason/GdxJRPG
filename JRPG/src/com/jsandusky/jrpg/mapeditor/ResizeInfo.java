package com.jsandusky.jrpg.mapeditor;

import com.jsandusky.util.Spite;


//POD for ReflectiveEditorMenu to resize the map
public class ResizeInfo
{
	public ResizeInfo(int width, int height) {
		Width = width;
		Height = height;
	}
	@Spite(group="",tip="")
	public int Width = 28;
	@Spite(group="",tip="")
	public int Height = 28;
}
