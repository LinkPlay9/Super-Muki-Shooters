import processing.core.*;

public class KeyHandler extends PApplet{
	//Thanks to Fabian
	static boolean 
		keyW,
		keyA,
		keyS,
		keyD,
		keySpace;
    
    int keyUsed;
            
    static boolean setMove(int key, boolean b) {
        
        switch (key) {
        
        //Ascii Code des Buttons angeben
        
        case 32:
        	
        	return keySpace = b;
       
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
