package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;
import com.jsandusky.util.Ref;

/* A collection of monsters - used for encounter */
public class MonsterTeam extends Base implements Serializable
{
	public String Name;
	
	@Ref(cl = Monster.class)
	public ArrayList< Reference<Monster> > Members;
}
