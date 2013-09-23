package com.jsandusky.gdx.common;
import com.badlogic.gdx.audio.Sound;

public class SoundHandle {
	
	public SoundHandle(String name, long id, Sound snd) {
		Name = name;
		ID = id;
		Snd = snd;
	}
	public String Name;
	public long ID;
	public Sound Snd;
	public float CutOff = Float.POSITIVE_INFINITY;
	
	public void play() {
		Snd.play(Volume);
	}
	
	public static float Volume = 1.0f;
}
