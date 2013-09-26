package com.jsandusky.jrpg.map;
import com.jsandusky.gdx.common.*;
import com.badlogic.gdx.graphics.g2d.Sprite;

//regular old standard texture tile
public class BasicTile extends Tile
{
	TextureHandle texture;
	
	BasicTile(TextureHandle texture) {
		this.texture = texture;
	}
	
	BasicTile() { //for serialization
		
	}
	
	public void setTexture(TextureHandle tex) {
		this.texture = tex;
		if (sprite == null) {
			sprite = new Sprite(texture.region);
		} else {
			sprite.setRegion(texture.region);
		}
	}

	public void loadResources(Resources res)
	{
		if (texture != null) {
			texture.load(res.getImageCache());
			sprite = new Sprite(texture.region);
		}
	}

	public TileSettings getSettings()
	{
		return null;
	}

	public void setSettings(TileSettings settings)
	{
	}
	
	public Tile cpy() {
		BasicTile ret = new BasicTile();
		ret.setTexture(texture);
		ret.ShadowMask = ShadowMask;
		ret.db = db;
		ret.gs = gs;
		return ret;
	}
}
