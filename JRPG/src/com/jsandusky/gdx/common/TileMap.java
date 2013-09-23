package com.jsandusky.gdx.common;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;

public class TileMap implements IDraw {
	public int xsize;
	public int ysize;
	protected int[] tile_map;
	protected TextureRegion[] tiles;
	float xPos = 0f;
	float yPos = 0f;
	
	public TileMap(float x, float y) {
		xPos = x;
		yPos = y;
	}
	
	public int getCell(int x, int y) {
		return tile_map[x + xsize * y];
	}

	@Override
	public void draw(Camera cam, Camera realCam, DecalBatch db, SpriteBatch sb, BitmapFont bmf) {
		for (int i = 0; i < xsize; ++i) {
			for (int j = 0; j < ysize; ++j) {
				int c = getCell(i,j);
				float xPos = this.xPos - (i * 32); //flips x
				float yPos = this.yPos + (j * 32);
				sb.draw(tiles[c], xPos, yPos);
			}
		}
	}
}
