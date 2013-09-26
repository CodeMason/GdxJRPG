package com.jsandusky.jrpg.map;
import com.badlogic.gdx.math.*;
import com.jsandusky.gdx.common.TextureHandle;
import com.jsandusky.gdx.common.Resources;

/* an overlay is something you can add to be drawn overtop 
of everything they are placed on a tile can be larger than one tile

use for lighting effects
*/
public class Overlay
{
	public Vector2 Position;
	public TextureHandle Texture;
	
	public void loadResources(Resources res) {
		if (Texture != null)
			Texture.load(res.getImageCache());
	}	
}
