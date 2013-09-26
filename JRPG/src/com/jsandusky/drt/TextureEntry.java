package com.jsandusky.drt;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureEntry
{
	public TextureRegion Region;
	public TextureBounds Bounds;
	
	public TextureEntry(TextureRegion region, TextureBounds bounds) {
		Region = region;
		Bounds = bounds;
	}
}
