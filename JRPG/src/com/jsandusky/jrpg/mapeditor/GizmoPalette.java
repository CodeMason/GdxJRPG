package com.jsandusky.jrpg.mapeditor;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.*;

public class GizmoPalette
{
	Stage stage;
	Skin skin;
	
	public GizmoPalette() {
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
	}
}
