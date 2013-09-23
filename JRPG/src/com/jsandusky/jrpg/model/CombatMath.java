package com.jsandusky.jrpg.model;
import com.jsandusky.gdx.util.Random;

public class CombatMath
{
//Monster attacks character
	public static int getDamage(Random r, Monster monster, Skill skill, Hero h) {
		return 0;
	}

//Character attacks monster
	public static int getDamage(Random r, Hero h, Skill skill, Monster monster) {
		
		int runningTotal = 0;
		for (Skill.Effect fx : skill.Effects) {
			int baseDmg = fx.Base;
			int randDmg = r.randI(-fx.Variance,fx.Variance);
			float total = baseDmg + randDmg;
			
			//Check for attribute damage boosts
			float mul = 1.0f;
			if (fx.Source != Statistic.Invalid) {
				switch (fx.Source) {
					case Strength:
						mul += h.Class.get().getStrength(h.CurrentLevel) / h.Class.get().MaxStrength;
						total *= mul;
						break;
					case Magic:
						mul += h.Class.get().getStrength(h.CurrentLevel) / h.Class.get().MaxStrength;
						total *= mul;
						break;
					case Speed:
						mul += h.Class.get().getStrength(h.CurrentLevel) / h.Class.get().MaxStrength;
						total *= mul;
						break;
					case Defense:
						mul += h.Class.get().getStrength(h.CurrentLevel) / h.Class.get().MaxStrength;
						total *= mul;
						break;
					case Vitality:
						mul += h.Class.get().getStrength(h.CurrentLevel) / h.Class.get().MaxStrength;
						total *= mul;
						break;
				}
			}
			
			//Check for monster resistances
			for (Resistance res : monster.Resistances) {
				if (res.Elem == fx.Elem)
					total *= (1.0f - res.Amount);
			}
			
			//Apply any status effects
			if (!hasStatusEffect(fx.Elem)) {
				runningTotal += total;
			} else {
				//apply status effect
			}
		}
		
		return runningTotal;
	}
	
	public static boolean hasStatusEffect(Element elem) {
		switch (elem) {
			case Poison:
			case Petrifying:
			case Silencing:
			case Paralyzing:
			case Stunning:
			case Confusing:
				return true;
			default:
				return false;
		}
	}
}
