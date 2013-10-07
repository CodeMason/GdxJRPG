package com.jsandusky.drt;
import java.util.ArrayList;
import java.io.Serializable;

public class Character implements Serializable
{
//This is used for the animations
	public Animation Base;
	public ArrayList<Animation> Animations = new ArrayList<Animation>();
}
