/*
 * GoatmanGame.java
 * goat man game class 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;

import java.io.File;
import java.util.Scanner;

public class GoatmanGame {
	public static void main(String[] args) throws Exception { 
		File file = new java.io.File("src/savefiles/save.txt");
		Scanner input = new Scanner(file);
		
		int[] data = new int[4];
		int i = 0;
		while (input.hasNext() && i < 4) {
			data[i] = input.nextInt();
			i++;
		}
		
		input.close();
		
		GameFrame game = new GameFrame(data);
		while (game.isProgramRunning()) {
			game.refresh();
		}
		System.exit(0);
	}
}