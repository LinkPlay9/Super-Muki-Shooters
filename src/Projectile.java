import processing.core.PApplet;

public class Projectile extends MainObject {
	// boolean sh = false;

	public Projectile(PApplet d, Player player) {
		super(d);
		this.x = player.x + 25;
		this.y = player.y;
	}

	public void draw() {
		drawing.noStroke();
		drawing.fill(244, 143, 177);
		drawing.ellipse(this.x, this.y, 10, 10);

	}

	public void shoot() {
		this.y -= 2;

	}

	public void moveLeft() {
		if (drawing.keyPressed) {
			if (drawing.key == 'a' || drawing.key == 'A') {
				this.x += -2.5f;
			}
		}
	}

	public void moveRight() {
		if (drawing.keyPressed) {
			if (drawing.key == 'd' || drawing.key == 'D') {
				this.x += 2.5f;
			}
		}
	}
}
