/*
 * Button.java
 * Button class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Button {
	private Rectangle rect;
	private Color color;
	private Text text;
	
	public Button(Rectangle rect, Color color, Text text) {
		this.rect = rect;
		this.color = color;
		this.text = text;
	}
	
	//Draws out buttons 
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		text.draw(g);
	}
	
	//Checks if button is clicked
	public boolean isButtonClicked(boolean clicked, Point p) {
		return (clicked && this.rect.contains(p));
	}
	
	public void updateColors(Color buttonColor, Color textColor) {
		this.color = buttonColor;
		this.text.setColor(textColor);
	}
}