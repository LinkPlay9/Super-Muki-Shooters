import processing.core.PApplet;

public class Projectile extends MainObject {
	boolean sh = false;

	public Projectile(PApplet d, Player player) {
		super(d);
		this.x = player.x + 25;
		this.y = player.y;

	}

	public void drawPro() {
		drawing.fill(255, 255, 0);
		drawing.ellipse(this.x, this.y, 10, 10);
	}

	public void shoot() {

		if (drawing.key == ' ') {
			sh = true;

		}
		if (sh) {
			velY = 2;
			this.y -= velY;

		}
		if (drawing.keyPressed) {

			if (drawing.key == 'a' || drawing.key == 'A') {
				if (sh == false) {
					this.x += -2.5f;
				}
			}

			if (drawing.key == 'd' || drawing.key == 'D') {
				if (sh == false) {
					this.x += 2.5f;
				}
			}

		}
	}

}
