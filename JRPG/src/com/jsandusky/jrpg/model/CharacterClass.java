package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;

public class CharacterClass extends Base implements Serializable
{
	public String Name;
	public String Story;
	
	public int MaxLevel; //determines stat increase per level
	
	public int BaseStrength;
	public String StrengthName = "Strength";
	public int BaseDefense;
	public String DefenseName = "Defense";
	public int BaseMagic;
	public String MagicName = "Magic"; //so you can call it something else
	public int BaseVitality;
	public String VitalityName = "Vitality";
	
	public int MaxStrength;
	public int MaxDefense;
	public int MaxMagic;
	public int MaxVitality;
	
	public int getStrength(int level) {
		float fraction = level / MaxLevel;
		int delta = MaxStrength - BaseStrength;
		delta = (int)(delta * fraction);
		return BaseStrength + delta;
	}
	public int getDefense(int level) {
		float fraction = level / MaxLevel;
		int delta = MaxDefense - BaseDefense;
		delta = (int)(delta * fraction);
		return BaseDefense + delta;
	}
	public int getMagic(int level) {
		float fraction = level / MaxLevel;
		int delta = MaxMagic - BaseMagic;
		delta = (int)(delta * fraction);
		return BaseMagic + delta;
	}
	public int getVitality(int level) {
		float fraction = level / MaxLevel;
		int delta = MaxVitality - BaseVitality;
		delta = (int)(delta * fraction);
		return BaseVitality + delta;
	}
	
	public ArrayList< Reference<Skill> > SkillsAtLeve = new ArrayList< Reference<Skill> >();
	
	public ArrayList<String> AllowedEquipment;
}
