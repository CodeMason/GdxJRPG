package com.jsandusky.util;

import java.util.ArrayList;

public class Scheduler {
	ArrayList<IScheduled> scheduled_ = new ArrayList<IScheduled>(32);
	int maxTime_;
	int sub_ = 0;
	int maxExec_;
	boolean paused_ = false;
	boolean forceSequence_ = false;
	
	public Scheduler(int maxTime,int maxExecs, boolean aForceSequence) {
		forceSequence_ = aForceSequence;
		maxTime_ = maxTime;
		maxExec_ = maxExecs;
	}
	public void pause() {
		paused_ = true;
	}
	public boolean paused() {
		return paused_;
	}
	public void resume() {
		paused_ = false;
	}
	public void run() {
		if (paused_)
			return;
		long time = java.lang.System.currentTimeMillis();
		sub_ = forceSequence_ ? 0 : Math.max(sub_, 0);
		int execCtr = 0;
		for (; sub_ < scheduled_.size(); ++sub_) {
			if (paused_)
				return;
			IScheduled s = scheduled_.get(sub_);
			if (s.run() == false) {
				scheduled_.remove(sub_);
				--sub_;
			}
			++execCtr;
			long newTime = java.lang.System.currentTimeMillis();
			int deltaTime = (int) (newTime - time);
			if (deltaTime > maxTime_)
				return;
			if (execCtr >= maxExec_)
				return;
		}
		sub_ = 0;
	}
	public void add(IScheduled sched) {scheduled_.add(sched);}
	public void clear() {scheduled_.clear();}
}
