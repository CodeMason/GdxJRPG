package com.jsandusky.drt;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.io.Serializable;

public class TextureEntry implements Serializable
{
	public String TextureName; //for in application use
	public String ToolPath; //relative to the animation file's location
	public transient TextureRegion Region;
	public TextureBounds Bounds;
	
	public TextureEntry(TextureRegion region, TextureBounds bounds) {
		Region = region;
		Bounds = bounds;
	}
	
	TextureEntry() {
		
	}
}
