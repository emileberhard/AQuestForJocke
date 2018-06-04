package com.QfJ.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.QfJ.Rectangle;

public class Screen{
	
	private int width, height;
	private Rectangle camera;
	public int[] pixels;
	public int backgroundColor = Color.green.getRGB();

	
	public Screen(int width, int height) {
			this.width = width;
			this.height = height;
			pixels = new int[width * height];
						
			camera = new Rectangle(0, 0, width, height);
			camera.x = 60;
			camera.y = 20;
	}
	
	public void renderBackground() {
		for(int y = 0; y < height; y++) {
			if(y < 0 || y >= height) break;
			for(int x = 0; x < width; x++) {
				if(x < 0 || x >= width) break;
				setPixel(backgroundColor, x, y);
			}
		}
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderStaticImage(BufferedImage image, int xPos, int yPos, int xZoom, int yZoom) {
		int[] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
		for(int y = 0; y < image.getHeight(); y++) {
			for(int x = 0; x < image.getWidth(); x++) { 
				for(int xZoomPosition = 0; xZoomPosition <= xZoom; xZoomPosition++) {
					if(((x * xZoom) + xPos + xZoomPosition) < 0 || ((x * xZoom) + xPos + xZoomPosition) >= width)  break;
					for(int yZoomPosition = 0; yZoomPosition < yZoom; yZoomPosition++) {
						if(((y * yZoom) + yPos + yZoomPosition) < 0 || ((y * yZoom) + yPos + yZoomPosition) >= height)  break;
						setStaticPixel(imagePixels[x + y * image.getWidth()], (x * xZoom) + xPos + xZoomPosition, (y * yZoom) + yPos + yZoomPosition);
					}
				}
			}
		}
	}
	
	public void renderImage(BufferedImage image, int xPos, int yPos, int xZoom, int yZoom) {
		int[] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
		for(int y = 0; y < image.getHeight(); y++) {
			for(int x = 0; x < image.getWidth(); x++) { 
				for(int xZoomPosition = 0; xZoomPosition <= xZoom; xZoomPosition++) {
					if(((x * xZoom) + xPos + xZoomPosition) < 0 || ((x * xZoom) + xPos + xZoomPosition) >= width)  break;
					for(int yZoomPosition = 0; yZoomPosition < yZoom; yZoomPosition++) {
						if(((y * yZoom) + yPos + yZoomPosition) < 0 || ((y * yZoom) + yPos + yZoomPosition) >= height)  break;
						setPixel(imagePixels[x + y * image.getWidth()], (x * xZoom) + xPos + xZoomPosition, (y * yZoom) + yPos + yZoomPosition);
					}
				}
			}
		}
	}
	
	public void setPixel(int pixel, int x, int y) {
		if(x >= camera.x && y >= camera.y && x <= camera.x + camera.w && y <= camera.y + camera.h) {
			int pixelIndex = (x - camera.x) + ((y - camera.y) * width);
			if(pixels.length > pixelIndex) {
				if(pixels[pixelIndex] == 0)
					pixels[pixelIndex] = pixel;
			}
		}
	}
	private void setStaticPixel(int pixel, int x, int y) {
		int pixelIndex = x + (y * width);
		if(pixels.length > pixelIndex) {
			if(pixels[pixelIndex] == 0)
				pixels[pixelIndex] = pixel;
		}
	}
}