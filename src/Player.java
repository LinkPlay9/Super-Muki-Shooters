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

		drawing.fill(255, 255, 255);
		drawing.rect(x, y, a, b);

	}

	public void movePlayer(float speed) {
		velX = speed;
		this.x = this.x + velX;

		// Damit der Spieler nicht aus dem Feld kann
		if (this.x <= 0) {
			this.x = 0;
		}

		if (this.x >= 749) {
			this.x = 749;

		}

	}

}
