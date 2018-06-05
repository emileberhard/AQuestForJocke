package com.QfJ;

import java.awt.Color;

import com.QfJ.graphics.Screen;

public class Rectangle {
	public int x, y, w, h;
	int[] pixels;
	
	public Rectangle(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		pixels = new int[x*y];
	}
	
	public Rectangle() {
		this(0, 0, 0, 0);
	}
	
	public void render(Color color, Screen screen) {
		for(int y = 0; y < this.h; y++) {
			for(int x = 0; x < this.w; x++) {
				pixels[x + (y*this.w)] = Color.CYAN.getRGB();
				screen.setPixel(pixels[x + (y*this.w)], x + this.x, y + this.y);
			}
		}
	}
	
	public void setWidth(int w) {
		this.w = w;
	}
	public void setHeight(int h) {
		this.h= h;
	}
}
