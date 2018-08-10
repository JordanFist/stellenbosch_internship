package carcassonne.ui;

import carcassonne.core.Direction;
import carcassonne.core.Position;
import carcassonne.core.Move;
import carcassonne.core.Place;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Image;
import java.io.File;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color; 
import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class meepleDrawable extends JPanel { 
	public tileDrawable panel;
	public Image image;
	public int x;
	public int y;
	
	public meepleDrawable(tileDrawable panel, int x, int y) {
		this.panel = panel;
		this.x = x;
		this.y = y;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//g.setColor(new Color(245, 245, 220));
		//g.fillRect(oldPosX, oldPosY, 100, 100);
	
		//int X = window.getWidth() / 2 - this.getWidth() / 2  + this.getWidth() * x;
		//int Y = (window.getHeight() - window.getInsets().top) / 2 - this.getHeight() / 2  + this.getHeight() * y;

		
		g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
		this.setBounds(x, y, this.getWidth(), this.getHeight());
   	}	
	/*
	public int getPosX() {
		return x;
        }

	public void setPosX(int x) {
		this.oldX = this.x;
		this.x = x;
	}

	public int getPosY() {
		return y;
	}

	public void setPosY(int y) {
		this.oldY = this.y;
    		this.y = y; 
	}*/

	public void setImage(Image image) {
		this.image = image;
	}
}
