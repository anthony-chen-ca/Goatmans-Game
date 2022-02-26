/*
 * Ground.java
 * ground class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Color;
import java.awt.Graphics;

class Ground extends Item {
  Ground(int x, int y, int width, int height) {
    super(x, y, width, height);
  }
  
  //drawing grouond 
  public void draw(Graphics g) {
	  g.setColor(Color.BLACK);
	  g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
  }
}