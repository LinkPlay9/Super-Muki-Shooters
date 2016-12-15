import processing.core.PApplet;

public class Enemy extends MainObject {
	public float velocityX, velocityY;
	// Random rand = new Random();
	float minY = -90;
	float maxY = -10;
	float finalY = drawing.random(minY, maxY);
	float minX = 10;
	float maxX = 800 - 25;
	float finalX = drawing.random(minX, maxX);
	boolean sichtbar = false;

	public Enemy(PApplet d) {
		super(d);
		this.x = finalX;
		this.y = finalY;
		this.a = 25;
		this.b = 25;
		velocityY = 80;
	}

	public void drawEnemy() {

		drawing.fill(255, 0, 0);
		drawing.rect(this.x, this.y, this.a, this.b);

	}

	public void update() {
		this.y += velocityY * Clock.elapsedTime;
	}

	public void enemyRandomSpawn() {
		velocityY += 50;
		this.y = drawing.random(minY, maxY);
		this.x = drawing.random(minX, maxX);
	}

}
