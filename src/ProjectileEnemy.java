import java.util.ArrayList;

import processing.core.PApplet;

public class ProjectileEnemy extends MainObject {
	ArrayList<Enemy> enemy = new ArrayList<Enemy>();

	public ProjectileEnemy(PApplet d, ArrayList<Enemy> enemy, int i) {
		super(d);
		this.x = enemy.get(i).x + 12.5f;
		this.y = enemy.get(i).y + 17.5f;

	}

	public void drawProjectileEnemy() {
		drawing.noStroke();
		drawing.fill(255, 0, 0);
		drawing.ellipse(this.x, this.y, 10, 10);
	}

	public void shootEnemy() {
		this.y += 200 * Clock.elapsedTime;
	}

}
