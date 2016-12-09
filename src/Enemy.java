public class Enemy extends Objects{

	
	//test
	public Enemy(float posX, float posY, float rX, float rY) {
		super(posX, posY, rX, rY);
		// TODO Auto-generated constructor stub
	}

	
	public void moveEnemy(float y)
	{
		
		this.velY = y;
		
		this.y += this.velY;
		
	}
	
	

	
	
}
