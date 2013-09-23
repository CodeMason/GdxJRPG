package com.jsandusky.gdx.common;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Musica {
	public static float Volume = 1f;
	HashMap<Integer,Music> songs = new HashMap<Integer,Music>();
	Music playing = null;
	String strPlaying = "";
	
	public Music load(String filename) {
		long h = getSDBMHash(filename);
        if (songs.containsKey(h)) {
            return songs.get(h);
        }
		Music snd = Gdx.audio.newMusic(Gdx.files.internal("data/mus/" + filename));
        songs.put((int)h, snd);
        return snd;
	}
	
	public void play(String name, float volume) {
		if (volume <= 0)
			return;
		if (playing != null) {
			if (strPlaying.equals(name))
				return;
			playing.stop();
		}
		Music mus = load(name);
		strPlaying = name;
		mus.play();
		mus.setVolume(volume);
		mus.setLooping(true);
		playing = mus;
	}
	
	public void stop() {
		if (playing != null)
			playing.stop();
		strPlaying = "";
	}
	
	private static long getSDBMHash(String str) {
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }

        return hash;
    }
}
