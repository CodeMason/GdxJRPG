package com.jsandusky.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;

public class Plane implements IDraw {
	Mesh mesh;
	boolean trans;
	public float panXRate = 0f;
	public float panYRate = 0f;
	int panInterval = 0;
	Texture tex;
	float[] verts;
	public Plane(float z, float texScale, boolean transparent, Texture tex) {
		trans = transparent;
		this.tex = tex;
		
		mesh = new Mesh(true, 4, 0, 
	    		new VertexAttribute(VertexAttributes.Usage.Position,3, "a_position"),
	    		new VertexAttribute(VertexAttributes.Usage.ColorPacked, 4, "a_color"),
	    		new VertexAttribute(VertexAttributes.Usage.TextureCoordinates,2, "a_texCoords"));
		verts = new float[] {
                -300f, z, -300f, Color.toFloatBits(1, 1, 1, 0.5f), 0, 10 * texScale,      // bottom left
                300f, z, -300f, Color.toFloatBits(1, 1, 1, 0.5f), 10 * texScale, 10 * texScale,       // bottom right
                300f, z, 300f, Color.toFloatBits(1, 1, 1, 0.5f), 10 * texScale, 0,        // top right
                -300f, z, 300f, Color.toFloatBits(1, 1, 1, 0.5f),  0, 0};
        mesh.setVertices(verts);     // top left
	}
	
	public Plane(float z, float texScale, float alpha, boolean transparent, Texture tex) {
		trans = transparent;
		this.tex = tex;
		
		mesh = new Mesh(true, 4, 0, 
	    		new VertexAttribute(VertexAttributes.Usage.Position,3, "a_position"),
	    		new VertexAttribute(VertexAttributes.Usage.ColorPacked, 4, "a_color"),
	    		new VertexAttribute(VertexAttributes.Usage.TextureCoordinates,2, "a_texCoords"));
		verts = new float[] {
                -300f, z, -300f, Color.toFloatBits(1, 1, 1, alpha), 0, 10 * texScale,      // bottom left
                300f, z, -300f, Color.toFloatBits(1, 1, 1, alpha), 10 * texScale, 10 * texScale,       // bottom right
                300f, z, 300f, Color.toFloatBits(1, 1, 1, alpha), 10 * texScale, 0,        // top right
                -300f, z, 300f, Color.toFloatBits(1, 1, 1, alpha),  0, 0};
        mesh.setVertices(verts);     // top left
	}
	
	@Override
	public void draw(Camera cam, Camera real, DecalBatch db, SpriteBatch sb, BitmapFont bmf) {
		tex.bind(0);
		Gdx.gl10.glEnable(GL10.GL_TEXTURE_2D);
		if (trans) {
			Gdx.gl10.glEnable(GL10.GL_BLEND);
			Gdx.gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		}
		GL10 gl = Gdx.graphics.getGL10(); 
		if (panXRate > 0 || panYRate > 0) {
			gl.glPushMatrix();
			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glTranslatef(panXRate*panInterval, panYRate*panInterval, 1);
			++panInterval;
		}			
	    mesh.render(GL10.GL_TRIANGLE_FAN);
	    if (trans) {
	    	Gdx.gl10.glDisable(GL10.GL_BLEND);
	    }
	    if (panXRate > 0 || panYRate > 0) {
	    	gl.glPopMatrix();
	    	gl.glLoadIdentity();
	    }
	}
}
