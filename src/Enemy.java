import processing.core.PApplet;

public class Enemy extends MainObject {

	public float velocityX, velocityY, posY, posX;

	public Enemy(PApplet d) {
		super(d);
		this.x = 800 / 3;
		this.y = 0;
		this.a = 25.0f;
		this.b = 25.0f;
		posY = this.y;
		posX = this.x;
		velocityX = 0.8f;
		velocityY = 1.8f;
		// TODO Auto-generated constructor stub
	}

	public void drawEnemy() {

		drawing.fill(255, 0, 0);
		drawing.rect(x, y, a, b);

	}

	public void moveEnemy() {
		this.y += velocityY;
		// Wenn Gegner ganz unten ankommt bleibt der auch da
		if (this.y > 600 - 26) {
			this.y = 600 - 26;

		}

	}

}
