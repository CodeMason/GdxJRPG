package com.jsandusky.jrpg.mapeditor;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.jsandusky.gdx.common.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.badlogic.gdx.utils.Scaling;
import com.jsandusky.dungeon.*;
import com.jsandusky.gdx.util.*;
import com.jsandusky.jrpg.mapeditor.MessageBox.ButtonAction;
import com.jsandusky.jrpg.map.*;
import com.jsandusky.jrpg.model.*;
import com.jsandusky.jrpg.ui.*;

public class EditorUI extends Menu
{
	Stage stage;
	Skin skin;
	Button undo;
	Button redo;
	Button showPalette;
	Button newMap;
	Button openMap;
	Button saveMap;
	Button exitEditor;
	Button mapInfo;
	Button resize;
	Button randomize;
	Button help;
	Image img;
	Window window;

	public boolean paintMode;
	public boolean rotateMode;
	
	Editor editor;
	Database source;
	
	public EditorUI(Editor edit, Database src) {
		//??edit.map.getResources().getMusic().stop();
		source = src;
		editor = edit;
		stage = new Stage();
		((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(0,stage);
		stage.setViewport(800,480,true);
		
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		window = new Window("Tools",skin);
		Button.ButtonStyle checkable = new Button.ButtonStyle();
		
		
		exitEditor = new TextButton("Exit",skin);
		exitEditor.addListener(new ChangeListener() {
		public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
					if (EditorUI.this.editor.hasChanges()) {
						IDriver.getActive().pushMenu(new SaveDialog(EditorUI.this.editor.map));
					} else {
						//IDriver.getActive().setMenu(new MainMenu(EditorUI.this.editor.map.getResources(), EditorUI.this.source));
						//EditorUI.this.source.stopStates();
						/*IDriver.getActive().clearMenus();
						Json json = new Json();
						json.setOutputType(OutputType.json);
						String jsonStr = json.prettyPrint(EditorUI.this.editor.map);
						editor.saveToFile(EditorUI.this.editor.map.getMission().MissionName);*/
						
					}
				}
		});
		newMap = new TextButton("New",skin);
		newMap.addListener(new ChangeListener() {
		public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
					if (EditorUI.this.editor.hasChanges()) {
						//Prompt save
					} else {
						EditorUI.this.editor.map = new GameMap(EditorUI.this.editor.map.getResources());
						EditorUI.this.editor.map.resize(32,32);
						EditorUI.this.editor.map.initialize();
						EditorUI.this.editor.clearChanges();
						EditorUI.this.editor.clearUndoRedo();
						EditorUI.this.editor.map.setDrawGridLines(true);
						EditorUI.this.editor.map.loadTileImages();
					}
				}
		});
		openMap = new TextButton("Open",skin);
		openMap.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
				
			}
		});
		saveMap = new TextButton("Save",skin);
		saveMap.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
			
			}
		});
		undo = new TextButton("Undo",skin);
		undo.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
			
			}
		});
		redo = new TextButton("Redo",skin);
		redo.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
		
			}
		});
		showPalette = new TextButton("Tile Palette",skin);
		showPalette.addListener(new ChangeListener() {
		public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor)
			{
				if (IDriver.getActive().getMenu(TilePickerUI.class) != null) {
					IDriver.getActive().removeMenu(TilePickerUI.class);
				} else {
					IDriver.getActive().pushMenu(new TilePickerUI(EditorUI.this.editor,EditorUI.this.editor.map.getResources(),"tiles"));
				}
			}			
		});
		mapInfo = new TextButton("Map Info",skin);
		mapInfo.addListener(new ChangeListener() {
		public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
				if (IDriver.getActive().getMenu(MapInfoUI.class) != null) {
					IDriver.getActive().removeMenu(MapInfoUI.class);
				} else {
					IDriver.getActive().pushMenu(new MapInfoUI(EditorUI.this.editor.map));
				}
			}
		});
		resize = new TextButton("Resize Map",skin);
		resize.addListener(new ChangeListener() {
		public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
				
				IDriver.getActive().pushMenu(new ReflectiveEditorMenu(new ResizeInfo(EditorUI.this.editor.map.getWidth(),EditorUI.this.editor.map.getHeight()),true) {
					public void onFinish(Object obj) {
						if (obj instanceof ResizeInfo) {
							ResizeInfo info = (ResizeInfo)obj;							
							EditorUI.this.editor.map.resize(info.Width, info.Height);
						}
					}
				});
			}
		});
		randomize = new TextButton("Gen Rand",skin);
		randomize.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
				IDriver.getActive().pushMenu(new ReflectiveEditorMenu(new RandomGenerator(),true) {
					public void onFinish(Object ret) {
						if (ret instanceof RandomGenerator) {
							/*RandomGenerator rand = (RandomGenerator)ret;
							if (rand != null) {
								RandomDungeon dung = new RandomDungeon(rand.Width,rand.Height,6,6,rand.Complexity);
								EditorUI.this.editor.map = new GameMap(dung,"",EditorUI.this.editor.map.getResources());
								EditorUI.this.editor.map.setDrawGridLines(true);
								EditorUI.this.editor.map.initialize();
								EditorUI.this.editor.map.loadTileImages();
							}*/
						}
					}
				});
			}
		});
		help = new TextButton("Help",skin);
		help.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
				//???EditorUI.this.source.Platform.doEditorManual();
			}
		});
		
		img = new Image();
		img.setVisible(false);
		
		//add all the buttons to edit toolbar
		window.row();
		window.setMovable(false);
		window.setPosition(0,Gdx.graphics.getHeight()-50);
		window.setHeight(Gdx.graphics.getHeight()/6);
		window.add(exitEditor);
		window.add(newMap);
		window.add(openMap);
		window.add(saveMap);
		window.add(undo);
		window.add(redo);
		window.add(showPalette);
		window.add(mapInfo);
		window.add(resize);
		window.add(randomize);
		window.add(help);
		window.add(img);
		window.pack();
		stage.addActor(window);
	}

	public boolean routeTouchDown(float x, float y, int ptr)
	{
		return false;
	}

	public boolean routeTouchUp(float x, float y, int ptr)
	{
		// TODO: Implement this method
		return false;
	}

	public boolean routeTouchDrag(float x, float y, int ptr)
	{
		// TODO: Implement this method
		return false;
	}

	public void draw(Camera cam, Camera realCam, DecalBatch dBatch, SpriteBatch sBatch, BitmapFont bmf)
	{
		//enable disable the undo/redo buttons as needed
		if (editor.currentTexture != null) {
			TextureRegionDrawable draw = (TextureRegionDrawable)img.getDrawable();
			if (draw == null || draw.getRegion() != editor.currentTexture.region) {
				TextureRegionDrawable drawer = new TextureRegionDrawable(editor.currentTexture.region);
				img.setDrawable(drawer);
			}
			img.setVisible(true);
			img.setAlign(Align.top);
			//img.setScale(0.5f);
			img.getDrawable().setMinHeight(32);
			img.getDrawable().setMinWidth(32);
			window.pack();
		} else {
			img.setVisible(false);
			window.pack();
		}
		if (editor.canUndo())
			undo.setDisabled(false);
		else
			undo.setDisabled(true);
		if (editor.canRedo())
			redo.setDisabled(false);
		else
			redo.setDisabled(true);
			
		if (editor.getMode() == Editor.Mode.Paint) {
			window.setTitle("IV Map Editor: PAINT MODE");
		} else if (editor.getMode() == Editor.Mode.Erase) {
			window.setTitle("IV Map Editor: ERASER MODE");
		} else if (editor.getMode() == Editor.Mode.Rotate){
			window.setTitle("IV Map Editor: ROTATE MODE");
		} else if (editor.getMode() == Editor.Mode.Settings) {
			window.setTitle("IV Map Editor: SETTINGS EDIT MODE");
		} else if (editor.getMode() == Editor.Mode.Pieces) {
			window.setTitle("IV Map Editor: PIECES EDIT MODE");
		} else if (editor.getMode() == Editor.Mode.Special) {
			window.setTitle("IV Map Editor: SPECIAL PAINT MODE");
		} else {
			window.setTitle("IV Map Editor");
		}
		
		stage.act();
		stage.draw();
	}

	public void dispose()
	{
		((InputMultiplexer)Gdx.input.getInputProcessor()).removeProcessor(stage);
		stage.dispose();
		skin.dispose();
	}
}
