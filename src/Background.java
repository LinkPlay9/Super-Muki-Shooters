import processing.core.PApplet;

public class Background extends PApplet {
	public PApplet drawing;
	float x = random(800);
	float y = random(-500, -50);
	float z = random(0, 20);
	float len = PApplet.map(z, 0, 20, 10, 20);
	float yspeed = PApplet.map(z, 0, 20, 1, 20);

	public Background(PApplet d) {
		drawing = d;

	}

	public void fall() {
		y += yspeed;
		float grav = PApplet.map(z, 0, 20, 0, (float) 0.2);
		yspeed = (float) (yspeed + grav);
		if (y > 600) {
			y = random(-200, -100);
			yspeed = PApplet.map(z, 0, 20, 4, 10);
		}
	}

	public void show() {
		float thick = PApplet.map(z, 0, 20, 1, 3);
		drawing.strokeWeight(thick);
		drawing.stroke(138, 43, 226);
		drawing.line(x, y, x, y + len);
	}

	public PApplet getDrawing() {
		return drawing;
	}

	public void setDrawing(PApplet drawing) {
		this.drawing = drawing;
	}

}