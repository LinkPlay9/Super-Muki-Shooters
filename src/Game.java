import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet {
	// Bilder,Spieler,Gegner,Projektile Deklarieren
	PImage bg, meme;
	Player Player1 = new Player(this);
	ArrayList<Enemy> ene = new ArrayList<Enemy>();
	ArrayList<Projectile> schuss = new ArrayList<Projectile>();
	ArrayList<ProjectileEnemy> schussGegner = new ArrayList<ProjectileEnemy>();
	int playerHitPoints = 100;
	boolean canShoot = true;
	int canShootCounter;
	clock tick = new clock();
	ProjectileEnemy kill2;

	public static void main(String[] args) {
		PApplet.main("Game");

	}

	public void setup() {
		frameRate(1000);
		// Gegner erzeugen
		tick.update();
		// Gegner erstellen
		for (int i = 0; i < 5; i++) {
			ene.add(new Enemy(this));
		}
		// Projektile für die Gegner erstellen
		for (int i = 0; i < ene.size() - 1; i++) {
			schussGegner.add(new ProjectileEnemy(this, ene, i));

		}

	}

	public void settings() {
		// CustomBackground mit der Auflösung 800x600sized d ad ad
		size(800, 600, P2D);
		bg = loadImage("data/Backgrounds/Level1.jpg");
		bg.resize(width, height);
		meme = loadImage("data/Backgrounds/nigga.jpg");
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

		// Gegner zeichnen, moven wenn Gegner durchkommt wird gegner schneller
		Enemy k = (new Enemy(this));
		for (int i = 0; i < ene.size() - 1; i++) {
			k = ene.get(i);
			k.drawEnemy();
			k.update();
			// GegnerProjectile erstellen und Schießen
			kill2 = new ProjectileEnemy(this, ene, i);
			kill2 = schussGegner.get(i);
			kill2.drawProjectileEnemy();
			kill2.shootEnemy();
			// Wenn Gegner durchkommt verliert Spieler 5 HP
			if (k.enemyThrough()) {
				playerHitPoints = playerHitPoints - 5;
				k.enemySpeedUp();
			}
			// Wenn GegnerKugel Player trifft verliert der 20 HP , NOCH NICHT
			// FERTIG !!
			if (Player1.y - kill2.y == 10) {
				playerHitPoints = playerHitPoints - 20;
			}
			if (playerHitPoints == 0) {
				ene.clear();

			}

		}

		// System.out.println(schussGegner.size());

		// Wenn spieler 0 hp hat dann nice meme
		if (playerHitPoints == 0) {
			textSize(50);
			fill(255, 0, 0);
			text("U LOST!", width / 2, height / 2);
			image(meme, width / 2, height / 2);
		}
		// HP-Anzeige
		fill(220, 153, 255);
		textSize(20);
		text("HP: " + (int) playerHitPoints, 720, 20);

		// Methode zum Schießen , KLAPPT
		msis();

		// Projectile erstellen & zeichnen & Schießen
		Projectile kill = new Projectile(this, Player1);
		for (int i = 0; i < schuss.size(); i++) {
			kill = schuss.get(i);
			kill.draw();
			kill.shoot();
			if (schuss.get(i).y <= 0) {
				schuss.remove(0);
			}
		}
		// System.out.println(schussGegner.size());
	}

	// Methode zum Schießen , KLAPPT !
	public void msis() {
		if (mousePressed) {
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