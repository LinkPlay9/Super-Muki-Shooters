import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemy extends MainObject {
	public float velocityX, velocityY;
	// Random rand = new Random();
	float minY = -300;
	float maxY = -10;
	float finalY = drawing.random(minY, maxY);
	float minX = 10;
	float maxX = 800 - 25;
	float finalX = drawing.random(minX, maxX);
	public boolean sichtbar = false;
	PImage enemy = drawing.loadImage("data/Player/enemy.png");
	float canShootCounter = 0;
	boolean canShoot = true;
	ArrayList<ProjectileEnemy> schussGegner = new ArrayList<ProjectileEnemy>();

	
	public Enemy(PApplet d) {
		super(d);
		this.x = finalX;
		this.y = finalY;
		this.a = 25;
		this.b = 25;
		velocityY = 80;
	}

	public void drawEnemy() {
//		drawing.noStroke();
//		drawing.fill(255, 0, 0);
//		drawing.rect(this.x, this.y, this.a, this.b);
		drawing.image(enemy, x, y);
	}

	public void update() {
		this.y += velocityY * Clock.elapsedTime;
	}

	public void enemyRandomSpawn() {
		velocityY += 20;
		this.y = drawing.random(minY, maxY);
		this.x = drawing.random(minX, maxX);
	}

}
