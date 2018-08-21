package carcassonne.ui;

import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Image;
import java.io.File;
import java.awt.Color;
import java.awt.Font;

public class Button extends JButton implements MouseListener {
	public static final String FONT_LOCATION = "../../../resources/main/carcassonne/ButtonFonts/";
	public availableCards Cards = new availableCards(this);

	public Background background;
	public String name;
	public Image font;

	public Button (Background background, String name) {
		this.name = name;
		this.background = background;
		this.addMouseListener(this);
		background.add(Cards).setBounds(0, 0, 1, 1);
		setImage();
	}

	public void setImage() {
		try {
			font = ImageIO.read(new File(FONT_LOCATION + "availableCards.jpg")); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getX() {
		return background.getWidth() / 20;
	}

	public int getY() {
		return background.getHeight() / 20;
	}

	public int getWidth() {
		return background.getWidth() / 9;
	}

	public int getHeight() {
		return background.getHeight() / 30;
	}

	@Override
   	public void mouseEntered(MouseEvent e) {		
		Cards.setVisible(true);
        }

	@Override
    	public void mouseExited(MouseEvent e) {
		Cards.setVisible(false);
        }

	@Override
	public void mousePressed(MouseEvent e) {
    	}

	@Override
    	public void mouseReleased(MouseEvent e) {
    	}

	@Override
    	public void mouseClicked(MouseEvent e) {
       	}

	public void paintComponent(Graphics g) {
		g.drawImage(font, 0, 0, this.getWidth(), this.getHeight(), this);
		g.setFont(new Font("Courier", Font.BOLD, 10));
		g.setColor(Color.white);
		g.drawString(this.name, this.getWidth() / 10, this.getHeight() / 2 + 5);
		setBounds(getX(), getY(), getWidth(), getHeight());
	}
}
