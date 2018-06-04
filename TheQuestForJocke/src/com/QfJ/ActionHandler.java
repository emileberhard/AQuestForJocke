package com.QfJ;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.QfJ.characters.Xiange;
import com.QfJ.graphics.Screen;

public class ActionHandler implements KeyListener, MouseListener{
	
	Xiange xiangeObjekt;
	Screen screen;
	
	public ActionHandler(Xiange xiangeObjekt, Screen screen){
		this.xiangeObjekt = xiangeObjekt;
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
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
}
