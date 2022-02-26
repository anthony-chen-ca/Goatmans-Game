
/*
 * SunMoon.java
 * sun moon class, displaying moons
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class SunMoon extends Item {
	private ImageIcon image; 
	private ImageIcon moon;
	private ImageIcon bloodMoon;
	private ImageIcon sun;
	private int state = 1;
	
	public SunMoon(int x, int y, int width, int height){
		super(x, y, width, height);
		this.image = new ImageIcon();
		this.moon = new ImageIcon("src/images/dayMoon.png");
		this.sun = new ImageIcon("src/images/sun.png");
		this.bloodMoon = new ImageIcon("src/images/bloodMoon.png");
		this.image.setImage(sun.getImage());
		this.state = 1;
	}
	
	//Displays different moons depending on what the state is 
	@Override
	public void draw(Graphics g) {
		if (state == 1) {
			image.setImage(sun.getImage());
		} else if (state == 2) {
			image.setImage(moon.getImage());
		} else {
			image.setImage(bloodMoon.getImage());
		}
		
		g.drawImage(image.getImage(),this.getX(), this.getY(), null);
			
	}
	
	public void update(int state) {
		this.state = state;
	}
}
