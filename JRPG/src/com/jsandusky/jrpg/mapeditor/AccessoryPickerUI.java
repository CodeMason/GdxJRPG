package com.jsandusky.jrpg.mapeditor;
import com.jsandusky.gdx.common.*;
import com.jsandusky.gdx.util.Random;
import com.jsandusky.jrpg.map.*;
import com.jsandusky.jrpg.model.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import java.util.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.math.*;
import com.jsandusky.jrpg.mapeditor.Editor.SpecialPainter;

public class AccessoryPickerUI extends Menu
{
	Skin skin;
	Stage stage;
	Window window;
	ArrayList<Label> labels = new ArrayList<Label>();
	HashMap<Image,SpecialPainter> imageMap = new HashMap<Image,SpecialPainter>();
	HashMap<Label,SpecialPainter> labelMap = new HashMap<Label,SpecialPainter>();
	HashMap<Image,Label> labelLookup = new HashMap<Image,Label>();
	Editor editor;
	
	void setActiveLabel(Image img) {
		for (Image i : labelLookup.keySet()) {
			if (i == img) {
				labelLookup.get(i).setColor(Color.GREEN);
			} else {
				labelLookup.get(i).setColor(Color.WHITE);
			}
		}
	}
	
	public AccessoryPickerUI(Editor editor) {
		this.editor = editor;
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		window = new Window("Accessories",skin);
		
		SpecialPainter pieceSettings = new SpecialPainter() {
			@Override
			public void doPaint(Square sq) {
				/*if (sq.Pieces.size() > 0) {
					final Piece p = sq.Pieces.get(0);
					PieceSettings ps = p.getSettings();
					if (ps != null) {
						ReflectiveEditorMenu men = new ReflectiveEditorMenu(ps,true) {
							@Override
							public void onFinish(Object target) {
								p.setSettings((PieceSettings)target);
							}
						};
						IDriver.getActive().pushMenu(men);
					}
				}*/
			}
			@Override
			public void doEmpty(Vector3 pt, GameMap map) {
				
			}
		};		
		
		Table content = new Table(skin);
		Image img = null;
		
		ClickListener clickListener = new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Image img = (Image)event.getTarget();
				SpecialPainter st = AccessoryPickerUI.this.imageMap.get(img);
				AccessoryPickerUI.this.editor.setPainter(st);
				AccessoryPickerUI.this.setActiveLabel(img);
			}			
		};
		
		Label lbl;
/*
//GENERATOR
		img = new Image(new TextureRegionDrawable(editor.map.getResources().getImageCache().getTexture("gen_running").region));
		Label lbl = new Label("Generator",skin);
		imageMap.put(img,generator);
		labelMap.put(lbl,generator);
		labelLookup.put(img,lbl);
		img.addListener(clickListener);
		labels.add(lbl);
		content.row();
		content.add(img);
		content.add(lbl).expandX();
//RELIC
		lbl = new Label("Relic",skin);
		img = new Image(new TextureRegionDrawable(editor.map.getResources().getImageCache().getTexture("sys_defend").region));
		imageMap.put(img,relic);
		labelMap.put(lbl,relic);
		labelLookup.put(img,lbl);
		img.addListener(clickListener);
		labels.add(lbl);
		content.row();
		content.add(lbl).expandX();
		
//LOOT
		lbl = new Label("Loot",skin);
		img = new Image(new TextureRegionDrawable(editor.map.getResources().getImageCache().getTexture("sys_defend").region));
		imageMap.put(img,loot);
		labelMap.put(lbl,loot);
		labelLookup.put(img,lbl);
		img.addListener(clickListener);
		labels.add(lbl);
		content.row();
		content.add(lbl).expandX();

//BLOOD STAIN
		lbl = new Label("Blood Stain",skin);
		img = new Image(new TextureRegionDrawable(editor.map.getResources().getImageCache().getTexture("sys_blood_edge").region));
		labels.add(lbl);
		imageMap.put(img,bloodstain);
		labelMap.put(lbl,bloodstain);
		labelLookup.put(img,lbl);
		img.addListener(clickListener);
		content.row();
		content.add(img);
		content.add(lbl).expandX();
		
//CORPSE
		img = new Image(new TextureRegionDrawable(editor.map.getResources().getImageCache().getTexture("sys_dead_marine").region));
		lbl = new Label("Human Corpse",skin);
		imageMap.put(img,humcorpse);
		labelMap.put(lbl,humcorpse);
		labelLookup.put(img,lbl);
		img.addListener(clickListener);
		labels.add(lbl);
		content.row();
		content.add(img);
		content.add(lbl).expandX();
		
//ALIEN CORPSE
		img = new Image(new TextureRegionDrawable(editor.map.getResources().getImageCache().getTexture("sys_dead_stealer").region));
		lbl = new Label("Alien Corpse",skin);
		imageMap.put(img,aliencorpse);
		labelMap.put(lbl,aliencorpse);
		labelLookup.put(img,lbl);
		img.addListener(clickListener);
		labels.add(lbl);
		content.row();
		content.add(img);
		content.add(lbl).expandX();
		
//ENTRANCE/EXIT POINT
		lbl = new Label("Entrance Point",skin);
		img = new Image(new TextureRegionDrawable(editor.map.getResources().getImageCache().getTexture("sys_entrance").region));
		labels.add(lbl);
		imageMap.put(img,entrancePoint);
		labelMap.put(lbl,entrancePoint);
		labelLookup.put(img,lbl);
		img.addListener(clickListener);
		content.row();
		content.add(img);
		content.add(lbl).expandX();
		
//ENTRANCE?EXIT MARKER
		lbl = new Label("Entrance Marker",skin);
		img = new Image(new TextureRegionDrawable(editor.map.getResources().getImageCache().getTexture("sys_objective").region));
		imageMap.put(img,entExitMarker);
		labelMap.put(lbl,entExitMarker);
		labelLookup.put(img,lbl);
		img.addListener(clickListener);
		content.row();
		content.add(img);
		content.add(lbl).expandX();
//EXIT Point
		lbl = new Label("Exit Point",skin);
		img = new Image(new TextureRegionDrawable(editor.map.getResources().getImageCache().getTexture("sys_objective").region));
		imageMap.put(img,exitPoint);
		labelMap.put(lbl,exitPoint);
		labelLookup.put(img,lbl);
		img.addListener(clickListener);
		content.row();
		content.add(img);
		content.add(lbl).expandX();

*/
//ROTATE PIECE		
		/*lbl = new Label("Rotate Piece", skin);
		img = new Image(new TextureRegionDrawable(editor.map.getResources().getImageCache().getTexture("sys_turn").region));		
		imageMap.put(img,rotatePiece);
		labelMap.put(lbl,rotatePiece);
		labelLookup.put(img,lbl);
		img.addListener(clickListener);
		content.row();
		content.add(img);
		content.add(lbl).expandX();*/
		
//PIECE SETTINGS
		/*lbl = new Label("Piece Settings",skin);
		img = new Image(new TextureRegionDrawable(editor.map.getResources().getImageCache().getTexture("sys_repair").region));
		imageMap.put(img,pieceSettings);
		labelMap.put(lbl,pieceSettings);
		labelLookup.put(img,lbl);
		img.addListener(clickListener);
		content.row();
		content.add(img);
		content.add(lbl).expandX();*/
		
//GET TO POINT
		/*lbl = new Label("Get To Point",skin);
		img = new Image(new TextureRegionDrawable(editor.map.getResources().getImageCache().getTexture("sys_terminal").region));
		imageMap.put(img,getToPoint);
		labelMap.put(lbl,getToPoint);
		labelLookup.put(img,lbl);
		img.addListener(clickListener);
		content.row();
		content.add(img);
		content.add(lbl).expandX();*/
		
		
		ScrollPane sp = new ScrollPane(content,skin);
		sp.setFillParent(true);
		window.add(sp).expandX().expandY();
		window.setHeight(Gdx.graphics.getHeight()/2);
		window.setWidth(Gdx.graphics.getWidth()/6);
		window.setX(Gdx.graphics.getWidth()-window.getWidth());
		stage.addActor(window);
		((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(0,stage);
	}
	public boolean routeTouchDown(float x, float y, int ptr)
	{
		// TODO: Implement this method
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
		stage.act();
		stage.draw();
	}

	public void dispose()
	{
		stage.dispose();
		skin.dispose();
		((InputMultiplexer)Gdx.input.getInputProcessor()).removeProcessor(stage);
	}
	
}
