import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet {
	PImage bg, meme;

	// Deklaration Spieler & Gegner

	public static void main(String[] args) {
		PApplet.main("Game");

	}

	public void setup() {
		frameRate(1000);

	}

	public void settings() {

		// CustomBackground mit der Auflösung 800x600
		size(800, 600, P2D);
		// fullScreen(P2D);

		bg = loadImage("data/Backgrounds/Level1.jpg");
		bg.resize(width, height);

		meme = loadImage("data/Backgrounds/nigga.jpg");
		meme.resize(150, 150);
		loop();
	}

	// Spieler, Gegner,Projektil deklarieren
	Player Player1 = new Player(this);
	Enemy Enemy1 = new Enemy(this);
	Projectile x = new Projectile(this, Player1);
	Projectile z = new Projectile(this, Player1);
	boolean sh = false;
	boolean ak = false;

	public void draw() {
		background(bg);

		fill(255, 255, 255);
		textSize(20);
		text("FPS: " + (int) frameRate, 0, 20);

		// SPieler zeichnen
		fill(0, 255, 0);
		Player1.drawPlayer();

		// Gegner zeichnen
		fill(255, 0, 0);
		Enemy1.drawEnemy();
		Enemy1.moveEnemy();

		// Wenn Gegner Durchkommt
		if (Enemy1.y >= 574) {
			textSize(32);
			fill(153, 255, 255);
			text("VERLOREN BITCH ! ", 245, 150);
			image(meme, 275, 165);
		}

		// KeyEvents für Spieler
		if (key == ' ') {
			sh = true;

		}

		if (sh) {
			fill(255, 153, 255);
			x.shoot();

		}

		if (key == 'f') {

			ak = true;

		}

		if (ak) {
			fill(0, 255, 0);
			z.shoot();

		}
		if (keyPressed) {

			if (key == 'a' || key == 'A') {
				Player1.movePlayer(-2.5f);
				if (sh == false) {
					x.x += -2.5f;
				}
				if (ak == false) {
					z.x += -2.5f;
				}
			}

			if (key == 'd' || key == 'D') {
				Player1.movePlayer(2.5f);
				if (sh == false) {
					x.x += 2.5f;
				}
				if (ak == false) {
					z.x += 2.5f;
				}
			}

			if (key == ESC)

			{
				System.exit(1);

			}
		}
	}
}
