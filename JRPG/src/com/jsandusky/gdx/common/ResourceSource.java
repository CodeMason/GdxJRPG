package com.jsandusky.gdx.common;
import java.util.*;

public abstract class ResourceSource
{
	protected static ArrayList<ResourceSource> sources = new ArrayList<ResourceSource>();
	
	public abstract Object getRes(String resourceType, String id);
	public abstract boolean loadRes(String resourceType, String info);
	
	public static void loadResource(String resourceType, String info) {
		for (ResourceSource src : sources) {
			if (src.loadRes(resourceType,info))
				return;
		}
	}
	
	public static Object getResource(String resourceType, String id) {
		for (ResourceSource src : sources) {
			Object ret = src.getRes(resourceType, id);
			if (ret != null)
				return ret;
		}
		return null;
	}
}
