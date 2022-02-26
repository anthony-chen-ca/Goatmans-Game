/*
 * Cloud.java
 * Cloud class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Cloud extends Creature {
	private ImageIcon image;
	private ImageIcon cloud1;
	private ImageIcon cloud2;
	public int worldWidth;
	
	public Cloud (int x, int y, int width, int height, int worldWidth, int type) {
		super(x, y, width, height, 5, 1);
		this.image = new ImageIcon();
		this.cloud1 = new ImageIcon("src/images/cloud1.png");
		this.cloud2 = new ImageIcon("src/images/cloud2.png");
		if (type == 1) {
			this.image.setImage(cloud1.getImage().getScaledInstance(150, 60, Image.SCALE_DEFAULT));
		} else if (type == 2) {
			this.image.setImage(cloud2.getImage().getScaledInstance(150, 60, Image.SCALE_DEFAULT));
		}
		this.worldWidth = worldWidth;
	}
	
	//Draws clouds 
	public void draw(Graphics g) {
		g.drawImage(this.image.getImage(), this.getX(), this.getY(), null);
	}
	
	//Make clouds move across the screen in the sky 
	public void floatRight() {
		if (this.getX() <= worldWidth) {
			this.moveRight();
		} else {
			this.setX(-100);
		}
	}
}
