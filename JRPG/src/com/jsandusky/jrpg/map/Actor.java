package com.jsandusky.jrpg.map;
import com.badlogic.gdx.math.*;
import java.io.Serializable;

public class Actor implements Serializable
{
	public GridPoint2 Position;
	
//used for smooth movement
	public Vector2 FinePosition;
	transient Vector2 startMoveLerp;
	transient Vector2 endMoveLerp;
}
