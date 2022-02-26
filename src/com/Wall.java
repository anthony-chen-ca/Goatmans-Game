/*
 * Wall.java
 * wall class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Color;
import java.awt.Graphics;

class Wall extends Item {
  Wall(int x, int y, int width, int height) {
    super(x, y, width, height);
  }
  
  //Drawing wall 
  public void draw(Graphics g) {
	  g.setColor(Color.BLACK);
	  g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
  }
}