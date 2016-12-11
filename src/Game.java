import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet {
	// Bilder,Spieler,Gegner,Projektile Deklarieren
	PImage bg, meme;
	Player Player1 = new Player(this);
	Enemy[] Enemy1 = new Enemy[9];
	ArrayList<Projectile> schuss = new ArrayList<Projectile>();
	boolean nigga = false;

	public static void main(String[] args) {
		PApplet.main("Game");

	}

	public void setup() {
		frameRate(1000);
		// Gegner erzeugen
		for (int i = 0; i < Enemy1.length; i++) {
			Enemy1[i] = new Enemy(this);

		}

		// for (int i = 0; i < 10; i++) {
		// schuss.add(new Projectile(this, Player1));
		// }

	}

	public void settings() {
		// CustomBackground mit der Auflösung 800x600sized d ad ad
		size(800, 600, P2D);
		bg = loadImage("data/Backgrounds/Level1.jpg");
		bg.resize(width, height);
		loop();
	}

	public void draw() {
		background(bg);
		schuss.add(new Projectile(this, Player1));
		fill(220, 153, 255);
		textSize(20);
		text("FPS: " + (int) frameRate, 0, 20);

		// Spieler erzeugen
		Player1.drawPlayer();

		// Keyevents Spieler
		Player1.movePlayer();

		// Gegner zeichnen
		for (int i = 0; i < Enemy1.length; i++) {
			fill(255, 255, 0);
			Enemy1[i].drawAndMoveEnemy();

		}

		// Projektil Zeichnen und bei KeyEvent schießen
		for (int i = 0; i < schuss.size(); i++) {
			Projectile j = schuss.get(i);
			j.drawPro();
			j.shoot();
			if (j.y <= Player1.y) {
				nigga = true;
			}
			if (nigga) {
				if (key == ' ') {
					schuss.remove(schuss.size() - 1);
					j.drawPro();
					j.shoot();
					nigga = false;

				}

			}

		}

	}

}
