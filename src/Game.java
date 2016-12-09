import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet {
	PImage bg;

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

		loop();
	}

	// Spieler, Gegner,Projektil deklarieren
	Player Player1 = new Player(this);
	Enemy Enemy1 = new Enemy(this);
	Projectile x = new Projectile(this, Player1);
	boolean sh = false;

	public void draw() {
		background(bg);

		fill(255, 255, 255);
		textSize(20);
		text("FPS: " + (int) frameRate, 0, 20);

		// SPieler zeichnen
		Player1.drawPlayer();

		// Gegner zeichnen
		fill(0, 255, 0);
		Enemy1.drawEnemy();

		// Wenn Gegner Getroffen wird
		if (x.x >= Enemy1.x && Enemy1.y >= x.y) {
			Enemy1.stopEnemy();
		} else {
			Enemy1.moveEnemy();

		}

		// KeyEvents für Spieler
		if (key == ' ') {
			sh = true;
		}

		if (sh) {
			fill(255, 153, 255);
			x.shoot();

		}

		if (keyPressed) {

			if (key == 'a' || key == 'A') {
				Player1.movePlayer(-2.5f);
				if (sh == false) {
					x.x += -2.5f;

				}
			}

			if (key == 'd' || key == 'D') {

				Player1.movePlayer(2.5f);
				if (sh == false) {
					x.x += 2.5f;

				}

			}

			if (key == ESC) {
				System.exit(1);

			}

			// Damit der Spieler nicht aus dem Feld kann
			if (Player1.x <= 0) {
				Player1.x = 0;
			}
		}

		if (Player1.x >= 749) {
			Player1.x = 749;

		}

	}

}
