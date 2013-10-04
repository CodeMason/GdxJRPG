package com.jsandusky.gdx.common;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import java.util.ArrayList;
import java.util.HashMap;

public class ResourceManager implements Disposable
{
	AssetManager assets;
	HashMap<String,TextureAtlas> atlases;
	HashMap<String,Sound> sounds;
	HashMap<String,Music> music;
	HashMap<String,Texture> textures;
	
	public ResourceManager(AssetManager assets) {
		this.assets = assets;
	}
	
	public boolean load() {
		return assets.update();
	}
	
	public void loadAtlas(String pack) {
		assets.load(pack,TextureAtlas.class);
	}
	
	public void loadTexture(String file) {
		assets.load(file,Texture.class);
	}
	
	public void loadSound(String file) {
		assets.load(file,Sound.class);
	}
	
	public void loadMusic(String file) {
		assets.load(file,Music.class);
	}
	
	public TextureHandle findRegion(String region) {
		for (TextureAtlas at : atlases.values()) {
			TextureAtlas.AtlasRegion reg = at.findRegion(region);
			if (reg != null) {
				return new TextureHandle(region,reg.index,at.hashCode(),reg);
			}
		}
		return null;
	}
	
	public TextureHandle findRegions(String region) {
		for (TextureAtlas at : atlases.values()) {
			Array<TextureAtlas.AtlasRegion> reg = at.findRegions(region);
			if (reg != null)
				return new TextureHandle(region,-1,at.hashCode(),reg);
		}
		return null;
	}
	
	public SoundHandle getSound(String snd) {
		if (sounds.containsKey(snd))
			return new SoundHandle(snd, snd.hashCode(), sounds.get(snd));
		return null;
	}
	
	public float getProgress() {
		return assets.getProgress();
	}
	
	public AssetManager getAssets() {
		return assets;
	}
	
	public void catalog() {
		atlases.clear();
		textures.clear();
		sounds.clear();
		music.clear();
		
		for (String str : assets.getAssetNames()) {
			Class c = assets.getAssetType(str);
			if (c.equals(TextureAtlas.class)) {
				atlases.put(str,assets.get(str,TextureAtlas.class));
			} else if (c.equals(Texture.class)) {
				textures.put(str,assets.get(str,Texture.class));
			} else if (c.equals(Sound.class)) {
				sounds.put(str,assets.get(str,Sound.class));
			} else if (c.equals(Music.class)) {
				music.put(str,assets.get(str,Music.class));
			}
		}
	}
	
	public void clear() {
		assets.clear();
		atlases.clear();
		sounds.clear();
		music.clear();
		textures.clear();
	}
	
	@Override
	public void dispose() {
		assets.dispose();
	}
}
