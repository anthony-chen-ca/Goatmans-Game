/*
 * Haystack.java
 * haystack class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Haystack extends Item {
	private ImageIcon haystack;
	private String haystackImg = "src/images/haystack.png";

	public Haystack(int x, int y, int width, int height) {
		super(x, y, width, height);
		haystack = new ImageIcon(haystackImg);
	}

	//drawing haystack sprite
	@Override
	public void draw(Graphics g) {
		g.drawImage(haystack.getImage(), this.getX(), this.getY(), null);
	}
}