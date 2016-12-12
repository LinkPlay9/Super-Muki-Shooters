import java.util.Random;

import processing.core.PApplet;

public class Enemy extends MainObject {

	public float velocityX, velocityY;
	Random rand = new Random();
	float minY = -92;
	float maxY = -10;
	float finalY = minY + (maxY - minY) * rand.nextFloat();
	float minX = 100;
	float maxX = 800 - 25;
	float finalX = minX + (maxX - minX) * rand.nextFloat();

	public Enemy(PApplet d) {
		super(d);
		this.x = finalX;
		this.y = finalY;
		this.a = 25;
		this.b = 25;
		velocityY = 100;
	}

	public void drawAndMoveEnemy() {

		drawing.fill(255, 0, 0);
		drawing.rect(x, y, a, b);
		this.y += velocityY * clock.elapsedTime;
		if (this.y > 600 - 26) {
			this.y = 600 - 26;

		}

	}

}
