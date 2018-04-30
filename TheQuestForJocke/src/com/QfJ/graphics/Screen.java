package com.QfJ.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

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
			if(y < 0 || y >= height) break;
			for(int x = 0; x < width; x++) {
				if(x < 0 || x >= width) break;
				if(pixels[x + y * width] == 0) {
					pixels[x + y * width] = Color.yellow.getRGB();
				}
			}
		}
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderImage(BufferedImage image, int xPos, int yPos) {
		int[] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
		for(int y = 0; y < image.getHeight(); y++) {
			if(y < 0 || y >= image.getHeight()) break;
			for(int x = 0; x < image.getWidth(); x++) {
				if(x < 0 || x >= image.getWidth()) break;
				pixels[(x + xPos) + (yPos + y) * width] = imagePixels[x + y * image.getWidth()];
			}
		}
	}
}