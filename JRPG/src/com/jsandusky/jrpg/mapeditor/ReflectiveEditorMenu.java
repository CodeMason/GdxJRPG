package com.jsandusky.jrpg.mapeditor;
import com.jsandusky.gdx.common.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g3d.decals.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.*;
import java.lang.reflect.*;
import com.jsandusky.util.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.jsandusky.gdx.util.*;

public abstract class ReflectiveEditorMenu extends Menu
{
	Stage stage;
	Dialog window;
	Skin skin;
	boolean isYesNo;
	
	Object target;
	Object[] targets;
	boolean doFinish = false;
	
	class CheckHandler extends ChangeListener {
		Object target_;
		Field field_;
		public CheckHandler(Field fld, Object target) {
			field_ = fld;
			target_ = target;
		}
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			CheckBox cb = CheckBox.class.cast(actor);
			if (cb != null) {
				try {
					field_.setBoolean(target_, cb.isChecked());
				} catch (Exception ex) {
				}
			}
		}
	};
	
	class TextHandler implements TextField.TextFieldListener
	{
		Field field_;
		Object target_;
		public TextHandler(Field fld, Object target) {
			field_ = fld;
			target_ = target;
		}
		public void keyTyped(TextField textField, char p2)
		{
			if (p2 == '\n')
				textField.getOnscreenKeyboard().show(false);
			else {
				try {
					if (field_.getType() == int.class) {
						field_.set(target_,Integer.parseInt(textField.getText()));
					} else if (field_.getType() == float.class) {
						field_.set(target_,Float.parseFloat(textField.getText()));
					} else if (field_.getType() == String.class) {
						field_.set(target_,textField.getText());
					} else if (field_.getType() == double.class) {
						field_.set(target_,Double.parseDouble(textField.getText()));
					}
				} catch (Exception ex) {
					
				}
			}
		}
	};
	
	public ReflectiveEditorMenu(Object tgt, boolean isYesNo) {
		target = tgt;
		this.isYesNo = isYesNo;
		load();
	}
	
	public ReflectiveEditorMenu(Object[] tgt, boolean isYesNo) {
		targets = tgt;
		this.isYesNo = isYesNo;
		load();
	}
	
		
	void load() {
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		String title = target != null ? target.getClass().getSimpleName() : " tile settings";
		window = new Dialog("Edit " + title,skin) {
			@Override
			protected void result(Object value) {
				doFinish = Boolean.class.cast(value);
				IDriver.getActive().removeMenu(ReflectiveEditorMenu.this);	
			}
		};
		
		Table table = new Table();
		
		if (target != null) {
			populateObject(table,target);
		} else if (targets != null) {
			for (int i = 0; i < targets.length; ++i) {
				Label lbl = new Label(targets[i].getClass().getSimpleName(),skin);
				lbl.setColor(Color.GREEN);
				lbl.setAlignment(Align.center);
				table.row();
				table.add(lbl);
				populateObject(table,targets[i]);
			}
		}
		
		table.setFillParent(true);
		ScrollPane scroller = new ScrollPane(table,skin);
		scroller.setFillParent(true);
		scroller.setScrollingDisabled(true,false);
		scroller.setFadeScrollBars(true);
		window.getContentTable().row();
		window.getContentTable().add(scroller).expandX().expandY().fill();

		if (!isYesNo)
			window.button("Exit",true);
		else {
			window.button("Confirm",true);
			window.button("Cancel",false);
		}

		window.pack();
		stage.setViewport(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,true);

		window.setX(Gdx.graphics.getWidth()/4 - window.getWidth());
		window.setY(Gdx.graphics.getHeight()/2 - window.getHeight() - 25);
		
		window.pack();
		stage.addActor(window);

		((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(0,stage);
	}
	
	void populateObject(Table table, Object target) {
		if (target == null)
			return;
		for (Field fld : target.getClass().getDeclaredFields()) {
			Spite spite = fld.getAnnotation(Spite.class);
			if (spite != null) {
				if (fld.getType() != boolean.class){
					Label lbl = new Label(fld.getName(),skin);
					table.row();
					table.add(lbl);
				}
				
				try {
					if (fld.getType() == int.class) {
						TextField tf = new TextField(fld.get(target).toString(),skin);
						tf.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
						tf.setTextFieldListener(new TextHandler(fld,target));
						table.row();
						table.add(tf);
					} else if (fld.getType() == float.class) {
						TextField tf = new TextField(fld.get(target).toString(),skin);
						tf.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
						tf.setTextFieldListener(new TextHandler(fld,target));
						table.row();
						table.add(tf);
					} else if (fld.getType() == double.class) {
						TextField tf = new TextField(fld.get(target).toString(),skin);
						tf.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
						tf.setTextFieldListener(new TextHandler(fld,target));
						table.row();
						table.add(tf);
					} else if (fld.getType() == String.class) {
						TextField tf = new TextField(fld.get(target).toString(),skin);
						tf.setTextFieldListener(new TextHandler(fld,target));
						table.row();
						table.add(tf);					
					} else if (fld.getType() == boolean.class) {
						CheckBox cb = new CheckBox(fld.getName(),skin);
						cb.setChecked(fld.getBoolean(target));
						cb.addListener(new CheckHandler(fld,target));
						table.row();
						table.add(cb);
					}
				} catch (Exception ex) {
					
				}
			}
		}
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
		stage.act();
		stage.draw();
	}

	public void dispose()
	{
		if (doFinish && target != null)
			onFinish(target);
		else if (doFinish && targets != null)
			onFinish(targets);
		
		((InputMultiplexer)Gdx.input.getInputProcessor()).removeProcessor(stage);
		stage.dispose();
		skin.dispose();
	}
	
	public abstract void onFinish(Object obj);
}
