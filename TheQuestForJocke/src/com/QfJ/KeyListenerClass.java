package com.QfJ;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.QfJ.characters.Xiange;

public class KeyListenerClass implements KeyListener{
	
	Xiange xiangeObjekt;
	
	public KeyListenerClass(Xiange xiangeObjekt){
		this.xiangeObjekt = xiangeObjekt;
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
}
