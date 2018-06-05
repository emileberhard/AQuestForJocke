package com.QfJ.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.QfJ.ActionHandler;
import com.QfJ.Game;
import com.QfJ.graphics.Screen;

public class StartMenu {
	private int width = Game.WIDTH;
	private int height = Game.HEIGHT;
	
	Screen screen;
	BufferedImage menuImage;
	ActionHandler actionHandler;
	Font font = new Font("Arial", Font.TRUETYPE_FONT, 60);
	
	public Button playButton = new Button(0, 0, Game.loadImage("playButton.png"), Game.loadImage("playButtonHover.png"));
	BufferedImage titleImage = Game.loadImage("titleImage.png");
	
	public StartMenu(Screen screen, Game game, ActionHandler actionHandler) {
		this.screen = screen;
		menuImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		playButton.setX(width/2 - playButton.buttonRect.w/2);
		playButton.setY(height/2 - playButton.buttonRect.h/2 + 10);
	}
	
	public void renderMenu(){
		updateMenu();
		screen.renderStaticImage(menuImage, 0, 0, 1, 1);
	}
	
	private void updateMenu() {
		Graphics2D g = menuImage.createGraphics();
		
		g.drawImage(titleImage, width/2 - titleImage.getWidth()/2, 40, null);
		g.drawImage(playButton.currentImage, playButton.buttonRect.x, playButton.buttonRect.y, null);
		
		g.dispose();
	}
}
