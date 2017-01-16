import ddf.minim.*;
import processing.core.PApplet;

public class sound {
	
	static PApplet applet;
	static Minim minim;
	static AudioPlayer[] music = new AudioPlayer[2];
	
	static void setupSoundEngine(PApplet p){
		applet = p;
		minim = new Minim(applet);
		loadSamples();
	}
	
	static void controlMusic(AudioPlayer audio, String cmd){
		switch(cmd){
		case "play":	
						if(!audio.isLooping()){
							audio.loop();
						}
						break;
						
		case "stop":	if(audio.isLooping()){
							audio.pause();
							audio.rewind();
						}
						break;
		}
	
	}
	
	static void playSound(AudioPlayer sound){
		sound.rewind();
		sound.play();
	}
	
	static void loadSamples(){
		//https://www.freesound.org/people/newagesoup/sounds/337837/
		music[0] = minim.loadFile("data/sound/Hit.mp3");
		//https://www.freesoundeffects.com/free-track/explosion-5-466450/
		music[1] = minim.loadFile("data/sound/Explosion.mp3");

	}
	
}
