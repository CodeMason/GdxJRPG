package com.jsandusky.jrpg.map;
import com.badlogic.gdx.math.*;
import com.jsandusky.gdx.common.SoundHandle;

//Teleports to a point
public class GoToEvent extends Event
{
	public String GoToMap; //if blank then on this map
	public GridPoint2 GoToSquare;
	public SoundHandle PlaySound; //plays the sound when doing the event
}
