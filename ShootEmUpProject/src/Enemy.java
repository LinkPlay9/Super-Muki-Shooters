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
		velocityY = 0.8f;
		// TODO Auto-generated constructor stub
	}

	public void drawEnemy() {

		drawing.fill(255, 0, 0);
		drawing.rect(x, y, a, b);

	}

	public void moveEnemy() {
		this.y += velocityY;

		if (this.y >= 600 - 25) {
			this.y = 600 - 25;
			System.out.print("BITCH HAST VERLOREN MUHAHAH");

		}

	}

	public void stopEnemy() {

		this.x = -2;
		this.y = -2;

	}

}