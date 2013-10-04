package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;
import com.jsandusky.util.Ref;

public class Hero extends Base implements Serializable
{
	public String Name;
	public String Story;
	public int StartingLevel;
	public int CurrentLevel;
	public int MaxLevel;
	public int XP;
	public boolean CanChangeEquipment = true;
	
	@Ref(cl = CharacterClass.class)
	public Reference<CharacterClass> Class;
	@Ref(cl = CharacterSet.class)
	public Reference<CharacterSet> CharSet;
	@Ref(cl = Skill.class)
	public ArrayList< Reference<Skill> > Skills;
	@Ref(cl = Equipment.class)
	public ArrayList< Reference<Equipment> > Equipped;
}
