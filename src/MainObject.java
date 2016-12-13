import processing.core.PApplet;

public class MainObject extends PApplet {

	public PApplet drawing;
	public float x;
	public float y;
	public float a, b;
	public float velX, velY;

	public MainObject(PApplet d) {
		drawing = d;

	}

	public PApplet getDrawing() {
		return drawing;
	}

	public void setDrawing(PApplet drawing) {
		this.drawing = drawing;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

}
