import processing.core.*;

public class KeyHandler extends PApplet{
	
	boolean keyUp,
		keyDown,
		keyLeft,
		keyRight,
		keyW,
		keyA,
		keyS,
		keyD;
    
    int keyUsed;
            
    boolean setMove(int key, boolean b) {
        
        switch (key) {
        
        case UP:
            
            return goUp = b;
                
        case DOWN:
            
            return goDown = b;
            
        case LEFT:
            
            return goLeft = b;
            
        case RIGHT:
            
            return goRight = b;
            
        case 'w':
            
            return keyW = b;
            
        case 'a':
            
            return keyA = b;
            
        case 's':
            
            return keyS = b;
            
        case 'd':
            
            return keyD = b;
            
        default:
            
            return b;
    
        }
    
    }
    

}
