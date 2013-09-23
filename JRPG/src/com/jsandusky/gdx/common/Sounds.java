package com.jsandusky.gdx.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.*;

public class Sounds
{
	public boolean loadRes(String name, String info)
	{
		if (name.equalsIgnoreCase("sound")) {
			loadSound(info);
            return true;
		}
		return false;
	}

	HashMap<Long,SoundHandle> SoundList = new HashMap<Long,SoundHandle>();
	
	public SoundHandle loadSound (String filename) {
        long h = getSDBMHash(filename);
        if (SoundList.containsKey(h)) {
            return SoundList.get(h);
        }
		Sound snd = Gdx.audio.newSound(Gdx.files.internal("data/sfx/" + filename));
		SoundHandle ret = new SoundHandle(filename,h,snd);
        SoundList.put(h, ret);
        return ret;
	}
	
	public static void play(SoundHandle sound) {
		sound.Snd.play(1);
	}
	
	//Play only if within cutoff range
	public static void play(SoundHandle sound, Vector2 listener, Vector2 source) {
		if (listener.dst(source) < sound.CutOff)
			sound.Snd.play();
	}
	//Play only if within cutoff range
	public static void play(SoundHandle sound, Vector3 listener, Vector3 source) {
		if (listener.dst(source) < sound.CutOff)
			sound.Snd.play();
	}
    
    private static long getSDBMHash(String str) {
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }

        return hash;
    }
}
