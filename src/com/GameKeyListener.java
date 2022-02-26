/*
 * GameKeyListener.java
 * game key listener class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameKeyListener implements KeyListener{
	private ArrayList<Integer> keysDown;
	
	GameKeyListener() {
		this.keysDown = new ArrayList<Integer>();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (isKeyValid(e)) {
			if (!this.keysDown.contains(e.getKeyCode())) {
				this.keysDown.add(new Integer(e.getKeyCode()));
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (this.keysDown.contains(e.getKeyCode())) {
			this.keysDown.remove(new Integer(e.getKeyCode()));
		}
	}
    //Calling movement player depending on which key is pressed 
	//setting direction number depending on which key is pressed 
	public void movePlayer(Item player, ArrayList<Item> rabbitArray, Item sacrificialPen ) {
		if (this.keysDown.contains(new Integer(KeyEvent.VK_UP))) {
			((Creature)player).jump();
			((Player)player).setDirection(1);
		}
		
		if (this.keysDown.contains(new Integer(KeyEvent.VK_LEFT))) {
			((Creature)player).moveLeft();
			((Player)player).setDirection(2);
		} else if (this.keysDown.contains(new Integer(KeyEvent.VK_RIGHT))) {
			((Creature)player).moveRight();
			((Player)player).setDirection(3);
		} else {
			((Player)player).setDirection(1);
		}
		
		if (this.keysDown.contains(new Integer(KeyEvent.VK_SPACE))) {
			((Player)player).holdRabbit(rabbitArray);
			((Player)player).dropRabbit(sacrificialPen);
		}
	}
	
	private boolean isKeyValid(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			return true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			return true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			return true;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			return true;
		} else {
			return false;
		}
	}
}
