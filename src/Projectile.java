import processing.core.PApplet;

public class Projectile extends MainObject {

	public Projectile(PApplet d, Player player) {
		super(d);
		this.x = player.x + 25;
		this.y = player.y;

	}

	public void shoot() {
		velY = 5;
		drawing.ellipse(this.x, this.y, 10, 10);
		this.y -= velY;

	}
}
