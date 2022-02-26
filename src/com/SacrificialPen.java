/*
 * SacrificialPen.java
 * sacrificial pen class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class SacrificialPen extends Item {
	private int requiredRabbits;
	private int numRabbits;
	private String rabbitImg = "src/images/rabbit.png";
	private ImageIcon rabbit;
	private Text text;
	private String penImg = "src/images/pen.png";
	private ImageIcon pen;
	
	SacrificialPen(int x, int y, int width, int height, int requiredRabbits) {
		super(x, y, width, height);
		this.numRabbits = 0;
		this.rabbit = new ImageIcon(rabbitImg);
		this.rabbit = new ImageIcon(rabbit.getImage().getScaledInstance(40, 40, BufferedImage.SCALE_SMOOTH));
		this.text = new Text(2, Integer.toString(numRabbits), Color.BLACK,
				new Rectangle(x + 25, y - 100, 50, 50));
		this.requiredRabbits = requiredRabbits;
		this.pen = new ImageIcon(penImg);
		this.pen = new ImageIcon(pen.getImage().getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH));
	}

	@Override
	/*
	 * draws sacrificial pen sprite 
	 * adds rabbits to sacrificial pen and displays it
	 * adds text informing playerof number of rabbits left to win
	 */
	
	public void draw(Graphics g) {
		g.drawImage(pen.getImage(), this.getX(), this.getY(), null);
		
		for (int i = 0; i < numRabbits; i++) { // display rabbits
			g.drawImage(rabbit.getImage(), this.getX() + 30*i, this.getY() - 25, null);
		}
		
		text.setText(Integer.toString(requiredRabbits - numRabbits) + " left");
		text.draw(g);
	}
	
	public void addRabbit() {
		this.numRabbits++;
	}
	
	public int getNumRabbits() {
		return numRabbits;
	}
}
