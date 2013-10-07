package com.jsandusky.jrpg.mapeditor;
import com.jsandusky.gdx.common.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.*;
import java.util.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.jsandusky.gdx.util.*;

public class MessageBox extends Menu
{
	Stage stage;
	Skin skin;
	Dialog dlg;
	
	String title_;
	String msg_;
	String btn1_;
	String btn2_;
	
	public static interface ButtonAction {
		public void perform();
	}
	
	ArrayList<ButtonAction> actions_ = new ArrayList<ButtonAction>();
	
	public MessageBox(String title, String msg, String btnOne, String btnTwo) {
		commonInit(title,msg);
		dlg.button(btnOne,0);
		dlg.button(btnTwo,1);
		dlg.setTitleAlignment(Align.left);
		dlg.setWidth(Gdx.graphics.getWidth()/4);
		commonEnd();
	}
	
	void commonInit(String title, String msg) {
		stage = new Stage();
		stage.setViewport(800,480,true);

		skin = new Skin(Gdx.files.internal("data/uiskin.json"));

		dlg = new Dialog(title,skin) {
			@Override
			protected void result(Object value) {
				if (MessageBox.this.actions_ == null || MessageBox.this.actions_.size() != 0) {
					int val = (Integer)value;
					if (MessageBox.this.actions_.size()-1 >= val)
						MessageBox.this.actions_.get(val).perform();
				}
				IDriver.getActive().removeMenu(MessageBox.this);
			}
		};
		Label lbl = new Label(msg,skin);
		dlg.getContentTable().add(lbl);
		dlg.getContentTable().pack();
	}
	
	public static void FYIMessage(String msg) {
		IDriver.getActive().pushMenu(new MessageBox("Information:",msg,"Close"));
	}
	
	public static void ERROR(String msg) {
		IDriver.getActive().pushMenu(new MessageBox("ERROR",msg,"Close"));
	}
	
	public static void Unimplemented(String msg) {
		IDriver.getActive().pushMenu(new MessageBox("Unimplemented",msg,"Close"));
	}
	
	public MessageBox(String title, String msg, String btn) {
		commonInit(title,msg);
		dlg.button(btn,1);
		commonEnd();
	}
	
	void commonEnd() {
		stage.addActor(dlg);
		dlg.setPosition(400-dlg.getWidth()/2, 480/2 - dlg.getHeight()/2);
		((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(0,stage);
	}
	
	public MessageBox addAction(ButtonAction action) {
		actions_.add(action);
		return this;
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
		sBatch.flush();
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
