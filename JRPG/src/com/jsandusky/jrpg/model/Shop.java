package com.jsandusky.jrpg.model;
import java.util.ArrayList;
import com.jsandusky.util.Ref;

public class Shop extends Base
{
	public String Name;
	
	@Ref(cl = Item.class)
	public ArrayList< Reference<Item> > Items;
	
	@Ref(cl = Equipment.class)
	public ArrayList< Reference<Equipment> > Equipment;
	
	public boolean IsInn;
}
