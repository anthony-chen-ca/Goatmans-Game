/*
 * Creature.java
 * Creature class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Rectangle;
import java.util.ArrayList;

abstract public class Creature extends Item {
	private int organism;
	private int velX;
	private int velY;
	private int speed;
	private boolean jumping;
	private int jumpHeight;

	private boolean collideLeft = false;
	private boolean collideRight = false;
	private boolean collideUp = false;
	private boolean collideDown = false;
	private float gravity;

	public Creature(int x, int y, int width, int height, int organism, int speed) {
		super(x, y, width, height);
		this.speed = speed;
		this.organism = organism;
		this.jumping = false;
		this.gravity = 0.5f;
	}

	public void update(double elapsedTime, ArrayList<Item> items, int rightBounds) {
		// reset
		collideLeft = false;
		collideRight = false;
		collideUp = false;
		collideDown = false;

		// temporary location
		if (this.organism != 3 && this.organism != 5) { // if not bat
			this.checkForJump(); // check for jump and apply gravity
		}
		
		if (this.organism == 5) {
			((Cloud)this).floatRight();
		}
		
		int tempX = (int)(this.getX() + (this.velX));
		int tempY = (int)(this.getY() + (this.velY));
		Rectangle tempBounds = this.getBoundingBox();
		tempBounds.x = tempX;
		tempBounds.y = tempY;

		// check collision
		for (Item item : items) {
			if (item != this) {
				checkCollision(tempBounds, item.getBoundingBox()); // if collide
			}
		}

		if ((this.velX > 0 && this.collideRight == false) // not colliding right
				|| (this.velX < 0 && this.collideLeft == false)) { // not colliding left
			if (tempX < rightBounds || this.organism == 4 || this.organism == 5) { // dumb temporary code to prevent falling out
				this.setX(tempX);
				tempBounds = this.getBoundingBox();
				tempBounds.x = tempX;
				this.setBoundingBox(tempBounds);
			}
		}

		if ((this.velY > 0 && this.collideDown == false) // not colliding down
				|| (this.velY < 0 && this.collideUp == false)) { // not colliding up
			this.setY(tempY);
			tempBounds = this.getBoundingBox();
			tempBounds.y = tempY;
			this.setBoundingBox(tempBounds);
		} else if (this.collideDown == true) {
			this.velY = 0;
		}

		this.velX = 0;

		if (this.organism == 3) { // if bat
			this.velY = 0;
		}
		
		// this.setX((int)(this.getX() + (this.velX*elapsedTime*100))); // d = d0 + vt
		// this.setY((int)(this.getY() + (this.velY*elapsedTime*100))); // d = d0 + vt
	}
	
	
    //Player movement 
	public void moveLeft() {
		this.velX = this.speed * (-1);
	}

	public void moveRight() {
		this.velX = this.speed;
	}

	public void moveUp() {
		this.velY = this.speed * (-1);
	}

	public void moveDown() {
		this.velY = this.speed;
	}

	public void jump() {
		if (this.jumping == false && collideDown == true) { // and on ground
			this.jumping = true;
			this.jumpHeight = this.getY() - (int)(this.getHeight() * 1.5);
		}
	}
	
	//Checks if a creature is jumping
	public void checkForJump() {
		if (this.jumping) {
			this.velY = -1;
			if (this.getY() <= this.jumpHeight) {
				this.jumping = false;
			}
		}else {
			this.velY = 1;
		}
	}
	
	//Rabbit jumps in a parabola in random directions 
	public void rabbitHop(double time, ArrayList<Item> rabbitArray) {
		System.out.println("Rabbit hop being called");
		if (this.jumping == false && collideDown == true) { // and on ground
			this.jumping = true;
			velY = (int) -12.0f;
			//this.jumpHeight = this.getY() - (int)(this.getHeight() * 1.5);
			for (Item rabbit : rabbitArray) {
				rabbit.setVelY((int) (rabbit.getVelY() + gravity * time)); 
				rabbit.setX((int) (velX * time));
				rabbit.setY((int) (velY * time));
			}
		}
	}

	//Checks collision between items on right and left side
	public void checkCollision(Rectangle rect1, Rectangle rect2) {
		if (rect1.intersects(rect2)) {
			int centerX1 = rect1.x + (rect1.width/2);
			int centerY1 = rect1.y + (rect1.height/2);
			int centerX2 = rect2.x + (rect2.width/2);
			int centerY2 = rect2.y + (rect2.height/2);
			int hd = (int)(Math.abs(Math.pow(centerX1, 2) + Math.pow(centerX2, 2)));
			int vd = (int)(Math.abs(Math.pow(centerY1, 2) + Math.pow(centerY2, 2)));

			if (hd < vd) {
				if (centerX1 < centerX2) {
					collideRight = true;
				} else {
					collideLeft = true;
				}
			} else if (hd > vd) {
				if (centerY1 < centerY2) {
					collideDown = true;
				} else {
					collideUp = true;
				}
			}
		}
	}

	public int getOrganism () {
		return this.organism;
	}
}