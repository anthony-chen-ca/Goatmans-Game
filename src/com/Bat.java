/*
 * Bat.java
 * Bat class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Bat extends Creature { // 3
	private boolean isChasing;
	private boolean isHungry;
	private ImageIcon bat;
	private String batImg = "src/images/bat.gif";

	public Bat(int x, int y, int width, int height) {
		super(x, y, width, height, 3, 1);
		this.isChasing = false;
		this.isHungry = false;
		bat = new ImageIcon(batImg);
	}
	
	//Draws bat gif
	public void draw(Graphics g) {
		g.drawImage(bat.getImage(),this.getX(), this.getY(), null);
	}
    
	
	/*
	 * if bat is hungry it will chase rabbit
	 * bat touches rabbit, the rabbit will disappear
	 * once bat eats rabbit it will no longer be hungry
	 */
	public void killRabbits(ArrayList<Item> rabbitArray, Item player) {
		if (isChasing && isHungry) {
			for (Item rabbit : rabbitArray) {
				if (this.getBoundingBox().intersects(rabbit.getBoundingBox())) {
					((Rabbit)rabbit).setVisible(false);
					isHungry = false;
				}
			}
			
			if (this.getBoundingBox().intersects(player.getBoundingBox())) {
				isHungry = false;
			} else {
				Item targetRabbit = getClosestRabbit(rabbitArray);
				if (targetRabbit != null) {
					if (this.getX() > targetRabbit.getX()) {
						this.moveLeft();
					}else if (this.getX() < targetRabbit.getX()) {
						this.moveRight();
					}else if (this.getY() > targetRabbit.getY()) {
						this.moveUp();
					}else if (this.getY() < targetRabbit.getY()) {
						this.moveDown();
					}
				}else {
					isHungry = false;
				}
			}
		}else {
			if (this.getY() > -100) {
				this.moveUp();
			}
		}
	}
	
	//Calculates the nearest rabbit to each bat
	public Item getClosestRabbit(ArrayList<Item> rabbitArray) {
		int minDistance = 10000;
		int distance;
		Item targetRabbit = null;

		for (Item rabbit : rabbitArray) {
			if (((Rabbit)rabbit).isVisible()) {
				distance = calcDistance(rabbit);
				if (distance < minDistance) {
					minDistance = distance;
					targetRabbit = rabbit;
				}
			}
		}
		return targetRabbit;
	}

	public int calcDistance(Item rabbit) {
		double part1 = Math.pow((this.getX() - rabbit.getX()), 2);
		double part2 = Math.pow((this.getY() - rabbit.getY()), 2);
		return (int)(Math.sqrt(part1 + part2));
	}

	public boolean isChasing() {
		return isChasing;
	}

	public void setChasing(boolean isChasing) {
		this.isChasing = isChasing;
	}
	
	public void setHungry(boolean isHungry) {
		this.isHungry = isHungry;
	}
	
}