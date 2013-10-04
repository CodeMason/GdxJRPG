package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.jsandusky.gdx.common.TextureHandle;
import com.jsandusky.gdx.common.SoundHandle;
import java.util.ArrayList;
import com.jsandusky.util.Ref;

public class Frame implements Serializable
{
	@Ref(cl = FrameData.class)
	public ArrayList<FrameData> FrameData = new ArrayList<FrameData>();
	
	public class FrameData implements Serializable {
		public transient Sprite sprite; //just cache the fucker
		public TextureHandle Texture;
		public SoundHandle Sound;
		public Vector2 Offset;
		public float Scale = 1f;
		public float Opacity = 1f;
		public float Rotation = 0f;
		public boolean FlippedHorizontal = false;
	}
}
