import processing.core.*;

public class KeyHandler extends PApplet{
	
	static boolean 
		keyW,
		keyA,
		keyS,
		keyD;
    
    int keyUsed;
            
    static boolean setMove(int key, boolean b) {
        
        switch (key) {
            
        case 87:
            
            return keyW = b;
            
        case 65:
            
            return keyA = b;
            
        case 83:
            
            return keyS = b;
            
        case 68:
            
            return keyD = b;
            
        default:
            
            return b;
    
        }
    
    }
    

}
