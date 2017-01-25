import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Game extends PApplet {
	//Bilder,Spieler,Gegner,Projektile Deklarieren
	PImage bg; //Hintergrundbild (Sterne)
	PImage lost, won; //lose & win Bilder
	PImage startscreen, playerselect; //Menüs
	PImage toniBio,panaBio, mathaanBio, zelleBio; //Steckbrife
	PImage playbutton, playbuttonhvr; //Buttons
	PImage player, enmey; //Spieler Objekt, Gegner Objekt
	PImage smeme, smade, slogo; //Bilder für das Intro
	ArrayList<Enemy> ene = new ArrayList<Enemy>();//Enemy Array, steuert Enemys
	ArrayList<Projectile> schussPlayer = new ArrayList<Projectile>();//Projektil Array, steuert Projektile des Spielers
	ArrayList<ProjectileEnemy> schussGegner = new ArrayList<ProjectileEnemy>();//Gegner Projektil Array, steuert Projektile der Gegner
	private static int playerHitPoints = 100; //Leben des Spielers
	public int points = 0;//Punkte des Spielers, löschen sich wenn er verliert
	boolean canShoot = true;
	float canShootCounter;//Zähler für Schussrate des Spielers
	boolean enemySetup = true;//Gegner werden nur einmal im Draw erstellt
	public int gamestate = 10; //spiel startet im intro
	Clock tick = new Clock(); //Clock für FPS-Unabhängige Animation (vielen Dank an Fabian)
	PFont lot,roboto;//Schriftarten fürs Spiel
	//Quelle der Fonts:
	//LOT free Font: http://www.fontfabric.com/lot-free-font/
	//Roboto Font: https://fonts.google.com/specimen/Roboto
	Background[] p = new Background[250];//Hintergrund Partikel
	public int charactersel;//Spieler Character, bestimmt welches Bild für den Spieler geladen wird
	//Zeit Variablen für Menüs
	long curtime = 0;
	long nexttime = 0;
	long introtime = 0;
	long timedif = 0;
	long starttime = System.currentTimeMillis();
	boolean disableShoot = false;//Schaltet schießen aus wenn man verliert / gewinnt
	int eneCount = 5; // Anzahl der Gegner
	int level = 1;	// Level Nummer
	ArrayList<Integer> highscores = new ArrayList<Integer>();
	
	
	public static void main(String[] args) {
		PApplet.main("Game");
	}

	public void setup() {
		frameRate(60);
		surface.setResizable(false); 
		tick.update();	//vielen Dank an Fabian
		sound.setupSoundEngine(this); //vielen Dank an Fabian
		//Background erzeugen
		for (int i = 0; i < p.length; i++) {
			p[i] = new Background(this);
		}
		//Bilder Laden
		lot = createFont("data/LOT.otf",32);
		roboto = createFont("data/Roboto-Regular.ttf",32);
		bg = loadImage("data/Backgrounds/Level1.jpg");	
		lost = loadImage("data/Backgrounds/gameover.png");
		won = loadImage("data/Backgrounds/won.png");
		startscreen = loadImage("data/start.png");
		playerselect = loadImage("data/playerselect.png");
		playbutton = loadImage("data/Button/play.png");
		playbuttonhvr = loadImage("data/Button/playhovr.png");
		smeme = loadImage("data/splash/memebois.png");
		smade = loadImage("data/splash/made with.png");
		slogo = loadImage("data/splash/logo.png");
		toniBio = loadImage("data/Player/toniBio.png");
		zelleBio = loadImage("data/Player/zelleBio.png");
		panaBio = loadImage("data/Player/panaBio.png");
		mathaanBio = loadImage("data/Player/mathaanBio.png");
	}

	public void settings() {
		size(800, 600, P2D);
	}

	Player Player1 = new Player(this);
	boolean isetup = true;
	public void draw() {
		noStroke();
		background(0);
		image(bg,0,0);
		tick.update();	//vielen Dank an Fabian
		textFont(lot);
		// Background Animation Methode
		for (int i = 0; i < p.length; i++) {
			p[i].fall();
			p[i].show();
		}
		
		/*	Gamestate Erklärung:
		 *	Gamestate steuert in welchem Menü / Level sich das Spiel befindet
		 *	
		 *	10 = Intro
		 *
		 *	0 = Startscreen: Titel, Play Button
		 *
		 *	Menüs beginnen mit 1*
		 *	11 = Character Menü
		 *
		 *	Level
		 *	1 = Game (beinhaltet Endstate)
		 */
		
		if(isetup){
			starttime = System.currentTimeMillis();
			isetup = false;
		}
		
		//Intro
		if (gamestate == 10){
			surface.setTitle("Super Muki Shooter");
			introtime = System.currentTimeMillis();
			
			timedif = introtime - starttime;
			
			if(mousePressed){
				gamestate = 0;
				curtime = System.currentTimeMillis();
			}
			
			if(timedif >= 0 && timedif <= 3000){
				image(smeme, 0, 0);
			}else if(timedif >= 3000 && timedif <= 6000){
				image(smade, 0, 0);
			}else if(timedif >= 6000 && timedif <= 9000){
				image(slogo, 0, 0);
			}else{
				gamestate = 0;
			}
		}
		
		// Menu
		if (gamestate == 0){
			//sound.controlMusic(sound.music[0], "play");
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
			nexttime = System.currentTimeMillis();
			if (mouseX >= rectX && mouseX <= rectX + rectSize && mouseY >= rectY && mouseY <= rectY + rectSize) {
				fill(0, 255, 0);
				image(playbuttonhvr, 0, 0);
				if(nexttime >= curtime+300){
					if (mousePressed) {
						gamestate = 11;
						curtime = System.currentTimeMillis();
					}
				}
			} else {
				image(playbutton, 0, 0);
			}
		}
		
		//Character Menü
		if (gamestate == 11){
			noStroke();
			tick.update();
			surface.setTitle("Super Muki Shooter - Select your Player");
			image(playerselect, 0, 0);
			int playerh = 230;
			int bsize = 140;
			nexttime = System.currentTimeMillis();
			
			//Highscore
			highscores.add(points);
			textAlign(CENTER);
			textFont(lot);
			textSize(40);
			fill(255);
			text("Highscore: " + Collections.max(highscores), this.width/2, this.height-100);
			
			if(nexttime >= curtime+300){
				//Peter Button
				if (mouseX >= 30 && mouseX <= 30+bsize && mouseY >= playerh && mouseY <= playerh+bsize){
					image(panaBio,0,0);
					if (mousePressed){
						charactersel = 1; //Setze Peter als Spieler
						gamestate = 1; //Wähle Level 1
					}
				}
				
				//Mathaan Button
				if (mouseX >= 230 && mouseX <= 230+bsize && mouseY >= playerh && mouseY <= playerh+bsize){
					image(mathaanBio,0,0);
					if (mousePressed){
						charactersel = 2; //Setze Mathaan als Spieler
						gamestate = 1; //Wähle Level 1
					}
				}
				
				//Toni Button
				if (mouseX >= 430 && mouseX <= 430+bsize && mouseY >= playerh && mouseY <= playerh+bsize){
					image(toniBio,0,0);
					if (mousePressed){
						charactersel = 3; //Setze Toni als Spieler
						gamestate = 1; //Wähle Level 1
					}
				}
				
				//Zelle Button
				if (mouseX >= 630 && mouseX <= 630+bsize && mouseY >= playerh && mouseY <= playerh+bsize){
					image(zelleBio,0,0);
					if (mousePressed){
						charactersel = 4; //Setze Zelle als Spieler
						gamestate = 1; //Wähle Level 1
					}
				}
			}
		}

		// Level 1
		if (gamestate == 1) {		
			if (enemySetup) {
				// Gegner erstellen
				for (int i = 0; i <= eneCount; i++) {
					ene.add(new Enemy(this));
				}
				// setup ausschalten
				enemySetup = false;
			}
			surface.setTitle("Super Muki Shooter - Level: "+level);
			fill(255,0,0);
			textFont(roboto);
			textSize(20);
			textAlign(LEFT);
			// FPS-ANzeige
			//text("FPS: " + (int) frameRate, 5, 20);
			
			//Statusanzeige
			textAlign(RIGHT);
			textFont(lot);
			textSize(20);
			fill(255);
			text("Points: " + points, this.width-5, 40); //Punkte
			//text("HitPoints: " + (int) playerHitPoints, this.width-5, 20);	//HP
			text("HP:", this.width-110, 20);
			fill(255,0,0);
			rect(this.width-105, 5, 100, 20,4);
			fill(0,255,0);
			rect(this.width-105, 5, playerHitPoints, 20,4);
		

			// Spieler erzeugen
			Player1.drawPlayer(charactersel);

			// Keyevents Spieler
			Player1.movePlayer();

			// Projektile für die Gegner erstellen und schießen
			for (int i = 0; i < ene.size() - 1; i++) {
				if(ene.get(i).y >= 0){
					if(ene.get(i).canShoot == true){
						schussGegner.add(new ProjectileEnemy(this, ene, i));
						ene.get(i).canShoot = false;
						ene.get(i).canShootCounter=0;
					}
					if(ene.get(i).canShoot == false){
						ene.get(i).canShootCounter = ene.get(i).canShootCounter + Clock.elapsedTime * random(0,1);
						if(ene.get(i).canShootCounter >= 1.5){ //schießgesch. des gegners
							ene.get(i).canShoot = true;
						}
					}
				}
				
			}
			
			// Gegner zeichnen, moven, wenn Gegner durchkommt wird gegner
			// schneller
			for (int i = 0; i < ene.size() - 1; i++) {
				ene.get(i).drawEnemy();
				ene.get(i).update();
				if (ene.get(i).y >= 0) {
					for (int j = 0; j < schussGegner.size(); j++) {
						schussGegner.get(j).drawProjectileEnemy();
//						schussGegner.get(j).shootEnemy();
						if (schussGegner.get(j).y >= 600 - 5) {
							schussGegner.remove(j);
//							schussGegner.add(new ProjectileEnemy(this, ene, i));
						}
					}
				}
				if (ene.get(i).y >= 600) {
//					System.out.println("-5 hp, gegner entwischt");
					playerHitPoints = playerHitPoints - 5;
//					ene.remove(i);
					ene.get(i).enemyRandomSpawn();
					ene.get(i).canShoot = true;
				}
			}
			
			for(int i = 0; i < schussGegner.size(); i++){
				schussGegner.get(i).shootEnemy();
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

			//ENDSTATES
			
			//Highscore			
			highscores.add(points);					
			
			// Wenn Spieler verliert
			if (playerHitPoints <= 0) {
				schussPlayer.clear();
				schussGegner.clear();
				disableShoot = true;
				playerHitPoints = 0;
				points = 0;
				level = 1;
				eneCount = 5;
				ene.clear();
				fill(255, 0, 0);
				//text("U LOST!", width / 2, height / 2);
				image(lost,0,0);
				Player1.y = 600;
				if (mousePressed) {
					restart();
				}
			}
			// Wenn Spieler gewinnt
			else if (playerHitPoints > 0 && ene.isEmpty()) {
				schussPlayer.clear();
				schussGegner.clear();
				disableShoot = true;
				fill(255);
				image(won, 0, 0);
				textAlign(CENTER);
				textSize(64);
				text("Points: " + points, this.width / 2, 120); //Punkte
				if (mousePressed) {
					eneCount = eneCount +5; //mehr gegner im nächsten level.
					level++;
					restart();
				}
			}
		}
		// Methode zum Schießen , KLAPPT
		shootMethod();
		// wenn gegner getroffen wird dann gegner tot
		ifEnemyHit();
		// wenn Player getroffen wird
		ifPlayerHit();
	}

	public void restart(){
		schussPlayer.clear();
		schussGegner.clear();
		playerHitPoints = 100;
		enemySetup = true;
		Player1.y = 600 -100;
		Player1.x = 800 / 2;
		disableShoot = false;
		gamestate = 0;
	}
	
	// Methode zum Schießen , KLAPPT !
	//Quelle: https://www.openprocessing.org/sketch/118081
	public void shootMethod() {
		if (KeyHandler.keySpace) {
			if(!disableShoot){
				// this regulates the shooting speed
				if (canShoot == true) {
					schussPlayer.add(new Projectile(this, Player1));
					canShoot = false;
					canShootCounter = 0;
				}
			}
		}
		// this checks if the right amount of time has passed before canShoot
		// can = true again
		if (canShoot == false) {
			canShootCounter = canShootCounter + Clock.elapsedTime;
			// if the right amount of time has passed. make canShoot true
			if (canShootCounter >= 0.5)/*
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
					sound.playSound(sound.music[1]);
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
				if(playerHitPoints <= 10){
					sound.playSound(sound.music[1]);
				}else{
					sound.playSound(sound.music[0]);
				}
				playerHitPoints = playerHitPoints - 10;
				schussGegner.remove(i);
			}
		}
	}

	public void keyPressed() {
		KeyHandler.setMove(keyCode, true);//vielen Dank an Fabian
	}

	public void keyReleased() {
		KeyHandler.setMove(keyCode, false);//vielen Dank an Fabian
	}
}
