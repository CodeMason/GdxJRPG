package com.jsandusky.jrpgexample;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jsandusky.drt.TestApp;
import com.jsandusky.jrpg.EngineSetup;
import com.jsandusky.jrpg.JRPGApplication;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Myth";
		cfg.useGL20 = false;
		cfg.width = 1024;
		cfg.height = 600;
		
		new LwjglApplication(new TestApp(), cfg);
		//new LwjglApplication(new JRPGApplication(new EngineSetup(EngineSetup.BattleSystem.DQ)), cfg);
	}
}
