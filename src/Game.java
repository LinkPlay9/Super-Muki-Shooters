import processing.core.*;
//test

public class Game extends PApplet {
	PImage bg;
	boolean moving = false;
	int i = 0;
	
	//Deklaration Spieler & Gegner
	Player Player1 = new Player(549.0f,549.0f,50.0f,50.0f);
	Enemy Enemy1 = new Enemy(10.0f,0.0f,25.0f,25.0f);
	Enemy Enemy2 = new Enemy(350.0f,0.0f,25.0f,25.0f);
	Enemy Enemy3 = new Enemy(550.0f,0.0f,25.0f,25.0f);
	Objects Muni = new Objects(Player1.x+19,Player1.y-6.0f,15.0f,15.0f);
	
	
	public static void main(String[] args)
	{
		PApplet.main("Game");
		
		
		
	}
	
	
	
	
	public void setup() {
        frameRate(1000);
        
        
        }
    
    public void settings() {
    	
    	
    	//CustomBackground mit der Auflösung 800x600
    	size(800,600, P2D);
    	//fullScreen(P2D);
    	
    	bg = loadImage("data/Backgrounds/Level1.jpg");
    	bg.resize(width, height);
    	loop();
    }
    
    
    
    
	
    	public void draw() {
    		background(bg);
    		textSize(20);
    		text("FPS: " + (int) frameRate, 0, 20);
    		
    		//Munition
    		fill(255,51,135);
    		ellipse(Muni.x,Muni.y,Muni.a,Muni.b);
    		
    		//Spieler
    		fill(255,255,255);
    		rect(Player1.x,Player1.y,Player1.a,Player1.b);
    		
    		//Gegner
    		fill(255,0,0);
    		rect(Enemy1.x,Enemy1.y,Enemy1.a,Enemy1.b);
    		
    		fill(0,0,255);
    		rect(Enemy2.x,Enemy2.y,Enemy2.a,Enemy2.b);
    		
    		fill(255,255,0);
    		rect(Enemy3.x,Enemy3.y,Enemy3.a,Enemy3.b);
    		
    		//Gegner nach unten bewegen
    		Enemy1.moveEnemy(0.5f);
    		Enemy2.moveEnemy(1.2f);
    		Enemy3.moveEnemy(0.8f);
    		
    		//Damit die Gegner nicht aus dem Feld können
    		if(Enemy1.y >= 574.0f)
    		{
    			Enemy1.y = 574.0f;
    				
    		}
    		
    		if(Enemy2.y >= 574.0f)
    		{
    			Enemy2.y = 574.0f;
    			
    		}
    		
    		if(Enemy3.y >= 574.0f)
    		{
    			Enemy3.y = 574.0f;
    				
    		}
    		
    		 // Muni Schießen (Noch nicht Fertig!)
    		if(key == ' ' )
    		{
    			if(key == 'a' || key == 'd')
    			{
    				Muni.MuniMove(2);
    			}
    			Muni.MuniMove(2);	
    			
 
    		}	
    		
    		
    		
    		
	    			
	    	//KeyEvents für Spieler
    		if(keyPressed)
    		{
    			if(key == 'a' || key == 'A')
    			{
    				
    				Player1.movePlayer(-2.5f);
    				Muni.MuniMove(0f,-2.5f);
    			}
    			
    			 if (key == 'd' || key == 'D' )
    			{
    				 
    				Player1.movePlayer(2.5f);
    				Muni.MuniMove(0f,2.5f);
    				
    				
    			}
    			
    			 if(key == ESC)
    			 {
    				 System.exit(1);
    				 
    			 }
    			 
    			
    			 //Damit der Spieler nicht aus dem Feld kann
    			if(Player1.x <= 0)
    			{
    				Player1.x = 0;
    				
    			}
    			
    			if(Player1.x >= 749)
    			{
    				Player1.x = 749;
    				
    			}
    			
    			
    				
    					
    	}
    		
    		
    			
    		
    			
    			
    			
    	}
    		
    }
    		
    		
    	
    	
    		
    
    	
 
    		
    		
   
    	
    	
    			
    	
        
    
    
	 
 
 	

