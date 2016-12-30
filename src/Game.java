import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Game extends PApplet {
	// Bilder,Spieler,Gegner,Projektile Deklarieren
	PImage bg; //Hintergrundbild (Sterne)
	PImage lost, won; //lose & win Bilder
	PImage startscreen, playerselect; //Menüs
	PImage playbutton, playbuttonhvr; //Buttons
	PImage player, enmey; //Spieler
	ArrayList<Enemy> ene = new ArrayList<Enemy>();
	ArrayList<Projectile> schussPlayer = new ArrayList<Projectile>();
	ArrayList<ProjectileEnemy> schussGegner = new ArrayList<ProjectileEnemy>();
	private static int playerHitPoints = 100; //Leben
	public int points = 0;
	boolean canShoot = true;
	boolean setup = true;
	int canShootCounter;
	public int gamestate = 0;
	boolean drogenmode = false;
	Clock tick = new Clock(); //Clock für FPS-Unabhängige Animation (vielen Dank Fabian Fritzsche)
	int startX, startY, startSize;
	boolean shh = false;
	PFont lot,roboto;
	Background[] p = new Background[250];
	public int charactersel;//Spieler Character, bestimmt welches Bild für den Spieler geladen wird

	public static void main(String[] args) {
		PApplet.main("Game");
	}

	public void setup() {
		frameRate(1000);
		tick.update();
		//Background erzeugen
		for (int i = 0; i < p.length; i++) {
			p[i] = new Background(this);
		}
		lot = createFont("data/LOT.otf",32);
		roboto = createFont("data/Roboto-Regular.ttf",32);
	}

	public void settings() {
		// CustomBackground mit der Auflösung 800x600size
		size(800, 600, P2D);
		bg = loadImage("data/Backgrounds/Level1.jpg");	
		lost = loadImage("data/Backgrounds/gameover.png");
		won = loadImage("data/Backgrounds/won.png");
		startscreen = loadImage("data/start.png");
		playerselect = loadImage("data/playerselect.png");
		playbutton = loadImage("data/Button/play.png");
		playbuttonhvr = loadImage("data/Button/playhovr.png");
		//player = loadImage("data/player.png");
		loop();
	}

	Player Player1 = new Player(this);
	
	public void draw() {
		noStroke();
		//drogenmode = true; //Drogenmode disabelt den Background so das alles eine Linie hinter sich her zieht
		if(!drogenmode){
			background(bg);	
		}
		tick.update();
		textFont(lot);
		
		// Background Animation Methode
		for (int i = 0; i < p.length; i++) {
			p[i].fall();
			p[i].show();
		}
		
		/*	Gamestate Erklärung:
		 *	Gamestate steuert in welchem Menü / Level sich das Spiel befindet
		 *	
		 *	0 = Startscreen: Titel, Play Button, Credits
		 *
		 *	Menüs beginnen mit 1*
		 *	11 = Character Menü
		 *
		 *	Levels sind Eintellig und beginnen mit 1
		 *	1 = Level 1 
		 */
		
		// Menu
		if (gamestate == 0) {
			noStroke();
			tick.update();
			surface.setTitle("Super Muki Shooter");
			image(startscreen, 0, 0);
			fill(3, 169, 244);
			stroke(255);
			fill(255, 0, 0);
			int rectX = 275;
			int rectY = 175;
			int rectSize = 250;
			if (mouseX >= rectX && mouseX <= rectX + rectSize && mouseY >= rectY && mouseY <= rectY + rectSize) {
				fill(0, 255, 0);
				image(playbuttonhvr, 0, 0);
				if (mousePressed) {
					gamestate = 11;
				}
			} else {
				image(playbutton, 0, 0);
			}
		}
		
		//Character Menü
		if (gamestate == 11){
			noStroke();
			tick.update();
			surface.setTitle("SMS - Select your Player");
			image(playerselect, 0, 0);
			
			//Mathaan Button
			if (mouseX >= 301 && mouseX <= 379 && mouseY >= 164 && mouseY <= 280){
				if (mousePressed){
					charactersel = 2; //Setze Mathaan als Spieler
					gamestate = 1; //Wähle Level 1
				}
			}
			
			//Zelle Button
			if (mouseX >= 414 && mouseX <= 493 && mouseY >= 164 && mouseY <= 278){
				if (mousePressed){
					charactersel = 4; //Setze Mathaan als Spieler
					gamestate = 1; //Wähle Level 1
				}
			}
			
			//Panna Button
			if (mouseX >= 297 && mouseX <= 379 && mouseY >= 320 && mouseY <= 431){
				if (mousePressed){
					charactersel = 1; //Setze Mathaan als Spieler
					gamestate = 1; //Wähle Level 1
				}
			}
			
			//Toni Button
			if (mouseX >= 420 && mouseX <= 500 && mouseY >= 320 && mouseY <= 433){
				if (mousePressed){
					charactersel = 3; //Setze Mathaan als Spieler
					gamestate = 1; //Wähle Level 1
				}
			}
					
		}

		// Level 1
		if (gamestate == 1) {		
			if (setup) {
				// Gegner erstellen
				for (int i = 0; i < 10; i++) {
					ene.add(new Enemy(this));
				}
				// Projektile für die Gegner erstellen
				for (int i = 0; i < ene.size() - 1; i++) {
					schussGegner.add(new ProjectileEnemy(this, ene, i));
				}
				// setup ausschalten
				setup = false;
			}
			surface.setTitle("SMS - Level 1");
			// FPS-ANzeige
			fill(255,0,0);
			textFont(roboto);
			textSize(20);
			textAlign(LEFT);
			text("FPS: " + (int) frameRate, 5, 20);
			
			//Statusanzeige
			textAlign(RIGHT);
			textFont(lot);
			textSize(20);
			fill(255);
			text("Points: " + points, this.width-5, 40); //Punkte
			text("HitPoints: " + (int) playerHitPoints, this.width-5, 20);	//HP

			// Spieler erzeugen
			Player1.drawPlayer(charactersel);

			// Keyevents Spieler
			Player1.movePlayer();

			// Gegner zeichnen, moven, wenn Gegner durchkommt wird gegner
			// schneller
			for (int i = 0; i < ene.size() - 1; i++) {
				ene.get(i).drawEnemy();
				ene.get(i).update();
				if (ene.get(i).y >= 0) {
					shh = true;
				}
				if (ene.get(i).y >= 600 - 25) {
					playerHitPoints = playerHitPoints - 5;
					ene.get(i).enemyRandomSpawn();
				}
			}
			// Bugfix mit dem Gegner
			if (ene.size() == 1) {
				ene.clear();
			}

			// Projectile für den Player erstellen & zeichnen & Schießen &&
			// Wenn Projektil ausserhalb Fenster dann aus ArrayList Löschen
			for (int i = 0; i < schussPlayer.size(); i++) {
				schussPlayer.get(i).draw();
				schussPlayer.get(i).shoot();
				if (schussPlayer.get(i).y <= 0) {
					schussPlayer.remove(i);
				}
			}

			// GegnerProjectile zeichnen und Schießen
			if (shh) {
				for (int i = 0; i < schussGegner.size(); i++) {
					schussGegner.get(i).drawProjectileEnemy();
					schussGegner.get(i).shootEnemy();
					if (schussGegner.get(i).y >= 600 - 5) {
						schussGegner.remove(i);
						shh = false;
					}
				}
			}
			// Wenn Spieler verliert
			if (playerHitPoints <= 0) {
				playerHitPoints = 0;
				ene.clear();
				fill(255, 0, 0);
				//text("U LOST!", width / 2, height / 2);
				image(lost,0,0);
			}
			// Wenn Spieler gewinnt
			else if (playerHitPoints > 0 && ene.isEmpty()) {
				fill(255, 0, 0);
				image(won, 0, 0);
			}
		}
		// Methode zum Schießen , KLAPPT
		shootMethod();
		// wenn gegner getroffen wird dann gegner tot
		ifEnemyHit();
		// wenn Player getroffen wird
		ifPlayerHit();
	}

	// Methode zum Schießen , KLAPPT !
	public void shootMethod() {
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
			if (canShootCounter == 20)/*
										 * change this number to change the
										 * duration
										 */ {
				canShoot = true;
			}
		}
	}

	public void ifEnemyHit() {
		for (int i = 0; i < ene.size() - 1; i++) {
			for (int j = 0; j < schussPlayer.size(); j++) {
				if (PApplet.dist(schussPlayer.get(j).x + 10, schussPlayer.get(j).y + 10, ene.get(i).x + 25,
						ene.get(i).y + 25) <= 20) {
					points = points + 10;
					ene.remove(i);
					schussPlayer.remove(j);
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
