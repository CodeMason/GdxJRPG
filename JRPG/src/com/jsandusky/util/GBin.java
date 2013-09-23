package com.jsandusky.util;

import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;


/*
 * GBin<T>
 * A grid of bins or buckets which contains objects in in cells based
 * on their positions
 * 
 * Use for collision broad-phase, networking, AI reasoning
 * 
 * Usage will need to tune the dimensions
 * No point to space sort if iterating over 1024x1024 cells
 */
public class GBin<T extends Object> {
	static class Bucket {
		public ArrayList contents;
		Vector2 min = new Vector2(), max = new Vector2();
	}
	Bucket[][] buckets;
	Vector2 min, max;
	int dim;
	public GBin(int dim, Vector2 min, Vector2 max) {
		buckets = new Bucket[dim][dim];
		this.dim = dim;
		this.min = min; this.max = max; 
		float incX = (max.x - min.x) / dim;
		float incY = (max.y - min.y) / dim;
		for (int x = 0; x < dim; ++x) {
			for (int y = 0; y < dim; ++y) {
				buckets[x][y].contents = new ArrayList<T>(24); //approximate guess
				buckets[x][y].min.x = min.x + (x * incX);
				buckets[x][y].min.y = min.y + (y * incY);
				buckets[x][y].max.x = buckets[x][y].min.x + incX; 
				buckets[x][y].max.y = buckets[x][y].max.y + incY;
			}
		}
	}
	
	public void add(T obj, Vector2 pos) {
		final int x = (int) ((pos.x - min.x) / dim);
		final int y = (int) ((pos.y - min.y) / dim);
		buckets[x][y].contents.add(obj);
	}
	
	public ArrayList<T> getCellContents(int x, int y) {
		return buckets[x][y].contents;
	}
	
	public int dim() {return dim;}
	
	public void clear() {
		for (int x = 0; x < dim; ++x)
			for (int y = 0; y < dim; ++y)
				buckets[x][y].contents.clear();
	}
}
