package com.jsandusky.jrpg.model;
import java.util.ArrayList;

public class Equipment extends Base
{
	public String Name;
	public String BaseKind; //like weapon
	public String Kind; //like sword
	public ArrayList<Modifier> Modifiers;
	public Reference<Skill> WeaponSkill; //if is a weapon type
	public Reference<Skill> OnHitSkill; //if armor, use for "of thorns" shit
	public Reference<Skill> OnDefendingSkill; //if defensive, use for "of thorns" shit
	
	
	public void apply(Hero h) {
		for (Modifier m : Modifiers) {
			m.apply(h);
		}
	}
	
	public void remove(Hero h) {
		for (Modifier m : Modifiers) {
			m.remove(h);
		}
	}
}
