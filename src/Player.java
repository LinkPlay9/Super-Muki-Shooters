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
		this.x = this.x + velX;

		if (drawing.keyPressed) {

			if (drawing.key == 'a' || drawing.key == 'A') {
				this.x += -500 * clock.elapsedTime;

			}

			if (drawing.key == 'd' || drawing.key == 'D') {
				this.x += 500 * clock.elapsedTime;

			}

			if (drawing.key == ESC)

			{
				System.exit(1);

			}
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