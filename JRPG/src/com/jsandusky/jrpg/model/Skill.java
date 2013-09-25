package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;

public class Skill extends Base implements Serializable
{
	public class Effect {
		public Element Elem = Element.Physical;
		public Statistic Source = Statistic.Invalid;
		public int Base;
		public int Variance;
	}
	
	public String Name;
	public String Description;
	public String UsingText; //An FString
	public Usage Usage;
	public int MagicCost;
	public boolean TargetAll;
	public boolean TargetRandom;
	public int UseTimes = 1;
	public boolean TargetFriend;
	public boolean TargetDeadFriend;
	public String Script; //skill(Target,Caster)
	public Reference<Animation> Anim;
	public String CharacterAnim; //may be null
	
	public ArrayList<Effect> Effects;
	
	public enum Usage {
		Always,
		Battle,
		Menu,
		Never,
	}
}
