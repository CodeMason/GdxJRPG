package com.jsandusky.gdx.common;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.Vector3;

public class BillboardSprite implements IDraw {
	Vector3 wpos;
	Vector3 spos;
	TextureRegion skin_;
	
	public BillboardSprite() {
	}
	
	public Vector3 project(Camera cam) {
		spos = new Vector3(wpos);
		cam.project(spos);
		return spos;
	}

	@Override
	public void draw(Camera camera, Camera real, DecalBatch db, SpriteBatch sb, BitmapFont bmf) {
		Decal d = Decal.newDecal(skin_);
		d.setPosition(wpos.x, wpos.y, wpos.z);
		d.lookAt(camera.position, camera.up);
		db.add(d);
	}
}
