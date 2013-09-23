package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;

public class Hero extends Base implements Serializable
{
	public String Name;
	public String Story;
	public int StartingLevel;
	public int CurrentLevel;
	public int MaxLevel;
	public int XP;
	public boolean CanChangeEquipment = true;
	public Reference<CharacterClass> Class;
	public Reference<CharacterSet> CharSet;
	public ArrayList< Reference<Skill> > Skills;
	public ArrayList< Reference<Equipment> > Equipped;
}
