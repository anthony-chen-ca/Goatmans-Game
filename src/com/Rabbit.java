/*
 * Rabbit.java
 * Rabbit class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */


package com;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public class Rabbit extends Creature { // 2
	private boolean visible;
	private String rabbitImg = "src/images/rabbit.png";
	private ImageIcon rabbit;

	Rabbit(int x, int y, int width, int height) {
		super(x, y, width, height, 2, 1);
		this.visible = true;
		this.rabbit = new ImageIcon(rabbitImg);
		this.rabbit = new ImageIcon(rabbit.getImage().getScaledInstance(40, 40, BufferedImage.SCALE_SMOOTH));
	}
    //Drawing rabbit sprite 
	public void draw(Graphics g) {
		if (visible == true) {
			g.drawImage(rabbit.getImage(),this.getX(), this.getY(), null);
		}
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}