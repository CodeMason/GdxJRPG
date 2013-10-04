package com.jsandusky.jrpg.map;
import com.badlogic.gdx.math.*;
import java.io.Serializable;
import com.jsandusky.jrpg.model.Database;

public abstract class Actor implements Serializable
{
	public GridPoint2 Position;
	
//used for smooth movement
	public Vector2 FinePosition;
	transient Vector2 startMoveLerp;
	transient Vector2 endMoveLerp;
	
	public abstract void load(Database db);
}
