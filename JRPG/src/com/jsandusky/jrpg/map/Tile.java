package com.jsandusky.jrpg.map;
import java.util.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.jsandusky.gdx.common.Resources;

import java.io.*;

public abstract class Tile implements Serializable
{
	public enum TileType {
		Closed,
		Floor,
		LadderDown,
		LadderUp,
		PitDown,
		PitUp,
		ControlStation,
		Tube
	}
	
	public int ShadowMask = 0; //1, 1<<1, 1<<2, 1<<3
	public TileType Type;
	public boolean onFire = false;
	public boolean isBloody = false; //splattered with gore?
	public boolean destroyed = false; //deck damaged / door wrecked
	transient public Sprite sprite;
	
	public abstract boolean isEntrance();
	public abstract boolean isExit();
	public abstract boolean isObject();
	
	public String Message; //this tile triggers a pop-up message when it's entered
	
	public abstract void loadResources(Resources res);
	public abstract TileSettings getSettings();
	public abstract void setSettings(TileSettings settings);
	
	public Event OnEnter;
}
