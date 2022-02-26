/*
 * Tree.java
 * tree class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Tree extends Item{
	private int type;
	private int level;
	
	private ImageIcon image;
	
	private ImageIcon[] greenTrees;
	private ImageIcon[] redTrees;
	private ImageIcon[] purpleTrees;

	public Tree(int x, int y, int width, int height, int type, int level) {
		super(x, y, width, height);
		this.image = new ImageIcon();
		
		//loading different types of trees for each level 
		this.greenTrees = new ImageIcon[4];
		greenTrees[0] = new ImageIcon("src/images/treeDay.png");
		greenTrees[1] = new ImageIcon("src/images/treeDay1.png");
		greenTrees[2] = new ImageIcon("src/images/treeDay2.png");
		greenTrees[3] = new ImageIcon("src/images/dayFlower.png");
		
		this.redTrees = new ImageIcon[4];
		redTrees[0] = new ImageIcon("src/images/treeNight.png");
		redTrees[1] = new ImageIcon("src/images/treeNight1.png");
		redTrees[2] = new ImageIcon("src/images/treeNight2.png");
		redTrees[3] = new ImageIcon("src/images/bloodFlower.png");
		
		this.purpleTrees = new ImageIcon[4];
		purpleTrees[0] = new ImageIcon("src/images/treeBlood.png");
		purpleTrees[1] = new ImageIcon("src/images/treeBlood1.png");
		purpleTrees[2] = new ImageIcon("src/images/treeBlood2.png");
		purpleTrees[3] = new ImageIcon("src/images/nightFlower.png");

		this.type = type;
		this.level = level;
	}
	
	//setting each image depending on state 
	public void draw(Graphics g) {
		if (level == 1) {
			image.setImage(greenTrees[type].getImage());
		} else if (level == 2) {
			image.setImage(greenTrees[type].getImage());
		} else if (level == 3) {
			image.setImage(redTrees[type].getImage());
		} else {
			image.setImage(purpleTrees[type].getImage());
		}

		g.drawImage(image.getImage(), this.getX(), this.getY(), null);
	}
}
