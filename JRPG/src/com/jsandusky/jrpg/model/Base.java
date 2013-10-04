package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.UUID;
import java.util.ArrayList;

public class Base implements Serializable
{
	public String Name;
	UUID id_ = UUID.randomUUID();
	
	public void setName(String name) {
		Name = name;
	}
	
	public String getName() {
		return Name;
	}
	
	public UUID getID() {
		return id_;
	}
	
	@Override
	public String toString() {
		if (Name != null && !Name.isEmpty())
			return Name;
		return id_.toString();
	}
	
	//This is used as a reflection helper for
	//the Ref / RefTwin annotations
	//this way ArrayList< Reference<C> > can be used
	// with @Ref(cl = C) instead of something more psycho
	// Yes it is a hack - but it's better than the alternatives
	public static boolean MustBeInRef(Class c) {
		if (refClasses.size() == 0) {
			refClasses.add(Animation.class);
			refClasses.add(CharacterClass.class);
			refClasses.add(CharacterSet.class);
			refClasses.add(Cutscene.class);
			refClasses.add(Dialog.class);
			refClasses.add(Equipment.class);
			refClasses.add(Hero.class);
			refClasses.add(Item.class);
			refClasses.add(MapMonster.class);
			refClasses.add(Monster.class);
			refClasses.add(MonsterTeam.class);
			refClasses.add(NPC.class);
			refClasses.add(Party.class);
			refClasses.add(Shop.class);
			refClasses.add(Skill.class);
		}
		if (refClasses.contains(c))
			return true;
		return false;
	}
	
	static ArrayList<Class> refClasses = new ArrayList<Class>();
}
