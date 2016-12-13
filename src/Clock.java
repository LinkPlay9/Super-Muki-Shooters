import processing.core.PApplet;

public class Clock extends PApplet {

	static float cur, prev, elapsedTime;

	void update() {
		cur = millis();
		elapsedTime = (float) 1e-3 * (cur - prev);
		prev = cur;
	}

}
