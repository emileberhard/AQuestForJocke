package com.QfJ;

public class Physics {
	boolean isColliding;
	public boolean isColliding(Rectangle r1, Rectangle r2) {
		isColliding = false;
		
		if((r1.x + r1.w) >= r2.x && r1.x <= (r2.x + r2.w))
			if((r1.y + r1.h) >= r2.y && r1.y <= (r2.y + r2.h))
				isColliding = true;
		
		return isColliding;
	}
}
