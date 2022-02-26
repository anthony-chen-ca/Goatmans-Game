/*
 * Player.java
 * player class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Player extends Creature  { // 1
	private boolean holding;
    private ImageIcon image; 
	private ImageIcon standing;
	private ImageIcon left;
	private ImageIcon right;
	private ImageIcon standingRabbit;
	private ImageIcon leftRabbit;
	private ImageIcon rightRabbit;
	
	private int direction;

	public Player(int x, int y, int width, int height) {
		super(x, y, width, height, 1, 2);
		this.image = new ImageIcon();
		this.standing = new ImageIcon("src/images/standing.png");
		this.left = new ImageIcon("src/images/left.png");
		this.right = new ImageIcon("src/images/right.png");
		this.standingRabbit = new ImageIcon("src/images/standingRabbit.png");
		this.leftRabbit = new ImageIcon("src/images/leftRabbit.png");
		this.rightRabbit = new ImageIcon("src/images/rightRabbit.png");
		this.image.setImage(standing.getImage());
		this.direction = 1;
	}
    
	/*
	 * Displays different graphics depending on which movement is made by player
	 * If player holding rabbit, the player rabbit sprites will display
	 */
	public void draw(Graphics g) {
		if (direction == 1) {
			if (this.holding == false) {
				image.setImage(standing.getImage().getScaledInstance(70, 100, Image.SCALE_DEFAULT));
			} else {
				image.setImage(standingRabbit.getImage().getScaledInstance(70, 100, Image.SCALE_DEFAULT));
			}
		} else if (direction == 2) {
			if (this.holding == false) {
				image.setImage(left.getImage().getScaledInstance(70, 100, Image.SCALE_DEFAULT));
			} else {
				image.setImage(leftRabbit.getImage().getScaledInstance(70, 100, Image.SCALE_DEFAULT));
			}
		} else if (direction == 3) {
			if (this.holding == false) {
				image.setImage(right.getImage().getScaledInstance(70, 100, Image.SCALE_DEFAULT));
			} else {
				image.setImage(rightRabbit.getImage().getScaledInstance(70, 100, Image.SCALE_DEFAULT));
			}
		}
		
		g.drawImage(image.getImage(),this.getX(), this.getY(), null);
	}
    
	/*
	 * check if player is holding rabbit
	 * if player not holding and player intersects rabbit it will get rabbit visible to false
	 */
	public void holdRabbit(ArrayList<Item> rabbitArray) {
		for (Item rabbit : rabbitArray) {
			if (this.holding == false && ((Rabbit)rabbit).isVisible()) {
				if (this.getBoundingBox().intersects(rabbit.getBoundingBox())) {
					this.setHolding(true);
					((Rabbit)rabbit).setVisible(false);
				}
			}
		}
	}
	
	/*
	 * if player is holding rabbit
	 * if player intersect sacrificial pen 
	 * the rabbit will display in the scrificial pen
	 * holding will become false 
	 */
	public void dropRabbit(Item sacrificialPen) {
		if (this.holding == true) {
			if (this.getBoundingBox().intersects(sacrificialPen.getBoundingBox())) {
				((SacrificialPen)sacrificialPen).addRabbit();
				this.holding = false;
			}
		}
	}
	
	public void setHolding (boolean holding) {
		 this.holding = holding;
	}
	
	public void setDirection (int direction) {
		// 1 - UP
		// 2 - LEFT
		// 3 - RIGHT
		
		this.direction = direction;
	}

}