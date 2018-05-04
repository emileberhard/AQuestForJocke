package com.QfJ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TextBox {
	BufferedImage textBox, lengthImage;
	int width, height;
	
	public BufferedImage renderText(String text) {
		lengthImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		Graphics lengthGraphics = lengthImage.getGraphics();
		
		lengthGraphics.setFont(new Font("Arial Black", Font.BOLD, 20));
		int textLength = lengthGraphics.getFontMetrics().stringWidth(text);
		width = textLength + 50;
		height = 40;
		textBox = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = textBox.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
		graphics.drawString(text, width - textLength - ((width - textLength)/2), height/2 + 7);
		return textBox;
	}
}
