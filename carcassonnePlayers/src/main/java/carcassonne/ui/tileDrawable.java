package carcassonne.ui;

import carcassonne.core.Direction;
import carcassonne.core.Position;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.awt.Font;

public class tileDrawable extends JPanel {
	public Window window;
	public Image image;
	public Direction dir;
	public Camera camera;
	public Position pos;
	
	public tileDrawable(Window window, Camera camera, Position pos, Direction dir) {
		this.window = window;
		this.pos = pos;
		this.dir = dir;	
		this.camera = camera;
	}

	public int getX() {
		return window.getWidth() / 2 - getWidth() / 2 + getWidth() * pos.x + camera.getX();
	}

	public int getY() {
		return (window.getHeight() - window.getInsets().top) / 2 - getHeight() / 2 - getHeight() * pos.y + camera.getY();

	}

	public void setImage(Image image) {
		this.image = image;
		repaint();
	}

	public void addSprite(int id) {
		setImage(window.tileSprites.sprites.get(id).getImage());
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform rot = new AffineTransform();
		rot.rotate(Math.toRadians(360 - 90 * dir.get()), getWidth() / 2, getHeight() / 2);
		g2d.drawImage(image, rot, null);
		setBounds(getX(), getY(), getWidth(), getHeight());
		if (pos.x == 0 && pos.y == 0) {
			g.setFont(new Font("Courier", Font.BOLD, 9));
			g.setColor(Color.black);
			g.drawString("starting tile", getWidth() / 30, getHeight() - getHeight() / 5);
		}
   	}
}
