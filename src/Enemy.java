import processing.core.PApplet;

public class Enemy extends MainObject {

	public Enemy(PApplet d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

	public void drawEnemy(float posX, float posY, float xa, float yb) {
		this.x = posX;
		this.y = posY;
		this.a = xa;
		this.b = yb;
		drawing.fill(255, 0, 0);
		drawing.rect(this.x, this.y, this.a, this.b);

	}

	public void moveEnemy(float speed) {
		velY = speed;
		this.y -= velY;

	}

}
