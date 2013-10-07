package com.jsandusky.jrpg.mapeditor;

import com.jsandusky.util.Spite;

//POD used for the ReflectiveEditorMenu to control randomizing the map
public class RandomGenerator
{
	@Spite(group="",tip="")
	public int Width = 32;
	@Spite(group="",tip="")
	public int Height = 32;
	@Spite(group="",tip="")
	public boolean Doors = true;
	@Spite(group="",tip="")
	public int Complexity = 100;
}
