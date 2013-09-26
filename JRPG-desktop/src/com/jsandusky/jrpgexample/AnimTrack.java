package com.jsandusky.jrpgexample;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class AnimTrack extends JPanel {
	
	Color keyColor;
	Color tickColor;
	Color keySelected;
	Color tickSelected;
	
	ArrayList<Tick> ticks;
	Tick selected;
	
	final int tickSpace = 1;
	final int tickWidth = 8;
	final int tickHeight = 12;
	Dimension calculatedSize;
	
	public static class Tick {
		public Rectangle area;
		public Object KeyFrame;
	}
	
	public AnimTrack() {
		tickColor = Color.BLACK;
		keyColor = Color.WHITE;
		tickSelected = Color.ORANGE;
		keySelected = Color.BLUE;
		
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AnimTrack tr = AnimTrack.this;
				for (Tick t : tr.ticks) {
					if (t.area.contains(arg0.getPoint())) {
						selected = t;
						return;
					}
				}
				selected = null;
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
	}
	
	public void setAnim(ArrayList li) {
		for (Object o : li) {
			
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		calcTicks();
		
		Graphics2D g2 = (Graphics2D)g;
		
		int x = tickSpace;
		for (Tick t : ticks) {
			if (t.KeyFrame != null) {
				if (t == selected)
					g2.setColor(keySelected);
				else
					g2.setColor(keyColor);
			} else {
				if (t == selected)
					g2.setColor(tickSelected);
				else
					g2.setColor(tickColor);
			}
			g2.drawRect(x, 0, t.area.width, t.area.height);
			x += tickWidth;
			x += tickSpace;
		}
	}
	
	
	@Override
	public Dimension getPreferredSize() { //double duty
		Dimension ret = new Dimension();
		
		ret.height = tickHeight + 4;
		ret.width += tickSpace;
		for (Tick t : ticks) {
			ret.width += tickWidth + tickSpace;
		}
		return ret;
	}
	
	void calcTicks() {
		int x = tickSpace;
		for (Tick t : ticks) {
			t.area = new Rectangle(x,0,tickWidth,tickHeight);
			x += tickWidth + tickSpace;
		}
	}
}
