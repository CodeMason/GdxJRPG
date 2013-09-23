package com.jsandusky.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Tween {
	public static class TweenInt {
		int base, target;
		public TweenInt(int base, int target) {
			this.base = base;
			this.target = target;
		}
		
		public int get(float factor) {
			return (int) (this.base + (this.target * factor));
		}
	}
	
	public static class TweenFloat {
		float base, target;
		public TweenFloat(float base, float target) {
			this.base = base;
			this.target = target;
		}
		
		public float get(float factor) {
			return this.base + (this.target * factor);
		}
	}
	
	public static class TweenVec2 {
		Vector2 base, target;
		public TweenVec2(Vector2 base, Vector2 target) {
			this.base = base;
			this.target = target;
		}
		
		public Vector2 get(float factor) {
			return this.base.lerp(this.target, factor);
		}
	}
	
	public static class TweenVec3 {
		Vector3 base, target;
		public TweenVec3(Vector3 base, Vector3 target) {
			this.base = base;
			this.target = target;
		}
		
		public Vector3 get(float factor) {
			return this.base.lerp(this.target, factor);
		}
	}
}
