package com.QfJ.graphics;

import java.util.Random;

public class Screen {
	
	private int width, height;
	public int[] pixels;
	public int[] tiles = new int[81];
	
	private Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for(int i = 0; i < 81; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
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
				int tileIndex = (x / 32) + (y / 32) * 14;
				pixels[x+y*width] = tiles[tileIndex];
			}
		}
	}
}

