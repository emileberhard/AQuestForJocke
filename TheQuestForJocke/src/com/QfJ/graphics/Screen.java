package com.QfJ.graphics;

public class Screen {
	
	private int width, height;
	public int[] pixels;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void render() {
		for(int y = 0; y < height; y++) {
			if(y >= height || y < 0) break;
			for(int x = 0; x < width; x++) {
				if(x >= width || x < 0) break;
				pixels[x+y*width] = 0xff00ff;
			}
		}
	}
}

