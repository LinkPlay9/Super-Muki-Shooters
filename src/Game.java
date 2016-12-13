import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet {
	// Bilder,Spieler,Gegner,Projektile Deklarieren
	PImage bg, lost, won;
	Player Player1 = new Player(this);
	ArrayList<Enemy> ene = new ArrayList<Enemy>();
	ArrayList<Projectile> schussPlayer = new ArrayList<Projectile>();
	ArrayList<ProjectileEnemy> schussGegner = new ArrayList<ProjectileEnemy>();
	private static int playerHitPoints = 100;
	public int points = 0;
	boolean canShoot = true;
	int canShootCounter;
	Clock tick = new Clock();

	public static void main(String[] args) {
		PApplet.main("Game");

	}

	public void setup() {
		frameRate(1000);
		// Gegner erzeugen
		tick.update();
		// Gegner erstellen
		for (int i = 0; i < 15; i++) {
			ene.add(new Enemy(this));
		}
		// Projektile für die Gegner erstellen
		for (int i = 0; i < ene.size(); i++) {
			schussGegner.add(new ProjectileEnemy(this, ene, i));
		}
	}

	public void settings() {
		// CustomBackground mit der Auflösung 800x600sized d ad ad
		size(800, 600, P2D);
		bg = loadImage("data/Backgrounds/Level1.jpg");
		bg.resize(width, height);
		lost = loadImage("data/Backgrounds/nigga.jpg");
		won = loadImage("data/Backgrounds/won.jpg");
		loop();

	}

	public void draw() {
		noStroke();
		background(bg);
		tick.update();

		// FPS-ANzeige
		fill(220, 153, 255);
		textSize(20);
		text("FPS: " + (int) frameRate, 0, 20);

		// Points anzeige
		fill(220, 153, 255);
		textSize(20);
		text("Points: " + points, 690, 40);

		// HP-Anzeige
		fill(220, 153, 255);
		textSize(20);
		text("HP: " + (int) playerHitPoints, 720, 20);

		// Spieler erzeugen
		Player1.drawPlayer();

		// Keyevents Spieler
		Player1.movePlayer();

		// Gegner zeichnen, moven, wenn Gegner durchkommt wird gegner schneller
		for (int i = 0; i < ene.size(); i++) {
			ene.get(i).drawEnemy();
			ene.get(i).update();
			if (ene.get(i).y >= 600 - 25) {
				playerHitPoints = playerHitPoints - 5;
				ene.get(i).enemyRandomSpawn();
			}
		}

		// GegnerProjectile zeichnen und Schießen
		for (int i = 0; i < schussGegner.size(); i++) {
			schussGegner.get(i).drawProjectileEnemy();
			schussGegner.get(i).shootEnemy();
			if (schussGegner.get(i).y >= 600 - 5) {
				schussGegner.remove(i);
			}
		}

		// Projectile für den Player erstellen & zeichnen & Schießen &&
		// Wenn Projektil ausserhalb Fenster dann aus ArrayList Löschen
		for (int i = 0; i < schussPlayer.size(); i++) {
			schussPlayer.get(i).draw();
			schussPlayer.get(i).shoot();
			if (schussPlayer.get(i).y <= 0) {
				schussPlayer.remove(0);
			}
		}
		// Methode zum Schießen , KLAPPT
		msis();
		// wenn gegner getroffen wird dann gegner tot
		ifEnemyHit();
		// wenn Player getroffen wird
		ifPlayerHit();

		if (playerHitPoints <= 0) {
			playerHitPoints = 0;
			ene.clear();
			fill(255, 0, 0);
			text("U LOST!", width / 2, height / 2);
			image(lost, width / 2, height / 2);
		}

		else if (playerHitPoints >= 0 && ene.isEmpty()) {
			fill(255, 0, 0);
			image(won, 300, 100);
		}
	}

	// Methode zum Schießen , KLAPPT !
	public void msis() {
		if (KeyHandler.keySpace) {
			// this regulates the shooting speed
			if (canShoot == true) {
				schussPlayer.add(new Projectile(this, Player1));
				canShoot = false;
				canShootCounter = 0;
			}
		}
		// this checks if the right amount of time has passed before canShoot
		// can = true again
		if (canShoot == false) {
			canShootCounter++;
			// if the right amount of time has passed. make canShoot true
			if (canShootCounter == 30)/*
										 * change this number to change the
										 * duration
										 */ {
				canShoot = true;
			}
		}
	}

	public void ifEnemyHit() {
		for (int i = 0; i < schussPlayer.size(); i++) {
			for (int j = 0; j < ene.size(); j++) {
				if (PApplet.dist(schussPlayer.get(i).x + 10, schussPlayer.get(i).y + 10, ene.get(j).x + 25,
						ene.get(j).y + 25) <= 20) {
					points = points + 10;
					ene.remove(j);
					schussPlayer.remove(i);
				}
			}
		}
	}

	public void ifPlayerHit() {
		for (int i = 0; i < schussGegner.size(); i++) {
			if (PApplet.dist(schussGegner.get(i).x + 10, schussGegner.get(i).y + 10, Player1.x + 50,
					Player1.y + 50) <= 40) {
				playerHitPoints = playerHitPoints - 10;
				schussGegner.remove(i);

			}
		}
	}

	public void keyPressed() {
		KeyHandler.setMove(keyCode, true);
	}

	public void keyReleased() {
		KeyHandler.setMove(keyCode, false);
	}
}
