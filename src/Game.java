import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet {
	// Bilder,Spieler,Gegner,Projektile Deklarieren
	PImage bg, meme;
	Player Player1 = new Player(this);
	Enemy[] Enemy1 = new Enemy[20];
	ArrayList<Projectile> schuss = new ArrayList<Projectile>();
	boolean nigga, sh = false;
	boolean canShoot = true;
	int canShootCounter;
	clock tick = new clock();

	public static void main(String[] args) {
		PApplet.main("Game");

	}

	public void setup() {
		frameRate(60);
		// Gegner erzeugen
		tick.update();
		for (int i = 0; i < Enemy1.length; i++) {
			Enemy1[i] = new Enemy(this);

		}

	}

	public void settings() {
		// CustomBackground mit der Auflösung 800x600sized d ad ad
		size(800, 600, P2D);
		bg = loadImage("data/Backgrounds/Level1.jpg");
		bg.resize(width, height);
		loop();
	}

	public void draw() {
		noStroke();
		background(bg);
		tick.update();
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

		// Methode zum Schießen , KLAPPT !
		msis();

		// Projectile erstellen & zeichnen
		Projectile kill = new Projectile(this, Player1);
		for (int i = 0; i < schuss.size(); i++) {
			kill = schuss.get(i);
			kill.draw();
			schuss.get(i).shoot();
			if (schuss.get(i).y <= 0) {
				schuss.remove(0);

			}
		}
		// Zähler
		//System.out.println(schuss.size());

	}

	// Methode zum Schießen , KLAPPT !
	public void msis() {
		if (mousePressed == true) {
			// this regulates the shooting speed
			if (canShoot == true) {
				schuss.add(new Projectile(this, Player1));
				canShoot = false;
				canShootCounter = 0;
			}
		}
		// this checks if the right amount of time has passed before canShoot
		// can = true again
		if (canShoot == false) {
			canShootCounter++;
			// if the right amount of time has passed. make canShoot true
			if (canShootCounter == 35)/*
										 * change this number to change the
										 * duration
										 */ {
				canShoot = true;
			}
		}
	}

}
