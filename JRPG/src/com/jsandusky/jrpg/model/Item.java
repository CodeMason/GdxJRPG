package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;
import com.jsandusky.gdx.common.TextureHandle;
import com.jsandusky.util.Ref;

public class Item extends Base implements Serializable
{
	public TextureHandle Image; //in inventory
	public ArrayList<Modifier> Modifiers = new ArrayList<Modifier>();
	public boolean Ressurect;
	
	@Ref(cl = Skill.class)
	public Reference<Skill> ApplySkill;
}
