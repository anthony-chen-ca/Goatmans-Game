/*
 * GameFrame.java
 * game frame class, everything is drawn in here 
 * Dayeon Lee & Anthony Chen
 * January 20, 2019
 */

package com;
/**
 * GameFrame.java
 * 12/28/18
 * Anthony C. & Dayeon L.
 * This program will display the game to the screen.
 * */

import java.util.ArrayList;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class GameFrame extends JFrame { 
	private static final long serialVersionUID = 1L;

	public static int[] WORLD_SIZE;
	public static int WIDTH;
	public static int HEIGHT;

	private boolean programStatus = true;
	private boolean mainMenuStatus = true;
	private boolean levelModeStatus = false;
	private boolean gameStatus = false;
	private boolean winStatus = false;
	private boolean optionStatus = false;
	private boolean secretStatus = false;
	private boolean tutorialStatus = false;

	private GameAreaPanel gamePanel;
	private GameKeyListener gameKeyListener;
	private GameMouseListener gameMouseListener;
	private FrameRate frameRate;
	private Clock clock;
	private Time time;
	private int offsetMaxX;
	private int offsetMaxY;
	private int offsetMinX = -200;
	private int offsetMinY = 0;
	private int camX;
	private int camY;
	private int requiredRabbits;
	private int rabbitCounter;
	private static Color PALE_YELLOW = new Color(245, 222, 179);
	private static Color BLOOD_RED = new Color(140, 0, 0);

	private int level = 1;
	private int[] data;
	private int state = 1;
	private Color backgroundColor;

	private Item player;
	private Item goatman;
	private Item ground;
	private Item sacrificialPen;
	private Item magicCircle;
	private Item sunMoon;
	private ArrayList<Item> cloudArray;
	private ArrayList<Item> wallArray;
	private ArrayList<Item> treeArray;
	private ArrayList<Item> haystackArray;
	private ArrayList<Item> rabbitArray;
	private ArrayList<Item> batArray;

	private AudioClip mainMenuMusic;
	private AudioClip daylightMusic;
	private AudioClip nightMusic;
	private AudioClip bloodMusic;
	private AudioClip gameOverMusic;
	private AudioClip winMusic;

	public GameFrame(int[] data) {
		super("The Goatman's Game");
		WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
		HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
		WORLD_SIZE = new int[] {WIDTH * 2, HEIGHT};

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setResizable(false);

		try {
			initializeMusic();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

		this.data = data;

		this.gamePanel = new GameAreaPanel();
		this.add(this.gamePanel);

		this.requestFocusInWindow(); 
		this.setVisible(true);

		this.gameKeyListener = new GameKeyListener();
		this.addKeyListener(gameKeyListener);

		this.gameMouseListener = new GameMouseListener();
		this.addMouseListener(gameMouseListener);
	}

	public void refresh() {
		(this.gamePanel).repaint();
	}

	public void loadLevel() {
		this.frameRate = new FrameRate();
		this.clock = new Clock();
		this.rabbitCounter = 0;
		this.winStatus = false;

		/*
		 * Adding all items to item array 
		 * Creating items
		 */
		int groundY;
		if (this.level == 1) {
			this.time = new Time(15, 15, 15);
			GameFrame.WORLD_SIZE[0] = (int)(WIDTH * 1.5);
			this.ground = new Ground(0, HEIGHT - 120, WORLD_SIZE[0], 200);
			groundY = this.ground.getY();
			this.player = new Player(WIDTH / 2 - 25, groundY - 100, 50, 100);
			this.goatman = new Goatman(WORLD_SIZE[0] - 125, groundY - 200 - 200, 100, 200);
			this.haystackArray = new ArrayList<Item>();
			haystackArray.add(new Haystack(1500, groundY - 50, 60, 50));
			this.rabbitArray = new ArrayList<Item>();
			rabbitArray.add(new Rabbit(900, groundY - 25, 25, 25));
			rabbitArray.add(new Rabbit(1200, groundY - 25, 25, 25));
			rabbitArray.add(new Rabbit(1525, groundY - 25 - 50, 25, 25));
			rabbitArray.add(new Rabbit(1800, groundY - 25, 25, 25));
			this.batArray = new ArrayList<Item>();
			batArray.add(new Bat(WIDTH, 0, 30, 30));
			this.treeArray = new ArrayList<Item>();
			treeArray.add(new Tree(400, groundY - 175, 50, 50, 0, level));
			treeArray.add(new Tree(1300, groundY - 355, 50, 50, 1, level));
			treeArray.add(new Tree(1700, groundY - 125, 50, 50, 2, level));
			treeArray.add(new Tree(1000, groundY - 175, 50, 50, 0, level));
			treeArray.add(new Tree(750, groundY - 85, 50, 50, 3, level));
			treeArray.add(new Tree(1500, groundY - 85, 50, 50, 3, level));
			treeArray.add(new Tree(1500, groundY - 355, 50, 50, 1, level));
			treeArray.add(new Tree(1100, groundY - 355, 50, 50, 1, level));
			treeArray.add(new Tree(600, groundY - 175, 50, 50, 0, level));
			treeArray.add(new Tree(870, groundY - 125, 50, 50, 2, level));
			this.requiredRabbits = rabbitArray.size() - 1;

		} else if (this.level == 2) {
			this.time = new Time(15, 15, 15);
			GameFrame.WORLD_SIZE[0] = WIDTH * 2;
			this.ground = new Ground(0, HEIGHT - 120, WORLD_SIZE[0], 200);
			groundY = this.ground.getY();
			this.player = new Player(WIDTH / 2 - 25, groundY - 100, 50, 100);
			this.goatman = new Goatman(WORLD_SIZE[0] - 125, groundY - 200 - 200, 100, 200);
			this.haystackArray = new ArrayList<Item>();
			haystackArray.add(new Haystack(2400, groundY - 50, 60, 50));
			haystackArray.add(new Haystack(2600, groundY - 50, 60, 50));
			this.rabbitArray = new ArrayList<Item>();
			rabbitArray.add(new Rabbit(900, groundY - 25, 25, 25));
			rabbitArray.add(new Rabbit(1200, groundY - 25, 25, 25));
			rabbitArray.add(new Rabbit(1500, groundY - 25, 25, 25));
			rabbitArray.add(new Rabbit(1800, groundY - 25, 25, 25));
			rabbitArray.add(new Rabbit(2100, groundY - 25, 25, 25));
			rabbitArray.add(new Rabbit(2450, groundY - 25 - 50, 25, 25));
			this.batArray = new ArrayList<Item>();
			batArray.add(new Bat(WIDTH - 300, 0, 30, 30));
			batArray.add(new Bat(WIDTH + 200, 0, 30, 30));
			this.treeArray = new ArrayList<Item>();
			treeArray.add(new Tree(700, groundY - 175, 50, 50, 0, level));
			treeArray.add(new Tree(1800, groundY - 355, 50, 50, 1, level));
			treeArray.add(new Tree(2300, groundY - 175, 50, 50, 0, level));
			treeArray.add(new Tree(2700, groundY - 355, 50, 50, 1, level));
			treeArray.add(new Tree(3000, groundY - 125, 50, 50, 2, level));
			treeArray.add(new Tree(1500, groundY - 125, 50, 50, 2, level));
			this.requiredRabbits = rabbitArray.size() - 1;

		} else if (this.level == 3) {
			this.time = new Time(20, 20, 15);
			GameFrame.WORLD_SIZE[0] = WIDTH * 2;
			this.ground = new Ground(0, HEIGHT - 120, WORLD_SIZE[0], 200);
			groundY = this.ground.getY();
			this.player = new Player(WIDTH / 2 - 25, groundY - 100, 50, 100);
			this.goatman = new Goatman(WORLD_SIZE[0] - 125, groundY - 200 - 200, 100, 200);

			this.haystackArray = new ArrayList<Item>();
			haystackArray.add(new Haystack(1500, groundY - 50, 60, 50));
			haystackArray.add(new Haystack(2400, groundY - 50, 60, 50));
			haystackArray.add(new Haystack(2600, groundY - 50, 60, 50));
			this.rabbitArray = new ArrayList<Item>();
			rabbitArray.add(new Rabbit(900, groundY - 25, 25, 25));
			rabbitArray.add(new Rabbit(1200, groundY - 25, 25, 25));
			rabbitArray.add(new Rabbit(1550, groundY - 50 - 25, 25, 25));
			rabbitArray.add(new Rabbit(1800, groundY - 25, 25, 25));
			rabbitArray.add(new Rabbit(2100, groundY - 25, 25, 25));
			rabbitArray.add(new Rabbit(2900, groundY - 25, 25, 25));
			this.batArray = new ArrayList<Item>();
			batArray.add(new Bat(WIDTH - 100, 0, 30, 30));
			batArray.add(new Bat(WIDTH + 100, 0, 30, 30));
			batArray.add(new Bat(WIDTH + 200, 0, 30, 30));
			batArray.add(new Bat(WIDTH + 350, 0, 30, 30));
			this.treeArray = new ArrayList<Item>();
			treeArray.add(new Tree(800, groundY - 355, 50, 50, 1, level));
			treeArray.add(new Tree(1800, groundY - 125, 50, 50, 2, level));
			treeArray.add(new Tree(2000, groundY - 125, 50, 50, 2, level));
			treeArray.add(new Tree(3100, groundY - 175, 50, 50, 0, level));
			treeArray.add(new Tree(1000, groundY - 99, 99, 50, 3, level));
			treeArray.add(new Tree(1300, groundY - 99, 99, 50, 3, level));
			treeArray.add(new Tree(600, groundY - 99, 99, 50, 3, level));
			treeArray.add(new Tree(2240, groundY - 99, 99, 50, 3, level));
			treeArray.add(new Tree(450, groundY - 355, 99, 50, 1, level));
			treeArray.add(new Tree(1200, groundY - 125, 99, 50, 2, level));
			treeArray.add(new Tree(2500, groundY - 355, 99, 50, 1, level));
			this.requiredRabbits = rabbitArray.size() - 1;

		} else {
			this.time = new Time(20, 20, 15);
			GameFrame.WORLD_SIZE[0] = WIDTH * 3;
			this.ground = new Ground(0, HEIGHT - 120, WORLD_SIZE[0], 200);
			groundY = this.ground.getY();
			this.player = new Player(WIDTH / 2 - 25, groundY - 100, 50, 100);
			this.goatman = new Goatman(WIDTH/2 + 200, groundY - 200, 100, 200);
			this.haystackArray = new ArrayList<Item>();
			haystackArray.add(new Haystack(WIDTH/2 + 200 - 130, groundY - 50, 60, 50));
			haystackArray.add(new Haystack(WIDTH/2 + 200 + 150, groundY - 50, 60, 50));
			haystackArray.add(new Haystack(2700, groundY - 50, 60, 50));
			haystackArray.add(new Haystack(3000, groundY - 50, 60, 50));
			haystackArray.add(new Haystack(3300, groundY - 50, 60, 50));
			haystackArray.add(new Haystack(3600, groundY - 50, 60, 50));
			haystackArray.add(new Haystack(2900, groundY - 50, 60, 50));
			haystackArray.add(new Haystack(3800, groundY - 50, 60, 50));
			haystackArray.add(new Haystack(1700, groundY - 50, 60, 50));
			haystackArray.add(new Haystack(1900, groundY - 50, 60, 50));
			this.rabbitArray = new ArrayList<Item>();
			rabbitArray.add(new Rabbit(2725, groundY - 50 - 25, 25, 25));
			rabbitArray.add(new Rabbit(3020, groundY - 50 - 25, 25, 25));
			rabbitArray.add(new Rabbit(3325, groundY - 50 - 25, 25, 25));
			rabbitArray.add(new Rabbit(3625, groundY - 50 - 25, 25, 25));
			rabbitArray.add(new Rabbit(2925, groundY - 50 - 25, 25, 25));
			rabbitArray.add(new Rabbit(3825, groundY - 50 - 25, 25, 25));
			rabbitArray.add(new Rabbit(1725, groundY - 50 - 25, 25, 25));
			rabbitArray.add(new Rabbit(1925, groundY - 50 - 25, 25, 25));
			this.batArray = new ArrayList<Item>();
			batArray.add(new Bat(3600, 0, 30, 30));
			batArray.add(new Bat(3900, 0, 30, 30));
			batArray.add(new Bat(4200, 0, 30, 30));
			this.treeArray = new ArrayList<Item>();
			treeArray.add(new Tree(500, groundY - 175, 50, 50, 0, level));
			treeArray.add(new Tree(2000, groundY - 355, 50, 50, 1, level));
			treeArray.add(new Tree(3100, groundY - 355, 50, 50, 1, level));
			treeArray.add(new Tree(4000, groundY - 125, 50, 50, 2, level));
			treeArray.add(new Tree(3500, groundY - 125, 50, 50, 2, level));
			treeArray.add(new Tree(2100, groundY - 355, 50, 50, 1, level));
			treeArray.add(new Tree(1770, groundY - 99, 50, 50, 3, level));
			treeArray.add(new Tree(300, groundY - 99, 50, 50, 3, level));
			treeArray.add(new Tree(1500, groundY - 99, 50, 50, 3, level));
			treeArray.add(new Tree(1300, groundY - 355, 50, 50, 1, level));
			treeArray.add(new Tree(1000, groundY - 175, 50, 50, 0, level));
			treeArray.add(new Tree(2400, groundY - 175, 50, 50, 0, level));
			treeArray.add(new Tree(3600, groundY - 125, 50, 50, 2, level));
			treeArray.add(new Tree(4200, groundY - 355, 50, 50, 1, level));
			this.requiredRabbits = rabbitArray.size() - 2;
		}

		this.offsetMaxX = WORLD_SIZE[0] - WIDTH + 200;
		this.offsetMaxY = WORLD_SIZE[1] - HEIGHT;

		this.sunMoon = new SunMoon(100, 100, 100, 100);
		this.sacrificialPen = new SacrificialPen(WORLD_SIZE[0] - WIDTH / 4, groundY - 50,
				WIDTH / 4, 50, requiredRabbits);
		this.magicCircle = new MagicCircle(0, groundY, WIDTH / 4, 50);

		this.cloudArray = new ArrayList<Item>();
		cloudArray.add(new Cloud(100, 100, 50, 50, WORLD_SIZE[0], 1));
		cloudArray.add(new Cloud(300, 150, 50, 50, WORLD_SIZE[0], 2));
		this.wallArray = new ArrayList<Item>();
		wallArray.add(new Wall(offsetMinX, 0, offsetMinX * (-1), HEIGHT));
		wallArray.add(new Wall(WORLD_SIZE[0], 0, offsetMinX * (-1), HEIGHT));

		this.camX = player.getX() - (WIDTH / 2);
		this.camY = player.getY() - (HEIGHT / 2);

		// set up state
		this.state = 1;
		this.time.setStateName("Daylight");
		this.backgroundColor = PALE_YELLOW;
		this.time.setTimeInterval(state);
		try {
			initializeMusic();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		this.daylightMusic.play();
	}

	public class GameAreaPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		private Text titleText;
		private Text levelModeText;
		private Text gameOverText;
		private Text winText;

		private Button exitButton;
		private Button levelModeButton;
		private Button retryButton;
		private Button mainMenuButton;
		private Button backButton;
		private Button secretButton;
		private Button tutorialButton;
		private Button optionButton;
		private Button resetDataButton;

		private Button one;
		private Button two;
		private Button three;
		private Button four;

		private ArrayList<Text> tutorialText;

		private Text secretText1;
		private Text secretText2;
		private Text secretText3;

		private ImageIcon rabbitImage;
		private ImageIcon tutorialImage;
		private int num;

		public GameAreaPanel() {

			//All displays for menu page 
			this.titleText = new Text(3, "The Goatman's Game", Color.WHITE,
					new Rectangle(GameFrame.WIDTH/2 - 300/2, 200, 300, 100));
			this.levelModeText = new Text(3, "Level Mode", Color.WHITE,
					new Rectangle(GameFrame.WIDTH/2 - 300/2, 200, 300, 100));
			this.gameOverText = new Text(3, "Game Over", Color.RED,
					new Rectangle(GameFrame.WIDTH / 2 - 50, GameFrame.HEIGHT / 2, 100, 100));
			this.winText = new Text(3, "Congratulations!", Color.BLACK,
					new Rectangle(GameFrame.WIDTH / 2 - 50, GameFrame.HEIGHT / 2, 100, 100));

			Rectangle exitRect = new Rectangle(GameFrame.WIDTH - 150, 100, 90, 60);
			this.exitButton = new Button(exitRect, Color.WHITE,
					new Text(2, "Exit", Color.BLACK, exitRect));
			Rectangle levelModeRect = new Rectangle(GameFrame.WIDTH/2 - 230/2, GameFrame.HEIGHT/2 - 100, 230, 80);
			this.levelModeButton = new Button(levelModeRect, Color.WHITE,
					new Text(2, "Level Mode", Color.BLACK, levelModeRect));
			Rectangle tutorialRect = new Rectangle(GameFrame.WIDTH/2 - 230/2, GameFrame.HEIGHT/2, 230, 80);
			this.tutorialButton = new Button(tutorialRect, Color.WHITE,
					new Text(2, "Tutorial", Color.BLACK, tutorialRect));
			Rectangle retryRect = new Rectangle(GameFrame.WIDTH/2 - 150/2 - 250,
					GameFrame.HEIGHT/2 + 200, 150, 80);
			this.retryButton = new Button(retryRect, Color.WHITE,
					new Text(2, "Retry", Color.BLACK, retryRect));
			Rectangle mainMenuRect = new Rectangle(GameFrame.WIDTH/2 - 225/2 + 250,
					GameFrame.HEIGHT/2 + 200, 225, 80);
			this.mainMenuButton = new Button(mainMenuRect, Color.WHITE,
					new Text(2, "Main Menu", Color.BLACK, mainMenuRect));
			Rectangle backRect = new Rectangle(50, GameFrame.HEIGHT - 80 - 175, 90, 60);
			this.backButton = new Button(backRect, Color.WHITE,
					new Text(2, "Back", Color.BLACK, backRect));
			Rectangle secretRect = new Rectangle(GameFrame.WIDTH/2 - 25, 480, 50, 50);
			this.secretButton = new Button(secretRect, BLOOD_RED,
					new Text(2, "?", Color.WHITE, secretRect));
			Rectangle optionRect = new Rectangle(GameFrame.WIDTH/2 - 230/2, GameFrame.HEIGHT/2 + 100, 230, 80);
			this.optionButton = new Button(optionRect, Color.WHITE,
					new Text(2, "Options", Color.BLACK, optionRect));
			Rectangle resetDataRect = new Rectangle(GameFrame.WIDTH/2 - 250/2, GameFrame.HEIGHT/2, 250, 75);
			this.resetDataButton = new Button(resetDataRect, BLOOD_RED,
					new Text(2, "Reset Data", Color.WHITE, resetDataRect));

			Rectangle oneRect = new Rectangle(GameFrame.WIDTH/2 - 150 - 25, 350, 50, 50);
			Rectangle twoRect = new Rectangle(GameFrame.WIDTH/2 - 50 - 25, 350, 50, 50);
			Rectangle threeRect = new Rectangle(GameFrame.WIDTH/2 + 50 - 25, 350, 50, 50);
			Rectangle fourRect = new Rectangle(GameFrame.WIDTH/2 + 150 - 25, 350, 50, 50);
			if (data[0] == 1) {
				this.one = new Button(oneRect, Color.DARK_GRAY,
						new Text(2, "1", Color.WHITE, oneRect));
			} else {
				this.one = new Button(oneRect, Color.WHITE,
						new Text(2, "1", Color.BLACK, oneRect));
			}
			if (data[1] == 1) {
				this.two = new Button(twoRect, Color.DARK_GRAY,
						new Text(2, "2", Color.WHITE, twoRect));
			} else {
				this.two = new Button(twoRect, Color.WHITE,
						new Text(2, "2", Color.BLACK, twoRect));
			}
			if (data[2] == 1) {
				this.three = new Button(threeRect, Color.DARK_GRAY,
						new Text(2, "3", Color.WHITE, threeRect));
			} else {
				this.three = new Button(threeRect, Color.WHITE,
						new Text(2, "3", Color.BLACK, threeRect));
			}
			if (data[3] == 1) {
				this.four = new Button(fourRect, Color.DARK_GRAY,
						new Text(2, "4", Color.WHITE, fourRect));
			} else {
				this.four = new Button(fourRect, Color.WHITE,
						new Text(2, "4", Color.BLACK, fourRect));
			}
			this.secretText1 = new Text(1,
					"Remarkable.",
					Color.WHITE, new Rectangle(0, 0, GameFrame.WIDTH, GameFrame.HEIGHT/3));
			this.secretText2 = new Text(1,
					"You're the first in a few thousand years to complete the ritual.",
					Color.WHITE, new Rectangle(0, GameFrame.HEIGHT/3, GameFrame.WIDTH, GameFrame.HEIGHT/3));
			this.secretText3 = new Text(1,
					"What do you desire most, human?",
					Color.WHITE, new Rectangle(0, GameFrame.HEIGHT/3 * 2, GameFrame.WIDTH, GameFrame.HEIGHT/3));

			this.tutorialText = new ArrayList<Text>();

			tutorialText.add(new Text(3,
					"Tutorial",
					Color.WHITE, new Rectangle(GameFrame.WIDTH/2 - 75, 100, 100, 50)));
			tutorialText.add(new Text(1,
					"Goal: To collect the required number of rabbits indicated above the sacrificial pen.",
					Color.WHITE, new Rectangle(GameFrame.WIDTH/2, 200, 100, 50)));
			tutorialText.add(new Text(2,
					"CONTROLS",
					Color.WHITE, new Rectangle(400, 300, 100, 50)));
			tutorialText.add(new Text(1,
					"LEFT KEY - Move left.",
					Color.WHITE, new Rectangle(400, 350, 100, 50)));
			tutorialText.add(new Text(1,
					"RIGHT KEY - Move right.",
					Color.WHITE, new Rectangle(400, 400, 100, 50)));
			tutorialText.add(new Text(1,
					"UP KEY - Jump.",
					Color.WHITE, new Rectangle(400, 450, 100, 50)));
			tutorialText.add(new Text(1,
					"SPACE KEY - Pick up / drop rabbit.",
					Color.WHITE, new Rectangle(400, 500, 100, 50)));
			tutorialText.add(new Text(2,
					"TIPS",
					Color.WHITE, new Rectangle(1300, 300, 100, 50)));
			tutorialText.add(new Text(1,
					"1. You can only pick up one rabbit at a time.",
					Color.WHITE, new Rectangle(1300, 350, 100, 50)));
			tutorialText.add(new Text(1,
					"2. During the night, bats will attempt to eat rabbits.",
					Color.WHITE, new Rectangle(1300, 400 + 25, 100, 50)));
			tutorialText.add(new Text(1,
					"Shoo them away by touching them.",
					Color.WHITE, new Rectangle(1300, 450 + 25, 100, 50)));
			tutorialText.add(new Text(1,
					"3. The Goatman will wake up during Blood Hour",
					Color.WHITE, new Rectangle(1300, 500 + 50, 100, 50)));
			tutorialText.add(new Text(1,
					"and attempt to catch you. Hide in the",
					Color.WHITE, new Rectangle(1300, 550 + 50, 100, 50)));
			tutorialText.add(new Text(1,
					"Magic Circle to avoid him.",
					Color.WHITE, new Rectangle(1300, 600 + 50, 100, 50)));

			this.rabbitImage = new ImageIcon("src/images/rabbitMenu.gif");
			this.tutorialImage = new ImageIcon("src/images/tutorial.png");
			this.num = 0;
		}

		public void paintComponent(Graphics g) {   
			super.paintComponent(g);
			setDoubleBuffered(true);
			if (mainMenuStatus) { // if main menu
				mainMenu(g);
			} else if (levelModeStatus) { // if level mode
				levelMode(g);
			} else if (tutorialStatus) { // if tutorial
				tutorial(g);
			} else if (optionStatus) { // if options
				options(g);
			} else if (secretStatus) { // ???
				secret(g);
			} else if (gameStatus) { // if running game
				runGame(g);
			} else if (gameStatus == false){ // if game is over
				gameOver(g);
			}
		}
		/*
		 *creating main Menu
		 *rabbit is drawn into sacrificial pen when player places rabbit
		 */
		public void mainMenu(Graphics g) {
			mainMenuMusic.play();
			this.setBackground(Color.BLACK);
			titleText.draw(g);
			levelModeButton.draw(g);
			tutorialButton.draw(g);
			optionButton.draw(g);
			exitButton.draw(g);

			for (int i = 1; i < 18; i++) {
				g.drawImage(rabbitImage.getImage(), 100*i, GameFrame.HEIGHT - 150, null);
			}

			boolean clicked = gameMouseListener.isClicked();
			Point p = new Point(gameMouseListener.getX(), gameMouseListener.getY());
			if (levelModeButton.isButtonClicked(clicked, p)) {
				mainMenuStatus = false;
				levelModeStatus = true;
			} else if (tutorialButton.isButtonClicked(clicked, p)) {
				mainMenuStatus = false;
				tutorialStatus = true;
			} else if (optionButton.isButtonClicked(clicked, p)) {
				mainMenuStatus = false;
				optionStatus = true;
			} else if (exitButton.isButtonClicked(clicked, p)) {
				programStatus = false;
			}
		}

		//Drawing out different levels 
		public void levelMode(Graphics g) {
			this.setBackground(Color.BLACK);
			levelModeText.draw(g);
			one.draw(g);
			two.draw(g);
			three.draw(g);
			four.draw(g);
			backButton.draw(g);
			if (isComplete()) {
				secretButton.draw(g);
			}

			for (int i = 1; i < 18; i++) {
				g.drawImage(rabbitImage.getImage(), 100*i, GameFrame.HEIGHT - 150, null);
			}

			boolean clicked = gameMouseListener.isClicked();
			Point p = new Point(gameMouseListener.getX(), gameMouseListener.getY());
			if (one.isButtonClicked(clicked, p)) {
				mainMenuMusic.stop();
				level = 1;
				levelModeStatus = false;
				gameStatus = true;
				loadLevel();
			} else if (two.isButtonClicked(clicked, p)) {
				mainMenuMusic.stop();
				level = 2;
				levelModeStatus = false;
				gameStatus = true;
				loadLevel();
			} else if (three.isButtonClicked(clicked, p)) {
				mainMenuMusic.stop();
				level = 3;
				levelModeStatus = false;
				gameStatus = true;
				loadLevel();
			} else if (four.isButtonClicked(clicked, p)) {
				mainMenuMusic.stop();
				level = 4;
				levelModeStatus = false;
				gameStatus = true;
				loadLevel();
			} else if (backButton.isButtonClicked(clicked, p)) {
				levelModeStatus = false;
				mainMenuStatus = true;
			} else if (isComplete() && secretButton.isButtonClicked(clicked, p)) {
				levelModeStatus = false;
				secretStatus = true;
			}
		}

		public void tutorial(Graphics g) {
			this.setBackground(Color.BLACK);
			for (Text text : tutorialText) {
				text.draw(g);
			}
			backButton.draw(g);
			g.setColor(Color.WHITE);
			g.fillRect(350, GameFrame.HEIGHT/2 + 100, 325, 225);
			g.drawImage(tutorialImage.getImage(), 350, GameFrame.HEIGHT/2 + 100, null);

			for (int i = 1; i < 18; i++) {
				g.drawImage(rabbitImage.getImage(), 100*i, GameFrame.HEIGHT - 150, null);
			}

			boolean clicked = gameMouseListener.isClicked();
			Point p = new Point(gameMouseListener.getX(), gameMouseListener.getY());
			if (backButton.isButtonClicked(clicked, p)) {
				tutorialStatus = false;
				mainMenuStatus = true;
			}
		}

		public void options(Graphics g) {
			this.setBackground(Color.BLACK);
			resetDataButton.draw(g);
			backButton.draw(g);

			for (int i = 1; i < 18; i++) {
				g.drawImage(rabbitImage.getImage(), 100*i, GameFrame.HEIGHT - 150, null);
			}

			boolean clicked = gameMouseListener.isClicked();
			Point p = new Point(gameMouseListener.getX(), gameMouseListener.getY());
			if (backButton.isButtonClicked(clicked, p)) {
				optionStatus = false;
				mainMenuStatus = true;
			} else if (resetDataButton.isButtonClicked(clicked, p)) {
				resetDataButton.updateColors(Color.DARK_GRAY, Color.WHITE);
				try {
					resetData();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public void runGame(Graphics g) {
			// update clock, frameRate, and rabbitCounter
			clock.update();
			frameRate.update();
			double elapsedTime = clock.getElapsedTime();
			rabbitCounter = ((SacrificialPen)sacrificialPen).getNumRabbits();

			// switch section of time
			if (time.isTimeToChange()) {
				change();
				time.setTimeToChange(false);
			}

			// draw background
			this.setBackground(backgroundColor);
			if (state == 3) {
				if (backgroundColor == BLOOD_RED) {
					int randomNum = randomNum(1, 50);
					if (randomNum == 5) { // switch colors
						backgroundColor = Color.BLACK;
						num = 10;
					}
				} else {
					if (num == 0) {
						backgroundColor = BLOOD_RED;
					} else {
						num--;
					}
				}
			}

			int rightBounds = WORLD_SIZE[0] - wallArray.get(1).getWidth();

			// update player and camera
			gameKeyListener.movePlayer(player, rabbitArray, sacrificialPen);
			((Creature)player).update(elapsedTime, getItems(), rightBounds);
			updateCamera(g);

			// draw time and frameRate
			time.draw(g, GameFrame.WIDTH, camX, camY, level);
			frameRate.draw(g, 25 + camX, 25 + camY);

			// draw environment
			for (Item cloud : cloudArray) {
				((Creature)cloud).update(elapsedTime, getItems(), rightBounds);
				cloud.draw(g);
			}
			((SunMoon)sunMoon).update(state);
			sunMoon.draw(g);

			for (Item tree : treeArray) {
				tree.draw(g);
			}
			ground.draw(g);
			for (Item wall : wallArray) {
				wall.draw(g);
			}
			sacrificialPen.draw(g);
			boolean rabbitsGone = areRabbitsGone();
			if (rabbitsGone == false) {
				magicCircle.draw(g);
			}
			for (Item haystack : haystackArray) {
				haystack.draw(g);
			}
			player.draw(g);

			// update goatman
			if (state == 3) {
				((Goatman)goatman).setChasing(true);
			} else {
				((Goatman)goatman).setChasing(false);
			}
			if (rabbitsGone == true) {
				((Goatman)goatman).setRage(true);
			}
			gameStatus = ((Goatman)goatman).move(player, magicCircle);
			((Creature)goatman).update(elapsedTime, getItems(), rightBounds);
			goatman.draw(g);

			// update rabbits
			for (Item rabbit : rabbitArray) {
				//((Creature)rabbit).rabbitHop(elapsedTime, rabbitArray);
				//((Rabbit)rabbit).moveRabbits(rabbitArray);
				((Creature)rabbit).update(elapsedTime, getItems(), rightBounds);
				rabbit.draw(g);	
			}

			// update bats
			for (Item bat : batArray) {
				if (state == 1) {
					((Bat)bat).setHungry(true);
				}else if (state == 2) {
					((Bat)bat).setChasing(true);
				}else if (state == 3) {
					((Bat)bat).setChasing(false);
				}
				((Bat)bat).killRabbits(rabbitArray, player);
				((Creature)bat).update(elapsedTime, getItems(), rightBounds);
				bat.draw(g);
			}

			// check if win
			if (rabbitCounter == requiredRabbits) {
				gameStatus = false;
				winStatus = true;
			}

			// stop music
			if (gameStatus == false) {
				daylightMusic.stop();
				nightMusic.stop();
				bloodMusic.stop();
			}
		}

		public void gameOver(Graphics g) {
			if (winStatus) {
				winMusic.play();
				this.setBackground(Color.WHITE);
				winText.draw(g);
			} else {
				gameOverMusic.play();
				this.setBackground(Color.BLACK);
				gameOverText.draw(g);
			}

			retryButton.draw(g);
			mainMenuButton.draw(g);

			// restart
			boolean clicked = gameMouseListener.isClicked();
			Point p = new Point(gameMouseListener.getX(), gameMouseListener.getY());
			if (retryButton.isButtonClicked(clicked, p)) {
				if (winStatus) {
					data[level-1] = 1;
					try {
						saveProgress();
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (level == 1) {
						one.updateColors(BLOOD_RED, Color.WHITE);
					} else if (level == 2) {
						two.updateColors(BLOOD_RED, Color.WHITE);
					} else if (level == 3) {
						three.updateColors(BLOOD_RED, Color.WHITE);
					} else if (level == 4) {
						four.updateColors(BLOOD_RED, Color.WHITE);
					}
				}
				winMusic.stop();
				gameOverMusic.stop();
				loadLevel();
				gameStatus = true;
			} else if (mainMenuButton.isButtonClicked(clicked, p)) {
				if (winStatus) {
					data[level-1] = 1;
					try {
						saveProgress();
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (level == 1) {
						one.updateColors(Color.DARK_GRAY, Color.WHITE);
					} else if (level == 2) {
						two.updateColors(Color.DARK_GRAY, Color.WHITE);
					} else if (level == 3) {
						three.updateColors(Color.DARK_GRAY, Color.WHITE);
					} else if (level == 4) {
						four.updateColors(Color.DARK_GRAY, Color.WHITE);
					}
				}
				winMusic.stop();
				gameOverMusic.stop();
				winStatus = false;
				mainMenuStatus = true;
			}
		}

		public void secret(Graphics g) {
			this.setBackground(Color.BLACK);
			backButton.draw(g);
			secretText1.draw(g);
			secretText2.draw(g);
			secretText3.draw(g);

			boolean clicked = gameMouseListener.isClicked();
			Point p = new Point(gameMouseListener.getX(), gameMouseListener.getY());
			if (backButton.isButtonClicked(clicked, p)) {
				secretStatus = false;
				levelModeStatus = true;
			}
		}
	}
	//adding all items to one arrayList 
	public ArrayList<Item> getItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		items.addAll(this.wallArray);
		items.add(this.ground);
		items.add(this.player);
		items.add(this.goatman);
		items.add(this.sacrificialPen);
		items.addAll(this.rabbitArray);
		items.addAll(this.batArray);
		items.addAll(this.haystackArray);

		for (Item rabbit : this.rabbitArray){
			if (((Rabbit)rabbit).isVisible() == false){
				items.remove(rabbit);
			}
		}

		return items;
	}
	//Updating camera to follow player moving 
	public void updateCamera(Graphics g) {
		camX = player.getX() - (WIDTH/2);
		camY = player.getY() - (HEIGHT/2);
		if (camX > offsetMaxX) {
			camX = offsetMaxX;
		}else if (camX < offsetMinX) {
			camX = offsetMinX;
		}
		if (camY > offsetMaxY) {
			camY = offsetMaxY;
		}else if (camY < offsetMinY) {
			camY = offsetMinY;
		}
		g.translate(-camX, -camY);
	}

	//Changing music, colour, and state when switching to different tates
	public void change() {
		if (this.state == 1) {
			this.state = 2;
			this.time.setStateName("Night");
			this.backgroundColor = Color.DARK_GRAY;
			this.time.setTimeInterval(state);
			this.daylightMusic.stop();
			try {
				this.nightMusic.resetAudioStream();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
			this.nightMusic.play();
		}else if (state == 2) {
			this.state = 3;
			this.time.setStateName("Blood Hour");
			this.backgroundColor = BLOOD_RED;
			this.time.setTimeInterval(state);
			this.nightMusic.stop();
			try {
				this.bloodMusic.resetAudioStream();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
			this.bloodMusic.play();
		}else if (state == 3) {
			this.state = 1;
			this.time.setStateName("Daylight");
			this.backgroundColor = PALE_YELLOW;
			this.time.setTimeInterval(state);
			this.bloodMusic.stop();
			try {
				this.daylightMusic.resetAudioStream();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
			this.daylightMusic.play();
		}
	}

	public void initializeMusic()
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.mainMenuMusic = new AudioClip("mainMenu");
		this.daylightMusic = new AudioClip("daylight");
		this.nightMusic = new AudioClip("night");
		this.bloodMusic = new AudioClip("bloodHour");
		this.gameOverMusic = new AudioClip("gameOver");
		this.winMusic = new AudioClip("win");
	}

	public boolean areRabbitsGone() {
		for (Item rabbit : this.rabbitArray) {
			if (((Rabbit)rabbit).isVisible()) {
				return false;
			}
		}
		return true;
	}

	public boolean isComplete() {
		for (int i = 0; i < 4; i++) {
			if (data[i] == 0) {
				return false;
			}
		}
		return true;
	}

	public int randomNum(int start, int end) {
		return (int)(Math.random() * end + start);
	}

	public void saveProgress() throws Exception {
		File file = new File("src/savefiles/save.txt");
		PrintWriter output = new PrintWriter(file);
		for (int i = 0; i < 4; i++) {
			output.print(data[i] + " ");
		}
		output.close();
	}

	public void resetData() throws Exception {
		File file = new File("src/savefiles/save.txt");
		PrintWriter output = new PrintWriter(file);
		for (int i = 0; i < 4; i++) {
			output.print(0 + " ");
		}
		output.close();
		this.data = new int[] {0, 0, 0, 0};
	}

	public boolean isGameRunning() {
		return this.gameStatus;
	}

	public void setGameStatus(boolean gameStatus) {
		this.gameStatus = gameStatus;
	}

	public boolean isProgramRunning() {
		return programStatus;
	}
}