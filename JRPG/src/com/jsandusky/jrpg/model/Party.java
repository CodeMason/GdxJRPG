package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;

public class Party extends Base implements Serializable
{
	public String PartyName; //used internally
	public ArrayList<Hero> Heroes = new ArrayList<Hero>();
	public ArrayList< Reference<Item> > Items = new ArrayList< Reference<Item> >();
	public ArrayList< Reference<Equipment> > Gear = new ArrayList< Reference<Equipment> >();
}
