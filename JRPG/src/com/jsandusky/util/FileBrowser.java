package com.jsandusky.util;
import com.jsandusky.gdx.common.Menu;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.graphics.*;
import java.util.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.jsandusky.gdx.util.*;

/* Generic file picker */
public class FileBrowser extends Menu
{	
	String ext;
	FileHandle top;
	FileHandle current;
	FileHandle selected;
	Table content;	
	Dialog dlg;
	Skin skin;
	Stage stage;
	ArrayList<Table> tables = new ArrayList<Table>();
	Listener call_;
	
	public interface Listener {
		public void onFileChosen(FileHandle f);
	}
	
	public FileBrowser(Listener li, FileHandle start, String ext) {
		this.top = start;
		call_ = li;
		current = start;
		build();
	}
	
	public FileBrowser(Listener li, FileHandle top, FileHandle start, String ext) {
		this.top = top;
		current = start;
		call_ = li;
		current = start; //must be a folder
		
		build();
	}
	
	void build() {
		stage = new Stage();
		
		
		dlg = new Dialog("",skin) {
			@Override
			protected void result(Object val) {
				if (val.equals("select")) {
					if (FileBrowser.this.selected != null) {
						FileBrowser.this.call_.onFileChosen(FileBrowser.this.selected);
					}
					IDriver.getActive().removeMenu(FileBrowser.this);
				} else {
					IDriver.getActive().removeMenu(FileBrowser.this);
				}
			}
		};
		dlg.button("Select","select");
		dlg.button("Cancle","cancel");
		content = new Table(skin);
		
		populateList(content,current);
		
		ScrollPane sp = new ScrollPane(content);
		sp.setFillParent(true);
		dlg.getContentTable().add(sp).fillX().fillY().expandX();
		dlg.pack();
		stage.addActor(dlg);
	}
	
	public void populateList(Table t, FileHandle fh) {
		tables.clear();
		
		if (current != top) {
			t.row();
			Table up = new Table(skin);
			up.add("\\..");
			up.setTouchable(Touchable.enabled);
			t.add(up);
			up.addListener(new UpHandler(this, current.parent()));
		}
		
		for (FileHandle f : fh.list()) {
			if (!f.isDirectory()) {
				if (ext != null && ext.length() > 0 && !f.extension().equals(ext)) //ignore filtered extensions
					continue;
			}
			
			Table fl = new Table(skin);
			t.row();
			fl.add(f.name());
			fl.setTouchable(Touchable.enabled);
			if (f.isDirectory()) {
				fl.addListener(new DirHandler(this,f));
			} else {
				fl.addListener(new FileHandler(this,f,fl));
			}
			t.add(fl);
			tables.add(fl);
		}
	}
	
	public class DirHandler extends ActorGestureListener {
		FileHandle file;
		FileBrowser owner;
		
		public DirHandler(FileBrowser owned, FileHandle f) {
			file = f;
			owner = owned;
		}
		
		@Override
		public void tap(Actor a, float x, float y) {
			owner.content.clear();
			owner.current = file;
			populateList(owner.content,file);
			owner.content.pack();
			owner.dlg.pack();
		}
	};
	
	public class UpHandler extends ActorGestureListener {
		FileHandle file;
		FileBrowser owner;
		public UpHandler(FileBrowser owned, FileHandle cur) {
			file = cur;
		}
		@Override
		public void tap(Actor a, float x, float y) {
			owner.content.clear();
			owner.current = file;
			populateList(owner.content,file);
			owner.content.pack();
			owner.dlg.pack();
		}
	};
	
	public class FileHandler extends ActorGestureListener {
		FileHandle file;
		FileBrowser owner;
		Table t;
		public FileHandler(FileBrowser owned, FileHandle file, Table t) {
			owner = owned;
			this.file = file;
			this.t = t;
		}
		@Override
		public void tap(Actor a, float x, float y) {
			for (Table t : owner.tables)
				t.setColor(Color.WHITE);
			owner.selected = file;
			t.setColor(Color.GREEN);
			t.invalidate();
		}
	};
	
	public boolean routeTouchDown(float x, float y, int ptr){return false;}
	public boolean routeTouchUp(float x, float y, int ptr){return false;}
	public boolean routeTouchDrag(float x, float y, int ptr){return false;}
	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf){
		stage.act();stage.draw();
	}
	public void dispose(){
		stage.dispose();
		skin.dispose();
	}
}
