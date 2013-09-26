package com.jsandusky.jrpg.model;
import com.jsandusky.gdx.common.TextureHandle;
import java.util.HashMap;

public class CharacterSet extends Base
{
	public TextureHandle Walk_N;
	public TextureHandle Walk_S;
	public TextureHandle Walk_E;
	public TextureHandle Walk_W;
	
	public HashMap<String,TextureHandle> Other_N;
	public HashMap<String,TextureHandle> Other_E;
	public HashMap<String,TextureHandle> Other_S;
	public HashMap<String,TextureHandle> Other_W;
}
