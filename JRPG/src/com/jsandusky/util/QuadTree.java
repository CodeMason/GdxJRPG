package com.jsandusky.util;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Pool;

public class QuadTree<Key extends Comparable<Key>, Value>  {
    private Node root;
    
    NodePool pool;
    private class NodePool extends Pool<Node> {
    	public NodePool() {
    		super(64);
    	}
		@Override
		protected Node newObject() {
			return new Node();
		}
    }
    
    public QuadTree() {
    	pool = new NodePool();
    }
    
    public void clear() {
    	root.clear();
    	pool.free(root);
    	root = null;
    }

    // helper node data type
    private class Node {
        Key x, y;              // x- and y- coordinates
        Node NW, NE, SE, SW;   // four subtrees
        Value value;           // associated data
        
        void clear() {
        	pool.free(NW);
			NW = null;
        	pool.free(NE);
			NE = null;
        	pool.free(SW);
			SW = null;
        	pool.free(SE);
			SE = null;
        }

        Node() {
        	
        }
        
        Node set(Key x, Key y, Value value) {
            this.x = x;
            this.y = y;
            this.value = value;
            return this;
        }
    } 
    		
    public void insert(Key x, Key y, Value value) {
        root = insert(root, x, y, value);
    }
    
    public void remove(Key x, Key y, Value value) {
    	remove(root,new Interval<Key>(x,y));
    }
    
    private void remove(Node h, Interval<Key> rect) {
    	if (h == null) 
        	return;
        if (h.x == rect.low && h.y == rect.high) {
        	h.value = null;
        	return;
            //System.out.println("    (" + h.x + ", " + h.y + ") " + h.value);
        }
        if ( less(rect.low, h.x) &&  less(rect.high, h.y)) 
        	remove(h.SW, rect);
        if ( less(rect.low, h.x) && !less(rect.high, h.y)) 
        	remove(h.NW, rect);
        if (!less(rect.low, h.x) &&  less(rect.high, h.y)) 
        	remove(h.SE, rect);
        if (!less(rect.low, h.x) && !less(rect.high, h.y)) 
        	remove(h.NE, rect);
    }

    private Node insert(Node h, Key x, Key y, Value value) {
        if (h == null) 
        	return pool.obtain().set(x, y, value);
        else if (h.value == null) {
        	h.value = value;
        }
        else if ( less(x, h.x) &&  less(y, h.y)) 
        	h.SW = insert(h.SW, x, y, value);
        else if ( less(x, h.x) && !less(y, h.y)) 
        	h.NW = insert(h.NW, x, y, value);
        else if (!less(x, h.x) &&  less(y, h.y)) 
        	h.SE = insert(h.SE, x, y, value);
        else if (!less(x, h.x) && !less(y, h.y)) 
        	h.NE = insert(h.NE, x, y, value);
        return h;
    }
    
    public void query(ArrayList<Value> holder, Key x, Key y, Key u, Key v) {
    	query2D(holder,new Interval2D<Key>(new Interval<Key>(x,y),new Interval<Key>(u,v)));
    }

    public void query2D(ArrayList<Value> holder, Interval2D<Key> rect) {
        query2D(holder, root, rect);
    }

    private void query2D(ArrayList<Value> holder, Node h, Interval2D<Key> rect) {
        if (h == null) 
        	return;
        Key xmin = rect.intervalX.low;
        Key ymin = rect.intervalY.low;
        Key xmax = rect.intervalX.high;
        Key ymax = rect.intervalY.high;
        if (rect.contains(h.x, h.y)) {
        	holder.add(h.value);
            System.out.println("    (" + h.x + ", " + h.y + ") " + h.value);
        }
        if ( less(xmin, h.x) &&  less(ymin, h.y)) 
        	query2D(holder, h.SW, rect);
        if ( less(xmin, h.x) && !less(ymax, h.y)) 
        	query2D(holder, h.NW, rect);
        if (!less(xmax, h.x) &&  less(ymin, h.y)) 
        	query2D(holder, h.SE, rect);
        if (!less(xmax, h.x) && !less(ymax, h.y)) 
        	query2D(holder, h.NE, rect);
    }

    private boolean less(Key k1, Key k2) { return k1.compareTo(k2) <  0; }
    private boolean eq  (Key k1, Key k2) { return k1.compareTo(k2) == 0; }
}
