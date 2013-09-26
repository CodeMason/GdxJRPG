package com.jsandusky.jrpg.map;
import com.jsandusky.jrpg.model.Item;
import com.jsandusky.jrpg.model.Reference;
import com.jsandusky.gdx.common.TextureHandle;
import com.jsandusky.gdx.common.*;

public class LockedDoorTile extends Tile
{

	public void loadResources(Resources res)
	{
	}

	public TileSettings getSettings()
	{
		return null;
	}

	public void setSettings(TileSettings settings)
	{
	}

	Reference<Item> KeyItem;
	TextureHandle Closed;
	TextureHandle Opened;
	public boolean IsClosed = true;
	
	public Tile cpy() {
		LockedDoorTile ret = new LockedDoorTile();
		ret.Closed = Closed;
		ret.Opened = Opened;
		ret.IsClosed = IsClosed;
		ret.ShadowMask = ShadowMask;
		ret.db = db;
		ret.gs = gs;
		return ret;
	}
}
