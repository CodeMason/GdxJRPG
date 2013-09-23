package com.jsandusky.jrpg.model;
import java.io.Serializable;
import java.util.ArrayList;
/* this is more like a battle animation, like a spell or shit */
public class Animation extends Base implements Serializable
{
	public float RunningTime = 5f;
	public ArrayList<Frame> Frames = new ArrayList<Frame>();
}
