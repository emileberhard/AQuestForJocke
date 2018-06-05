package com.QfJ;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.QfJ.UI.StartMenu;
import com.QfJ.characters.Xiange;
import com.QfJ.graphics.Screen;

public class ActionHandler implements KeyListener, MouseListener, MouseMotionListener{
	Xiange xiangeObjekt;
	Game game;
	StartMenu menu;
	Screen screen;
	
	boolean mouseEntered;
	
	public ActionHandler(Xiange xiangeObjekt, Screen screen, StartMenu menu, Game game){
		this.xiangeObjekt = xiangeObjekt;
		this.menu = menu;
		this.game = game;
		
		this.screen = screen;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP) {
			xiangeObjekt.up = true;
		}
		if(key == KeyEvent.VK_DOWN) {
			xiangeObjekt.down = true;
		}
		if(key == KeyEvent.VK_RIGHT) {
			xiangeObjekt.right = true;
		}
		if(key == KeyEvent.VK_LEFT) {
			xiangeObjekt.left = true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP) {
			xiangeObjekt.up = false;
		}
		if(key == KeyEvent.VK_DOWN) {
			xiangeObjekt.down = false;
		}
		if(key == KeyEvent.VK_RIGHT) {
			xiangeObjekt.right = false;
		}
		if(key == KeyEvent.VK_LEFT) {
			xiangeObjekt.left = false;
		}
	}	
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		int xPos = e.getX()/Game.SCALE;
		int yPos = e.getY()/Game.SCALE;
		
		if(game.menuActive) {
			if(menu.playButton.isPressed(xPos, yPos)){
				game.menuActive = false;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		int xPos = e.getX()/Game.SCALE;
		int yPos = e.getY()/Game.SCALE;
		
		if(game.menuActive) {
			if(menu.playButton.isHovered(xPos, yPos)){
				menu.playButton.setHovered(true);
			}else {
				menu.playButton.setHovered(false);
			}
		}
	}
}
