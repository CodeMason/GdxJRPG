package com.jsandusky.jrpg.map;
import com.badlogic.gdx.math.*;

public class Actor
{
	public GridPoint2 Position;
	
//used for smooth movement
	transient Vector2 startMoveLerp;
	transient Vector2 endMoveLerp;
}
