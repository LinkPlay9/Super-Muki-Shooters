import processing.core.PApplet;

public class Player extends MainObject {

	float posX, posY;

	public Player(PApplet d) {

		super(d);
		this.x = 800 / 2;
		this.y = 600 - 50;
		this.a = 50.0f;
		this.b = 50.0f;
		posY = this.y;
		posX = this.x;

	}

	public void drawPlayer() {

		drawing.fill(0, 255, 0);
		drawing.rect(x, y, a, b);

	}

	public void movePlayer() {
		

		if (KeyHandler.keyA) {	this.x += -350 * Clock.elapsedTime; }

		if (KeyHandler.keyD) {	this.x += 350 * Clock.elapsedTime; }

		if (drawing.key == ESC) { System.exit(1); }

		// Damit der Spieler nicht aus dem Feld kann
		if (this.x <= 0) {
			this.x = 0;
		}

		if (this.x >= 749) {
			this.x = 749;

		}
	}
}