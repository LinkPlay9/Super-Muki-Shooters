
public class Player extends Objects {
//test
	
	public Player(float posX, float posY, float rX, float rY) {
		super(posX, posY, rX, rY);
		// TODO Auto-generated constructor stub
	}

	
	public void movePlayer(float x)
	{
		
		velX = x;
		this.x += velX;
		
		
	}
	
	
}
