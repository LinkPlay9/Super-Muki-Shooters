import processing.core.*;
//test

 public class Objects extends PApplet{
	

	public float x,y,a,b;
	public float velX,velY;
	public float movY,rsY,rsX;
	
	
		public Objects( float posX, float posY, float rX, float rY) {
		this.x = posX;
		this.y = posY;
		this.a = rX;
		this.b = rY;
	
		
		
	}

		public void  Munition(float a, float b, float c , float d)
		
		{
			
			this.x = a;
			this.y = b;
			this.a = c;
			this.b = d;
			
		}
		
		
		public void MuniMove(float r)
		{
			
		rsY = r;
		
		this.y -= rsY;
		}
		
		public void MuniMove(float r, float u)
		{
			
		rsY = r;
		
		this.y -= rsY;
		
		rsX = u;
		this.x += rsX;
		
			
			
		}
		
	
	public float getVelX() {
		return velX;
	}




	public void setVelX(int velX) {
		this.velX = velX;
	}




	public float getVelY() {
		return velY;
	}




	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public float getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
	

}
