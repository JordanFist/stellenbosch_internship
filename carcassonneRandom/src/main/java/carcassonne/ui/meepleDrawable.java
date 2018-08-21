package carcassonne.ui;

import carcassonne.core.Position;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Image;

public class meepleDrawable extends JPanel {
	public Window window;
	public Image image;
	public Position pos;
	
	public meepleDrawable(Window window, Position pos) {
		this.window = window;
		this.pos = pos;
	}

	public void addSprite(int id) {
		setImage(window.meepleSprites.sprites.get(id).getImage());
	}

	public void setImage(Image image) {
		this.image = image;
		repaint();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		setBounds(pos.x, pos.y, getWidth(), getHeight());
   	}	
}
