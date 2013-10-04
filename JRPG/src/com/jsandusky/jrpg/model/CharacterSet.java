package com.jsandusky.jrpg.model;
import com.jsandusky.gdx.common.TextureHandle;
import java.util.HashMap;
import com.jsandusky.util.RefTwin;

public class CharacterSet extends Base
{
	public TextureHandle Walk_N;
	public TextureHandle Walk_S;
	public TextureHandle Walk_E;
	public TextureHandle Walk_W;

	@RefTwin(KeyClass = String.class, ValueClass = TextureHandle.class)	
	public HashMap<String,TextureHandle> Other_N;
	
	@RefTwin(KeyClass = String.class, ValueClass = TextureHandle.class)	
	public HashMap<String,TextureHandle> Other_E;
	
	@RefTwin(KeyClass = String.class, ValueClass = TextureHandle.class)	
	public HashMap<String,TextureHandle> Other_S;
	
	@RefTwin(KeyClass = String.class, ValueClass = TextureHandle.class)	
	public HashMap<String,TextureHandle> Other_W;
}
