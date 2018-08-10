package carcassonne.ui;

import carcassonne.core.Direction;
import carcassonne.core.Position;
import carcassonne.core.Move;

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

public class tileDrawable extends JPanel { 
	public Window window;
	public Image image;
	public int x = 0;
	public int y = 0;
	public Direction dir;
	public int oldX = 0;
	public int oldY = 0;
	
	public tileDrawable(Window window, int x, int y, Direction dir) {
		this.window = window;
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//g.setColor(new Color(245, 245, 220));
		//g.fillRect(oldPosX, oldPosY, 100, 100);
	
		int X = window.getWidth() / 2 - this.getWidth() / 2  + this.getWidth() * x;
		int Y = (window.getHeight() - window.getInsets().top) / 2 - this.getHeight() / 2  + this.getHeight() * y;

		AffineTransform rotation = new AffineTransform();
		rotation.rotate(Math.toRadians(360 - 90 * dir.get()), this.getWidth()/2, this.getHeight()/2);
		g2d.drawImage(image, rotation, null);
		this.setBounds(X, Y, this.getWidth(), this.getHeight());
   	}	

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
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
