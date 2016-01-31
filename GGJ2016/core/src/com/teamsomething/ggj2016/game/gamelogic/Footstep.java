/**
 * 
 */
package com.teamsomething.ggj2016.game.gamelogic;

/**
 * @author
 *
 */
public class Footstep implements Comparable<Footstep> {
	@Override
	public String toString() {
		return "Footstep [type=" + type + ", time=" + time + ", didMiss=" + didMiss + "]";
	}

	private FootstepType type;
	private double time;
	private boolean didMiss = false;

	public FootstepType getType() {
		return type;
	}

	public void setType(FootstepType type) {
		this.type = type;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public boolean isDidMiss() {
		return didMiss;
	}

	public void setDidMiss(boolean didMiss) {
		this.didMiss = didMiss;
	}

	public Footstep(FootstepType type, double time) {
		super();
		this.type = type;
		this.time = time;
	}

	@Override
	public int compareTo(Footstep o) {
		if (o.time < time) {
			return -1;
		} else if (o.time > time) {
			return 1;
		} else {
			return 0;
		}
	}
}
