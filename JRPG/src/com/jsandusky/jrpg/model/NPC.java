package com.jsandusky.jrpg.model;
import java.io.Serializable;

public class NPC extends Base implements Serializable
{
	public Reference<CharacterSet> CharSet;
	public Reference<Shop> Shop;
	public Reference<Dialog> Dialog;
}
