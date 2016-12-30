import processing.core.PApplet;

public class Background extends PApplet {
	public PApplet drawing;
	float x = random(800);
	float y = random(0, 600);
	// float z = random(0, 20);
	float size = random(1, 3);
	float size2 = random(1, 3);
	float yspeed = random(15, 25);

	public Background(PApplet d) {
		drawing = d;
	}

	public void fall() {
		y = y + yspeed * Clock.elapsedTime;
		if (y > 600) {
			y = random(-600, 0);
		}
	}

	public void show() {
		drawing.fill(122, 122, 122);
		drawing.ellipse(x, y, size, size2);
	}

	public PApplet getDrawing() {
		return drawing;
	}

	public void setDrawing(PApplet drawing) {
		this.drawing = drawing;
	}

}