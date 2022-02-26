/*
 * FrameRate.java
 * frame rate class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class FrameRate {
	String frameRate; // display frame rate to screen
	long lastTimeCheck; // store the time of the last time the time was recorded
	long deltaTime; // to keep the elapsed time between last time and current time
	int frameCount; // count how many frames per second
	
	public FrameRate() { 
		lastTimeCheck = System.currentTimeMillis();
		frameCount = 0;
		frameRate = "0 fps";
	}

	public void update() { 
		long currentTime = System.currentTimeMillis();  // get the current time
		deltaTime += currentTime - lastTimeCheck; // add to the elapsed time
		lastTimeCheck = currentTime; // update the last time variable
		frameCount++; // new frame
		if (deltaTime >= 1000) { // when a second has passed, update the string message
			frameRate = frameCount + " fps" ;
			frameCount = 0; // reset the number of frames since last update
			deltaTime = 0;  // reset the elapsed time     
		}
	}

	public void draw(Graphics g, int x, int y) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		g.setColor(Color.BLACK);
		g.drawString(frameRate,x,y); // display the frameRate
	}
}