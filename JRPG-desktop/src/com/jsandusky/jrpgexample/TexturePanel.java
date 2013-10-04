package com.jsandusky.jrpgexample;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jsandusky.drt.AnimEditorApp;
import com.jsandusky.gdx.common.PixmapTextureAtlas;

/*
 * Shows the textures available to the animation editor
 */
public class TexturePanel extends JPanel {
	AnimEditorApp app;
	PixmapTextureAtlas atlas;
	
	HashMap<String,BufferedImage> images = new HashMap<String,BufferedImage>();
	
	public TexturePanel(AnimEditorApp app) {
		super();
		this.app = app;
	}
	
	public void update() {
		images.clear();
		ArrayList<String> tex = getTextures();
		for (String tr : tex) {
			Pixmap pm = atlas.createPixmap(tr);
			
			BufferedImage img = new BufferedImage(pm.getWidth(),pm.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
			for (int x = 0; x < pm.getWidth(); ++x) {
				for (int y = 0; y < pm.getHeight(); ++y) {
					int pix = pm.getPixel(x, y);
					img.setRGB(x, y, pix);
				}
			}
			images.put(tr, img);
		}
	}
	
	public ArrayList<String> getTextures() {
		return app.getTextures();
	}
	
	@Override
	public Dimension getPreferredSize() {
		Dimension ret = new Dimension();
		for (BufferedImage bi : images.values()) {
			ret.height += bi.getHeight();
			ret.width = Math.max(ret.width, bi.getWidth());
		}
		return ret;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		int y = 0;
		for (String str : images.keySet()) {
			BufferedImage bi = images.get(str);
			g2.drawImage(bi,0,y,bi.getWidth(),bi.getHeight(),null);
			y += bi.getHeight();
		}
	}
}
