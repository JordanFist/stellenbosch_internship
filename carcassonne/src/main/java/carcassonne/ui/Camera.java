package carcassonne.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera implements KeyListener{
	public panelStore drawableStore;
	public int X = 0;
	public int Y = 0;

	public Camera(panelStore drawableStore) {
		this.drawableStore = drawableStore;
	}

	@Override
   	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			Y -= 20; 
			for (Panel panel : drawableStore.panels) 
				panel.tile.repaint();
		} 
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Y += 20; 
			for (Panel panel : drawableStore.panels) 
				panel.tile.repaint();
		} 
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			X -= 20;
			for (Panel panel : drawableStore.panels) 
				panel.tile.repaint();
		} 
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			X += 20;
			for (Panel panel : drawableStore.panels) 
				panel.tile.repaint();
		}	
	}

	@Override
    	public void keyTyped(KeyEvent e) {

	}

	@Override
    	public void keyReleased(KeyEvent e) {
	
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}
}
