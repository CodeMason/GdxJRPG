package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;
import com.jsandusky.util.Ref;

public class Party extends Base implements Serializable
{
	public String PartyName; //used internally
	@Ref(cl = Hero.class)
	public ArrayList<Hero> Heroes = new ArrayList<Hero>();
	@Ref(cl = Item.class)
	public ArrayList< Reference<Item> > Items = new ArrayList< Reference<Item> >();
	@Ref(cl = Equipment.class)
	public ArrayList< Reference<Equipment> > Gear = new ArrayList< Reference<Equipment> >();
}
