import processing.core.PApplet;

public class clock extends PApplet {

	static float cur, prev, elapsedTime;

	void update() {
		cur = millis();
		elapsedTime = (float) 1e-3 * (cur - prev);
		prev = cur;
	}

}