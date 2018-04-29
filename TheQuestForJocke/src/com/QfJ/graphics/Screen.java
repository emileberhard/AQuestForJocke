package com.QfJ.graphics;

import java.awt.Color;

public class Screen{
	
	private int width, height;
	public int[] pixels;
	
	public Screen(int width, int height) {
			this.width = width;
			this.height = height;
			pixels = new int[width * height];
	}
	
	public void render() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				pixels[x + y * width] = Color.yellow.getRGB();
			}
		}
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
}