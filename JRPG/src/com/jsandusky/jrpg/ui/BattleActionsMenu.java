package com.jsandusky.jrpg.ui;
import com.jsandusky.gdx.common.Menu;
import com.jsandusky.gdx.common.MenuNinePatch;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.decals.*;

public class BattleActionsMenu extends Menu
{
	public boolean routeTouchDown(float x, float y, int ptr)
	{
		return false;
	}

	public boolean routeTouchUp(float x, float y, int ptr)
	{
		return false;
	}

	public boolean routeTouchDrag(float x, float y, int ptr)
	{
		return false;
	}

	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
	}

	public void dispose()
	{
	}
}
