package carcassonne.ui;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Color; 
import java.awt.Font;

public class nextTileDrawable extends JPanel {
	public Window window;
	public Image image;
	
	public nextTileDrawable(Window window) {
		this.window = window;
	}

	public void setImage(Image image) {
		this.image = image;
		repaint();
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(245, 245, 220));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.red);
		g.drawRect(0, 0, getWidth(), getHeight());

		g.setFont(new Font("courier", Font.BOLD, 12));
		g.setColor(Color.black);
		g.drawString("Next card", 20, 10);
		
		g.drawImage(image, 50 - 35, 50 - 35, 70, 70, null);
		
		this.setBounds(window.getWidth() - 100, window.getHeight() - window.getInsets().top - 100, 100, 100);
   	}	
}
