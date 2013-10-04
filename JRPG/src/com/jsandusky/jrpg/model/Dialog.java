package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;
import com.jsandusky.gdx.common.TextureHandle;
import com.jsandusky.util.Ref;

public class Dialog extends Base implements Serializable
{
	public TextureHandle Portrait;
	@Ref(cl = String.class)
	public ArrayList<String> Pieces;
		//only called when the dialog is finished
	public String Script; //receives worldstate, NPC, scriptcore
}
