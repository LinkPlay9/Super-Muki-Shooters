import processing.core.PApplet;

//Thanks to Fabian

public class Clock extends PApplet {

	static float cur, prev, elapsedTime;

	void update() {
		cur = millis();
		elapsedTime = (float) 1e-3 * (cur - prev);
		prev = cur;
	}

}
