package com.QfJ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class TextBox {
	BufferedImage textBox, lengthImage;
	private int width, height;
	private int fontSize = 15;
	private String font = "American Typewriter";
	
	public BufferedImage getTextImage(String text) {
		lengthImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		Graphics lengthGraphics = lengthImage.getGraphics();
		
		lengthGraphics.setFont(new Font(font, Font.PLAIN, fontSize));
		int textLength = lengthGraphics.getFontMetrics().stringWidth(text);
		width = textLength + 30;
		height = 35;
		textBox = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = textBox.createGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fill(new RoundRectangle2D.Double(0, 0, width, height, 10, 10));
		graphics.setColor(Color.BLACK);
		graphics.draw(new RoundRectangle2D.Double(0, 0, width-1, height-1, 10, 10));
		graphics.setFont(new Font(font, Font.PLAIN, fontSize));
		graphics.drawString(text, width - textLength - ((width - textLength)/2), height/2 + 7);
		return textBox;
	}
}
