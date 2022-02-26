/*
 * Item.java
 * item class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

abstract public class Item {
	private int x;
	private int y;
	private int width;
	private int height;
	private Rectangle boundingBox;
	protected Image image;
	private int velY;
	private int velX;

	Item(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.boundingBox = new Rectangle(this.x, this.y, this.width, this.height);
		
		
	}
	public int getVelY() {

		return velY;
	}
	
	public void setVelY(int velY) {
		this.velY= velY;
	}
	
	public int getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX= velX;
	}
	
	public void setImage(Image image) {

		this.image = image;
	}
	public Image getImage() {

		return image;
	}

	public int[] getPosition() {
		return new int[] {this.x, this.y};
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
	
	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}
	public void draw (Graphics g, Item player) {};


	abstract public void draw(Graphics g);
	
}