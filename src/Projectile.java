import processing.core.PApplet;

public class Projectile extends MainObject {
	public float xEnemy, yEnemy;

	public Projectile(PApplet d, Player player) {
		super(d);
		this.x = player.x + 25;
		this.y = player.y;

	}

	public void draw() {

		drawing.fill(244, 143, 177);
		drawing.ellipse(this.x, this.y, 10, 10);

	}

	public void shoot() {
		this.y -= 200 * Clock.elapsedTime;

	}

}