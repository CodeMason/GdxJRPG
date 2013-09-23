package com.jsandusky.gdx.util;

import com.jsandusky.util.Spite;

public abstract class DataObject {
	public int _dbId;
	@Spite(group="Basic", tip="")
	public String _TagName;
	
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	public String getTagName() {return _TagName;}
	
	//Freestanding objects are not deleted as a result of a parent's deletion
	public boolean freestanding() {return false;}
}
