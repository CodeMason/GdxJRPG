package com.jsandusky.shooter;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.jsandusky.jrpg.EngineSetup;
import com.jsandusky.jrpg.JRPGApplication;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        cfg.useWakelock = true;
        
        initialize(new JRPGApplication(new EngineSetup(EngineSetup.BattleSystem.DQ)),cfg);
        //initialize(new Myth(null, null), cfg);
    }
}
