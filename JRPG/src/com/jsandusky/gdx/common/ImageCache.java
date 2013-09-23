package com.jsandusky.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import java.util.*;

public class ImageCache
{
	ArrayList<AtlasWrapper> atlases = new ArrayList<AtlasWrapper>();
    
	public Object getRes(String type, String id)
	{
		if (type == "texture") {
			for (AtlasWrapper a : atlases) {
				TextureHandle reg = a.findRegion(id);
				if (reg != null)
					return reg;
			}
		} else if (type == "sequence") {
			for (AtlasWrapper a : atlases) {
				TextureHandle regions = a.findRegions(id);
				if (regions != null)
					return regions;
			}
		}
		return null;
	}

	public void loadRes(String name, String info)
	{
        AtlasWrapper newAt = new AtlasWrapper(name, new TextureAtlas(Gdx.files.internal(info),Gdx.files.internal(info.substring(0, info.lastIndexOf("/")))));
        atlases.add(newAt);
	}
	public void unload(String name) {
		for (int i = 0; i < atlases.size(); ++i) {
			if (atlases.get(i).name == name) {
				atlases.remove(i);
				return;
			}
		}
	}
      
    public TextureHandle getTexture (String name) {  
        for (AtlasWrapper at : atlases) {
            TextureHandle ret = at.findRegion(name);
            if (ret != null)
                return ret;
        } return null;
    }
	
	public TextureHandle getTexture(String atlas, String name) {
		for (AtlasWrapper at : atlases) {
			if (at.name.equals(name)) {
				return at.findRegion(name);
			}
		} return null;
	}
    
    public TextureHandle getTexture(long atlasID, int id) {
        for (AtlasWrapper atlas : atlases) {
            if (atlas.id == atlasID)
                return atlas.findRegion(id);
        } return null;
    }
    
    public TextureHandle getFrames(String name) {
        for (AtlasWrapper atlas : atlases) {
            TextureHandle ret = atlas.findRegions(name);
            if (ret != null)
                return ret;
        } return null;
    }
	
	public ArrayList<TextureHandle> getAll(String atlas) {
		for (AtlasWrapper at : atlases) {
			if (at.name.equals(atlas)) {
				return at.getAll();
			}
		}
		return null;
	}
}
