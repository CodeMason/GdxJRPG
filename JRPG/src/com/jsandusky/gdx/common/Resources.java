package com.jsandusky.gdx.common;

public class Resources {
	ImageCache images = new ImageCache();
	Sounds sounds = new Sounds();
	
	public Sounds getSounds() {
		return sounds;
	}
	
	public ImageCache getImageCache() {
		return images;
	}
}
