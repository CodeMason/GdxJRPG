package com.jsandusky.jrpg.model;
import java.io.Serializable;
import com.jsandusky.util.Ref;

public class NPC extends Base implements Serializable
{
	@Ref(cl = CharacterSet.class)
	public Reference<CharacterSet> CharSet;
	
	@Ref(cl = Shop.class)
	public Reference<Shop> Shop;
	
	@Ref(cl = Dialog.class)
	public Reference<Dialog> Dialog;
}
