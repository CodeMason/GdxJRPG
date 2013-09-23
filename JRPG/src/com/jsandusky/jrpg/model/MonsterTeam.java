package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;

/* A collection of monsters - used for encounter */
public class MonsterTeam extends Base implements Serializable
{
	public String Name;
	public ArrayList< Reference<Monster> > Members;
}
