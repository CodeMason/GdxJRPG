package com.jsandusky.drt;
import com.badlogic.gdx.graphics.*;
import java.util.ArrayList;

public class ToolsFuncs
{
	AnimEditorApp app;
	Bone selectedBone;
	Keyframe currentKey;
	public ToolsFuncs(AnimEditorApp app) {
		
	}
	public void loadTexture(String file) {
		//adds it as a TextureEntry
	}
	public void loadTextureAtlas(String map) {
		//adds all of the images as a TextureEntry
	}
	public ArrayList<TextureEntry> getTextureEntries() {
		return null;
	}
	public int getNumberOfFrames() {
		return 0;
	}
	public float getAnimSpeed() {
		return 0;
	}
	public void setAnimSpeed(float f) {
		
	}
	public Bone createBone(String name, TextureEntry tex) {
		return null;
	}
	public Bone createBone(String name, TextureEntry tex, String parent) {
		return null;
	}
	public void deleteBone(Bone b) {
		
	}
	public void insertFrame(Keyframe after) {
		
	}
	public void deleteFrame(Keyframe frame) {
		
	}
	public void setOnionSkinning(int backwards, int forwards) {
	}
}
