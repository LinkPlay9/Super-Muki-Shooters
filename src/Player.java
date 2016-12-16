import processing.core.PApplet;
import processing.core.PImage;

public class Player extends MainObject {

	float posX, posY;
	PImage player;

	public Player(PApplet d, PImage player) {

		super(d);
		this.x = 800 / 2;
		this.y = 600 - 100;
		this.a = 50.0f;
		this.b = 50.0f;
		posY = this.y;
		posX = this.x;
		this.player = player;
	}

	public void drawPlayer() {
		player = drawing.loadImage("data/player.png");
		player.resize(50, 0);
		drawing.noStroke();
		// drawing.fill(0, 255, 12);
		// drawing.rect(x, y, a, b);
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