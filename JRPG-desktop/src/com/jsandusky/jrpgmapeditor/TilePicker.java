package com.jsandusky.jrpgmapeditor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jsandusky.drt.AnimEditorApp;
import com.jsandusky.gdx.common.PixmapTextureAtlas;

public class TilePicker extends JPanel {

	/**
	 * Create the panel.
	 */
	ArrayList<FileHandle> images = new ArrayList<FileHandle>();
	ArrayList<PixmapTextureAtlas> atlases = new ArrayList<PixmapTextureAtlas>();
	HashMap<String,BufferedImage> imageMap = new HashMap<String,BufferedImage>();
	
	public void load() {
		HashMap<String,TextureAtlas.AtlasRegion> regions = app.getAtlasRegions();
		ArrayList<PixmapTextureAtlas> atlas = app.getAtlases();
		
		for (PixmapTextureAtlas at : atlas) {
			TextureAtlas a = at.getAtlas();
			for (TextureAtlas.AtlasRegion region : a.getRegions()) {
				Pixmap pm = at.createPixmap(region.name);
				BufferedImage image = new BufferedImage(pm.getWidth(),pm.getHeight(),BufferedImage.TYPE_INT_ARGB);
				for (int x = 0; x < pm.getWidth(); ++x) {
					for (int y = 0; y < pm.getHeight(); ++y) {
						int pix = pm.getPixel(x, y);
						int alpha = pix;
						alpha = alpha << 24;
						int rgb = pix;
						rgb = rgb >> 8;
						image.setRGB(x, y, alpha | rgb);
					}
				}
				imageMap.put(region.name, image);
			}
		}
	}
	
	AnimEditorApp app;
	
	public TilePicker(AnimEditorApp app) {
		this.app = app;
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				TilePicker.this.load();
				TilePicker.this.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	@Override
	public Dimension getPreferredSize() {
		Dimension ret = new Dimension();
		
		for (BufferedImage i : imageMap.values()) {
			ret.width = Math.max(ret.width, i.getWidth());
			ret.height += i.getHeight();
		}
		
		return ret;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		int y = 0;
		for (String str : imageMap.keySet()) {
			BufferedImage bi = imageMap.get(str);
			g2.drawImage(bi,0,y,bi.getWidth(),bi.getHeight(),null);
			y += bi.getHeight();
		}
	}
}
