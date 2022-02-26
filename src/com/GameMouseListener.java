/*
 * GameMouseListener.java
 * game mouse listener class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */


package com;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameMouseListener implements MouseListener, MouseMotionListener {
	private int x;
	private int y;
	private boolean clicked = false;

	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	public void mousePressed(MouseEvent e) {
		
		x = e.getX();
		y = e.getY();
		this.clicked = true;
	}

	public void mouseReleased(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		this.clicked = false;
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		System.out.println("MOVE");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	public boolean isClicked() {
		return clicked;
	}
	
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}