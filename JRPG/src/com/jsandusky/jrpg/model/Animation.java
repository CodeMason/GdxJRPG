package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;
import com.jsandusky.util.Ref;
/* this is more like a battle animation, like a spell or shit */
public class Animation extends Base implements Serializable
{
	public float RunningTime = 5f;
	@Ref(cl = Frame.class)
	public ArrayList<Frame> Frames = new ArrayList<Frame>();
}
