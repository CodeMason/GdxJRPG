package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;
import com.jsandusky.gdx.common.TextureHandle;

public class Item extends Base implements Serializable
{
	public String Name;
	public TextureHandle Image; //in inventory
	public ArrayList<Modifier> Modifiers = new ArrayList<Modifier>();
	public boolean Ressurect;
	public Reference<Skill> ApplySkill;
}
