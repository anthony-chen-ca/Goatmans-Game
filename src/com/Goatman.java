/*
 * GoatMan.java
 * goatman class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */


package com;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Goatman extends Creature { // 4
	private boolean isChasing;
	private boolean isStuck;
	private boolean rage;
	private int[] oldPos;
	private ImageIcon goatMan1;
	private String goatManImg1 = "src/images/goatman1.png";

	public Goatman(int x, int y, int width, int height) {
		super(x, y, width, height, 4, 3);
		this.isChasing = false;
		this.isStuck = false;
		this.rage = false;
		this.oldPos = new int[] {x, y};
		this.goatMan1 = new ImageIcon(goatManImg1);
	}
	
    //Drawing goatMan sprite
	public void draw(Graphics g) {
		g.drawImage(goatMan1.getImage(), this.getX(), this.getY(), null);
	}

	public void setChasing(boolean isChasing) {
		this.isChasing = isChasing;
	}
	
	public void setStuck(boolean isStuck) {
		this.isStuck = isStuck;
	}
	
	public void setRage(boolean rage) {
		this.rage = rage;
	}
	
    // chases the player if the state is blood moon
	public boolean move(Item player, Item magicCircle) {
		if (this.isChasing) {
			if (this.getBoundingBox().intersects(player.getBoundingBox())) {
				return false;
			} else {
				if (!this.rage) {
					if (this.getX() > player.getX()
							&& this.getX() > magicCircle.getX() + magicCircle.getWidth()) {
						this.moveLeft();
					}else if (this.getX() < player.getX()) {
						this.moveRight();
					}
				} else {
					if (this.getX() > player.getX()) {
						this.moveLeft();
					}else if (this.getX() < player.getX()) {
						this.moveRight();
					}
				}

				if (this.isStuck || this.getY() > player.getY()) {
					this.jump();
				}
			}
		} else {
			if (this.getX() > oldPos[0]) {
				this.moveLeft();
				if (this.isStuck || this.getY() > oldPos[1]) {
					this.jump();
				}
			}else if (this.getX() < oldPos[0]) {
				this.moveRight();
				if (this.isStuck || this.getY() > oldPos[1]) {
					this.jump();
				}
			}
		}
		return true;
	}
}