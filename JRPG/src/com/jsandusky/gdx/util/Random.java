package com.jsandusky.gdx.util;

public class Random {
	private long seed;
	private long rand31()
	{
	    long high_, low_;
	    low_ = 16807 * (seed & 0xFFFF);
	    high_ = 16807 * (seed >> 16);
	    low_ += (high_ & 0x7FFF) << 16;
	    low_ += high_ >> 15;
	    if (low_ > 0x7FFFFFFF)
	        low_ -= 0x7FFFFFFF;
	    return (seed = low_);
	}
	public Random(long aSeed) { 
		seed = aSeed;
	}
	public int rand() {
		return (int)rand31();
	}
	public int randI(int aMin, int aMax) {
		return (int)(aMin + (rand() % (aMax - aMin + 1)));
	}
	
	public float randF(float aMin, float aMax) {
		return (aMin + (aMax - aMin) * randF());
	}
	
	public float randF() {
		return ((float)rand()) * (1.0f/Float.MAX_VALUE);
	}
	
	public double randD(double aMin, double aMax) {
		return (aMin + (aMax - aMin) * randD());
	}
	
	public double randD() {
		return ((double)rand()) * (1.0f/Double.MAX_VALUE);
	}
	
	public int d6() {
		return randI(1,6);
	}
	
	public int d6(int ct) {
		int sum = 0;
		while (ct > 0) {
			sum += d6();
			--ct;
		} return sum;
	}
	
	public int d8() {
		return randI(1,8);
	}
	
	public int d10() {
		return randI(1,10);
	}
	
	public int d12() {
		return randI(1,12);
	}
	
	public int d20() {
		return randI(1,20);
	}
	
	public int d8(int ct) {
		int sum = 0;
		while (ct > 0) {
			sum += d8();
			--ct;
		} return sum;
	}
	
	public int d10(int ct) {
		int sum = 0;
		while (ct > 0) {
			sum += d10();
			--ct;
		} return sum;
	}
	
	public int d12(int ct) {
		int sum = 0;
		while (ct > 0) {
			sum += d12();
			--ct;
		} return sum;
	}
	
	public int d20(int ct) {
		int sum = 0;
		while (ct > 0) {
			sum += d20();
			--ct;
		} return sum;
	}
}
