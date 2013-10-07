package com.jsandusky.jrpg.scripting;

//System functions that can be called from FScript
public class Script
{
	public void startEncounter(String teamName) {
		
	}
	
	public void showMessage(String msg) {
		
	}
	
	public void removeFromParty(String name) {
		
	}
	
	public void addToParty(String name) {
		
	}
	
	public void startDialog(String name) {
		
	}
	
	public void switchMusic(String name) {
		
	}
	
	public void playSound(String name) {
		
	}
	
	public void removeTile(int x, int y, int layer) {
		
	}
	
	public void changeTileTexture(int x, int y, int layer, String tex) {
		
	}
	
//From the WorldState
	public void setStateString(String name, String value) {
		
	}
	
	public void setStateInt(String name, int value) {
		
	}
	
	public String getStateString(String name) {
		return "";
	}
	
	public int getStateInt(String name) {
		return 0;
	}
	
	//plays an animation somewhere on the map
	//same animations as the battle anims
	public void playAnimation(int x, int y, String name) {
		
	}
	
	public void teleportTo(Object actor, int x, int y) {
		
	}
	
	public void goTo(Object actor, int x, int y) {
		
	}
	
	public void goToAndFace(Object actor, int x, int y) {
		
	}
}
