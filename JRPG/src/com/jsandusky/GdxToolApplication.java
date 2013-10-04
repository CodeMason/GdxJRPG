package com.jsandusky;
import com.badlogic.gdx.*;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import java.util.HashMap;
import com.jsandusky.gdx.common.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.*;

/* Provides utility methods for getting tool based software
- lists of files to where particular resources are to be expected
- playing sounds
- getting to texture data and texture atlases and such


Pixmap texture atlas is mostly meant for getting texture data
for tools
	- Ideally texture atlases will have a name for their purpose
	such as NAME_char.pack, NAME_tiles.pack, NAME_portraits.pack, NAME_system.pack
	NAME_mons.pack, NAME_fx.pack
*/
public abstract class GdxToolApplication implements ApplicationListener
{
	boolean loaded_;

	class LoadRequest {
		public String Atlas; 
		public String AtlasImage; //??
		
		public LoadRequest(String at, String img) {
			Atlas = at; AtlasImage = img;
		}
	}

	ArrayList< LoadRequest > loadRequests = new ArrayList<LoadRequest>();
	HashMap<String,PixmapTextureAtlas> LoadedRequests = new HashMap<String,PixmapTextureAtlas>();
	
	public synchronized void requestLoad(String file, String img) {
		loadRequests.add(new LoadRequest(file,img));
	}
	
	public synchronized void runLoads() {
		if (loadRequests.size() > 0) {
			for (int i = 0; i < loadRequests.size(); ++i) {
				PixmapTextureAtlas at = new PixmapTextureAtlas(Gdx.files.internal(loadRequests.get(i).Atlas),Gdx.files.internal(loadRequests.get(i).AtlasImage));
				
				LoadedRequests.put(loadRequests.get(i).Atlas,at);
				loadRequests.remove(i);
				--i;
			}
		}
	}
	
	public void freeLoad(String file) {
		if (LoadedRequests.containsKey(file)) {
			LoadedRequests.get(file).dispose();
			LoadedRequests.remove(file);
		}
	}
	
	//must be in our load pool
	public PixmapTextureAtlas getAtlas(String file) {
		PixmapTextureAtlas ret = LoadedRequests.get(file);;
		if (ret != null)
			return ret;
		return null;
	}
	
	public boolean gdxLoaded() {
		return loaded_;
	}
	
	public FileHandle getDataDirectory() {
		if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
			return Gdx.files.internal("./bin/data");
		} else {
			return Gdx.files.internal("data");
		}
	}
	
	public FileHandle getTextureDir() {
		if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
			return Gdx.files.internal("./bin/data/tex");
		} else {
			return Gdx.files.internal("data/tex");
		}
	}
	
	public FileHandle getSoundDir() {
		if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
			return Gdx.files.internal("./bin/data/snd");
		} else {
			return Gdx.files.internal("data/snd");
		}
	}
	
	public FileHandle getMusicDir() {
		if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
			return Gdx.files.internal("./bin/data/mus");
		} else {
			return Gdx.files.internal("data/mus");
		}
	}
	
	public FileHandle getMapDir() {
		if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
			return Gdx.files.internal("./bin/data/maps");
		} else {
			return Gdx.files.internal("data/maps");
		}
	}
}
