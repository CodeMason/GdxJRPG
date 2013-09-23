package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.jsandusky.gdx.common.TextureHandle;
import com.jsandusky.gdx.common.SoundHandle;
import java.util.ArrayList;

public class Frame implements Serializable
{
	public ArrayList<FrameData> FrameData = new ArrayList<FrameData>();
	
	public class FrameData {
		public transient Sprite sprite;
		public TextureHandle Texture;
		public SoundHandle Sound;
		public Vector2 Offset;
		public float Scale = 1f;
		public float Rotation = 0f;
		public boolean FlippedHorizontal = false;
	}
}
