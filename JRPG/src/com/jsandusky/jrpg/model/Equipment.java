package com.jsandusky.jrpg.model;
import java.util.ArrayList;
import com.jsandusky.util.Ref;

public class Equipment extends Base
{
	public String Name;
	public String BaseKind; //like weapon
	public String Kind; //like sword
	
	@Ref(cl = Modifier.class)
	public ArrayList<Modifier> Modifiers;
	@Ref(cl = Skill.class)
	public Reference<Skill> WeaponSkill; //if is a weapon type
	@Ref(cl = Skill.class)
	public Reference<Skill> OnHitSkill; //if armor, use for "of thorns" shit
	@Ref(cl = Skill.class)
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
