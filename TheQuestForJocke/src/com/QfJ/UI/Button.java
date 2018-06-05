package com.QfJ.UI;

import java.awt.image.BufferedImage;

import com.QfJ.Rectangle;

public class Button {
	Rectangle buttonRect;
	BufferedImage buttonImage, buttonHoverImage, currentImage;
	
	public Button(int x, int y, BufferedImage buttonImage, BufferedImage buttonHoverImage) {
		buttonRect = new Rectangle(x, y, buttonImage.getWidth(), buttonImage.getHeight());
		this.buttonImage = buttonImage;
		this.buttonHoverImage = buttonHoverImage;
		
		currentImage = buttonImage;
	}
	
	public BufferedImage getButtonImage() {
		return buttonImage;
	}
	public BufferedImage getButtonHoverImage() {
		return buttonHoverImage;
	}
	
	public boolean isPressed(int mouseX, int mouseY) {
		boolean isPressed = false;
		
		if(mouseX > buttonRect.x && mouseX < (buttonRect.x + buttonRect.w)){
			if(mouseY > buttonRect.y && mouseY < (buttonRect.y + buttonRect.h)) {
				isPressed = true;
			}
		}
		return isPressed;
	}
	
	public boolean isHovered(int mouseX, int mouseY) {
		boolean isHovered = false;
		
		if(mouseX > buttonRect.x && mouseX < (buttonRect.x + buttonRect.w)){
			if(mouseY > buttonRect.y && mouseY < (buttonRect.y + buttonRect.h)) {
				isHovered = true;
			}
		}
		return isHovered;
	}
	
	public void setHovered(boolean isHovered) {
		if(isHovered) {
			currentImage = buttonHoverImage;
		}else {
			currentImage = buttonImage;
		}
		
		buttonRect.setWidth(currentImage.getWidth());
		buttonRect.setHeight(currentImage.getHeight());
	}
	
	public void setX(int x) {
		buttonRect.x = x;
	}
	public void setY(int y) {
		buttonRect.y = y;
	}
}
