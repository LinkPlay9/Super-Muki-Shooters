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
		//music[0] = minim.loadFile("data/sound/music/Crescendolls.mp3", 4096);
		//http://soundbible.com/1771-Laser-Cannon.html
		music[1] = minim.loadFile("data/sound/Laser Blast.mp3");

	}
	
}
