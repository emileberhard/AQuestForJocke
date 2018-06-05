package com.QfJ.UI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class SpeechBox {
	BufferedImage textBox, lengthImage;
	private int width, height;
	Font font = new Font("Arial", Font.TRUETYPE_FONT, 12);
	
	public BufferedImage getTextImage(String text) {
		Canvas c = new Canvas();
		FontMetrics fm = c.getFontMetrics(font);
		int textLength = fm.stringWidth(text);
		Rectangle2D textBounds = fm.getStringBounds(text, c.getGraphics());
		
		width = (int) (textBounds.getWidth()) + ((int) (textBounds.getWidth())/10);
		height = (int) (textBounds.getHeight() + 9);
		textBox = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D graphics = textBox.createGraphics();
		
		graphics.setColor(Color.WHITE);
		graphics.fill(new RoundRectangle2D.Double(0, 0, width, height, 10, 10));
		graphics.setColor(Color.BLACK);
		graphics.draw(new RoundRectangle2D.Double(0, 0, width-1, height-1, 10, 10));
		graphics.setFont(font);
		graphics.drawString(text, width - textLength - ((width - textLength)/2), height/2 + ((int) textBounds.getHeight()/2) - height/15);
		
		graphics.dispose();
		return textBox;
	}
}
