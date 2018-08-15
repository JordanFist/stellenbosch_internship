package carcassonne.ui;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Image;


public class meepleDrawable extends JPanel { 
	public tileDrawable panel;
	public Image image;
	public int X;
	public int Y;
	
	public meepleDrawable(tileDrawable panel, int X, int Y) {
		this.panel = panel;
		this.X = X;
		this.Y = Y;
	}

	public void paintComponent(Graphics g) {
		//g.setColor(new Color(245, 245, 220));
		//g.fillRect(oldPosX, oldPosY, 100, 100);
		
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
		this.setBounds(X, Y, this.getWidth(), this.getHeight());
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
