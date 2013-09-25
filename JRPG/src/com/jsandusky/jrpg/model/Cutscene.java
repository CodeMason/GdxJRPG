package com.jsandusky.jrpg.model;
import java.io.Serializable;
import com.jsandusky.gdx.common.TextureHandle;
import java.util.ArrayList;

/* more like moving images and text showing up 
background images,
foreground images - moveable by script
*/
public class Cutscene extends Base implements Serializable
{
	public enum Entrance {
		Instant,
		SlideInFromLeft,
		SlideInFromRight,
		SlideInFromTop,
		SlideInFromBottom,
		FadeIn
	}
	public class Slide {
		public TextureHandle Image;
		public Entrance EnterVia;
		public float EnterSpeed;
		public boolean LeavePreviousSlide;
		
		public String Text;
	}
	
	public ArrayList<Slide> Slides = new ArrayList<Slide>();
}
