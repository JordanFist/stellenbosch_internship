package carcassonne.ui;

import carcassonne.core.Direction;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class tileDrawable extends JPanel {
	public Window window;
	public Image image;
	public Direction dir;
	public Camera camera;
	public int x;
	public int y;
	
	public tileDrawable(Window window, Camera camera, int x, int y, Direction dir) {
		this.window = window;
		this.x = x;
		this.y = y;
		this.dir = dir;	
		this.camera = camera;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//g.setColor(new Color(245, 245, 220));
		//g.fillRect(oldPosX, oldPosY, 100, 100);

		int X = window.getWidth() / 2 - getWidth() / 2 + getWidth() * x + camera.getX();
		int Y = (window.getHeight() - window.getInsets().top) / 2 - getHeight() / 2 - getHeight() * y + camera.getY();

		AffineTransform rotation = new AffineTransform();
		rotation.rotate(Math.toRadians(360 - 90 * dir.get()), this.getWidth() / 2, this.getHeight() / 2);
		g2d.drawImage(image, rotation, null);
		this.setBounds(X, Y, this.getWidth(), this.getHeight());
   	}

	public void setImage(Image image) {
		this.image = image;
	}
}
