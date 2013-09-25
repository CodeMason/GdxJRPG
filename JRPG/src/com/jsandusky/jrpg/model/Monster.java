package com.jsandusky.jrpg.model;
import java.io.*;
import java.util.ArrayList;
import com.jsandusky.gdx.common.TextureHandle;

public class Monster extends Base implements Serializable
{
	public String Name;
	public TextureHandle Image;
	public TextureHandle HurtImage; //below 50%
	public TextureHandle AlmostOverImage; //below 25%
	public int Vitality;
	public int Magic;
	public int Defense;
	public int Strength;
	public int Speed;
	
	public ArrayList<Resistance> Resistances = new ArrayList<Resistance>();
	public ArrayList< Reference<Skill> > Skills = new ArrayList< Reference<Skill> >();
}
