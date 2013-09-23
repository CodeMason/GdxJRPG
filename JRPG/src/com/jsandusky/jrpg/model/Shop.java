package com.jsandusky.jrpg.model;
import java.util.ArrayList;

public class Shop extends Base
{
	public String Name;
	public ArrayList< Reference<Item> > Items;
	public ArrayList< Reference<Equipment> > Equipment;
}
