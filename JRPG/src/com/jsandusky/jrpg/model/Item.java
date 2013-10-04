package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;
import com.jsandusky.gdx.common.TextureHandle;
import com.jsandusky.util.Ref;

/* An item is considered to be consumable */
public class Item extends Base implements Serializable
{
	public TextureHandle Image; //in inventory
	
	@Ref(cl = Modifier.class)
	public ArrayList<Modifier> Modifiers = new ArrayList<Modifier>();
	
	public boolean Ressurect;
	
	@Ref(cl = Skill.class)
	public Reference<Skill> ApplySkill;
	
	public boolean canUse() {
		return true;
	}
}
