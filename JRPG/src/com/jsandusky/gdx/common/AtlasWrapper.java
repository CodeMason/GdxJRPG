package com.jsandusky.gdx.common;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

import java.util.*;

public class AtlasWrapper {
    public long id;
    public String name;
    TextureAtlas atlas;
    HashMap<String,TextureHandle> cachedSequences = new HashMap<String,TextureHandle>();
    HashMap<Integer,TextureHandle> cachedSingle = new HashMap<Integer,TextureHandle>();
    
    public AtlasWrapper(String name, TextureAtlas atlas) {
        id = getSDBMHash(name);
        this.name = name;
        this.atlas = atlas;
        for (AtlasRegion reg : atlas.getRegions()) {
        	//prebuild the single cache?
        }
	}    
    
    public TextureAtlas getAtlas() {
        return atlas;
    }
    
    public TextureHandle findRegion(String name) {
        AtlasRegion reg = atlas.findRegion(name);
        if (reg != null) {
            TextureHandle ret =  new TextureHandle(name, reg.index,id,reg);            
            if (!cachedSingle.containsKey(reg.index))
                cachedSingle.put(reg.index,ret);
            return ret;
        } return null;
    }
    
    public TextureHandle findRegion(String name, int sub) {
        AtlasRegion reg = atlas.findRegion(name,sub);
        if (reg != null) {
            TextureHandle h = new TextureHandle(name, reg.index, id, reg);
            if (!cachedSingle.containsKey(reg.index))
                cachedSingle.put(reg.index,h);
            return h;
        }
        return null;
    }
    
    public TextureHandle findRegion(int id) {
        if (cachedSingle.containsKey(id))
            return cachedSingle.get(id);
        TextureHandle ret = new TextureHandle("", id, this.id, atlas.getRegions().get(id));
        cachedSingle.put(id,ret);
        return ret;
    }
    
    public TextureHandle findRegions(String name) {
        if (cachedSequences.containsKey(name))
            return cachedSequences.get(name);
        Array<AtlasRegion> regions = atlas.findRegions(name);
        if (regions != null && regions.size > 0) {
            TextureHandle ret = new TextureHandle(name, 0, id, regions);
            cachedSequences.put(name,ret);
            return ret;
        } return null;
    }
	
	public ArrayList<TextureHandle> getAll() {
		Array<AtlasRegion> regions = atlas.getRegions();
		ArrayList<TextureHandle> textures = new ArrayList<TextureHandle>();
		for (AtlasRegion region : regions) {
			TextureHandle ret = new TextureHandle(region.name,region.index, id, region);
			textures.add(ret);
		}
		return textures;
	}
    
    private static long getSDBMHash(String str) {

        long hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }

        return hash;
    }
}
