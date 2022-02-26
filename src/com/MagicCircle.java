/*
 * MagicCirle.java
 * magic circle class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class MagicCircle extends Item {
	private ImageIcon magicCircle;
	
	MagicCircle(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.magicCircle = new ImageIcon("src/images/magicCircle.png");
		this.magicCircle = new ImageIcon(magicCircle.getImage().getScaledInstance
				(width, height, BufferedImage.SCALE_SMOOTH));
	}
	
	//Drawing magic circle sprite 
	@Override
	public void draw(Graphics g) {
		g.drawImage(magicCircle.getImage(), this.getX(), this.getY(), null);
	}
}