package com.QfJ;

public class Physics {
	boolean isColliding;
	public boolean isColliding(Rectangle r1, Rectangle r2) {
		isColliding = false;
		
		
		
		
		/*if((r1.x + r1.w) >= r2.x && r1.y <= (r2.y + r2.h)) //kollision r1 topp mot r2 botten
			if((r1.y + r1.h) >= r2.y)
				isColliding = true;

		if((r2.x + r2.w) >= r1.x &&  r2.y <= (r1.y + r1.h)) // kollision r1 vänster mot r2 höger
			if((r2.y + r2.h) <= r1.y)
				isColliding = true;
		
		if((r1.y + r1.h) >= r2.y && r1.x <= (r2.x + r2.w)) //kollision r1 botten mot r2 topp
		     if((r1.x + r1.w) <= r2.x)
		    	   isColliding = true;
		
		if((r2.y + r2.h) >= r1.y && r2.x <= (r1.x + r1.w))//kollision r1 topp mot r2 botten
			if((r2.x + r2.w) >= r1.x)
				isColliding = true;*/
		return isColliding;
	}
}
