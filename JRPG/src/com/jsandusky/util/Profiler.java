package com.jsandusky.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Profiler {
	public static class Profile {
		public long last = 0;
		public long avg = 0;
		public long worst = 0;
		public long best = 0;
		public long total = 0;
		public long ct = 1;
	}
	HashMap<String, Profile> profiles_ = new HashMap<String,Profile>();
	static Profiler inst_;
	Profiler() {}

	public static Profiler inst() {
		if (inst_ == null)
			inst_ = new Profiler();
		return inst_;
	}

	public void start(String name) {
		long t = java.lang.System.currentTimeMillis();
		Profile p = profiles_.get(name);
		if (p != null) {
			p.last = t;
			++p.ct;
		} else {
			p = new Profile();
			p.last = t;
			p.avg = 0;
			p.best = 0;
			p.worst = 0;
			profiles_.put(name, p);
		}
	}	
	
	public void stop(String name) {
		long t = java.lang.System.currentTimeMillis();
		Profile p = profiles_.get(name);
		if (p != null) {
			long delta = t - p.last;
			p.total += delta;
			p.best = p.best < delta ? p.best : delta;
			p.worst = p.worst > delta ? p.worst : delta;
			p.avg = p.total / p.ct;
			p.last = t;
		} else {
			p = new Profile();
			p.last = t;
			p.avg = 0;
			p.best = 0;
			p.worst = 0;
		}
	}

	//DO NOT MIX WITH START/STOP PROFILING
	//Intended to count occurences of something and the time between those
	//NOT INTENDED TO COUNT TIME INVOLVED IN SOMETHING
	public void bump(String name) {
		long t = java.lang.System.currentTimeMillis();
		Profile p = profiles_.get(name);
		if (p != null) {
			long delta = t - p.last;
			p.last = t;
			p.best = p.best < delta ? p.best : delta;
			p.worst = p.worst > delta ? p.worst : delta;
			p.avg = (p.avg + delta) / p.ct;
			++p.ct;
		} else {
			p = new Profile();
			p.last = t;
			p.avg = 0;
			p.best = 0;
			p.worst = 0;
		}		
	}
	
	public void clear() {
		profiles_.clear();
	}
	
	public Set<String> getProfiles() {
		return profiles_.keySet();
	}
	
	public Profile getProfile(String name) {
		if (profiles_.containsKey(name))
			return profiles_.get(name);
		return null;
	}
}