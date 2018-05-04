package com.QfJ;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Player {
	int hp = 100;
	String name;
	BufferedImage playerImage;
	BufferedImage hpImage;
	
	public Player(String name, BufferedImage playerImage) {
		this.name = name;
		this.playerImage = playerImage;
	}
	
	public BufferedImage getPlayerImage() {
		return playerImage;
	}
	
	public BufferedImage getHpImage() {
		hpImage = new BufferedImage(200, 20, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = hpImage.createGraphics();
		graphics.setPaint(Color.lightGray);
		graphics.fill(new Rectangle2D.Double(0, 0, hpImage.getWidth(), hpImage.getHeight()));
		graphics.setPaint(Color.RED);
		graphics.fill(new Rectangle2D.Double(0, 0, hp * 2, hpImage.getHeight()));
		graphics.setPaint(Color.WHITE);
		graphics.setFont(new Font("Arial", Font.PLAIN, 10));
		graphics.drawString("HP: " + Integer.toString(hp), 13, hpImage.getHeight()/2+4);
		return hpImage;
	}
}
