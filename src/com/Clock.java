/*
 * Clock.java
 * clock class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

public class Clock {
	private long elapsedTime; // time that has passed
	private long lastTimeCheck; // when the last time check occurred
	
	public Clock() { // constructor starts with the first time check
		this.lastTimeCheck = System.nanoTime();
		this.elapsedTime = 0;
	}

	public void update() { // called each loop of the game loop
		long currentTime = System.nanoTime(); // if the computer is fast you need more precision
		this.elapsedTime = currentTime - this.lastTimeCheck; // update current time
		this.lastTimeCheck = currentTime; // update last time check
	}

	public double getElapsedTime() { // used to get the elapsed time in milliseconds
		return this.elapsedTime/1.0E9;
	}
}