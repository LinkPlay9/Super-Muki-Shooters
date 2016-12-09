import processing.core.*;

public class KeyHandler extends PApplet{
	
	boolean goUp, goDown, goLeft, goRight, triggered;
    
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
            
        case 'T':
            
            return triggered = b;
            
        default:
            
            return b;
    
        }
    
    }
    

}
