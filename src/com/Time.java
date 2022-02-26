/*
 * Time.java
 * time class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

public class Time {
	private Timer timer;
	private int interval; // amount of time of each section
	private int delay = 1000; // before task is executed
	private int period = 1000; // between executed tasks
	private int time; // time
	private boolean timeToChange;
	private String stateName;
	private int daylightLength = 15 + 1;
	private int nightLength = 15 + 1;
	private int bloodHourLength = 10 + 1;
	
	private Font font1 = new Font("Courier", Font.PLAIN, 45);
	private Font font2 = new Font("Courier", Font.PLAIN, 30);

	public Time(int daylightLength, int nightLength, int bloodHourLength) {
		this.daylightLength = daylightLength + 1;
		this.nightLength = nightLength + 1;
		this.bloodHourLength = bloodHourLength + 1;
		this.timer = new Timer();
		this.stateName = "Daylight";
		this.interval = daylightLength;
		timer.scheduleAtFixedRate(new TimeTask(), this.delay, this.period);
		this.timeToChange = false;
	}

	public class TimeTask extends TimerTask {
		public void run() {
			time = setInterval();
		}
	}
	
    // if timer has reached zero, it resets 
	public int setInterval() {
		if (this.interval <= 0) {
			timeToChange = true;
			resetTimer();
		}
		return --this.interval;
	}

	public void resetTimer() {
		this.timer.cancel();
		this.timer = new Timer();
		this.timer.scheduleAtFixedRate(new TimeTask(), this.delay, this.period);
	}

	public void draw(Graphics g, int width, int camX, int camY, int level) {
		// display name and time to screen
		FontMetrics metrics1 = g.getFontMetrics(font1);
		FontMetrics metrics2 = g.getFontMetrics(font2);

		g.setFont(font1);
		g.setColor(Color.BLACK);
		int x1 = (width - metrics1.stringWidth(stateName)) / 2;
		int y1 = 100;
		g.drawString(stateName, x1 + camX, y1 + camY);
		
		if (level != 4) {
			g.setFont(font2);
			g.setColor(Color.BLACK);
			String timeString = Integer.toString(time);
			int x2 = (width - metrics2.stringWidth(timeString)) / 2;
			int y2 = 150;
			g.drawString(timeString, x2 + camX, y2 + camY);
		}
	}
	
	// changes the length of each state depening on the type of state 
	public void setTimeInterval(int state) {
		if (state == 1) {
			this.interval = daylightLength;
		} else if (state == 2) {
			this.interval = nightLength;
		} else if (state == 3) {
			this.interval = bloodHourLength;
		}
	}
	
	public boolean isTimeToChange() {
		return this.timeToChange;
	}
	
	public void setTimeToChange(boolean timeToChange) {
		this.timeToChange = timeToChange;
	}
	
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
}
