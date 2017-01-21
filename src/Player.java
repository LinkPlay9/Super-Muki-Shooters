import processing.core.PApplet;
import processing.core.PImage;

public class Player extends MainObject {

	float posX, posY;
	PImage player;

	public Player(PApplet d) {

		super(d);
		this.x = 800 / 2;
		this.y = 600 - 100;
		this.a = 50.0f;
		this.b = 50.0f;
		posY = this.y;
		posX = this.x;
	}

	public void drawPlayer(int charactersel) {
		switch (charactersel){
			case 1:			player = drawing.loadImage("data/Player/fetafighter.png"); break;	// 1 für Pana
			case 2:			player = drawing.loadImage("data/Player/maccaroni.png"); break;		// 2 für Mathaan
			case 3:			player = drawing.loadImage("data/Player/blinkerboi.png"); break;	// 3 für Toni
			case 4:			player = drawing.loadImage("data/Player/ufolo.png"); break;			// 4 für Zelle
			default: 		player = drawing.loadImage("data/Player/default.png"); break;

		}
		drawing.image(player, x, y);
	}

	public void movePlayer() {

		if (KeyHandler.keyA) {
			this.x += -350 * Clock.elapsedTime;
		}

		if (KeyHandler.keyD) {
			this.x += 350 * Clock.elapsedTime;
		}

		if (drawing.key == ESC) {
			System.exit(1);
		}

		// Damit der Spieler nicht aus dem Feld kann
		if (this.x <= 0) {
			this.x = 0;
		}

		if (this.x >= 749) {
			this.x = 749;

		}
	}
}