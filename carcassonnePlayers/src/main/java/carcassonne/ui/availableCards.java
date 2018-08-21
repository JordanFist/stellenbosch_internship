package carcassonne.ui;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Image;
import java.io.File;
import java.awt.Color;
import java.awt.Font;

public class availableCards extends JPanel {
	public static final String CARDS_LOCATION = "../../../resources/main/carcassonne/";

	public int remainingCards[] = {2, 4, 1, 3, 5, 2, 1, 3, 2, 3, 3, 3, 2, 3, 2, 3, 1, 3, 2, 1, 8, 9, 4, 1};
	public Image cards;
	public Button button;

	public availableCards(Button button) {
		this.setVisible(false);
		this.button = button;
		setImage();
	}

	public void setImage() {
		try {
			cards = ImageIO.read(new File(CARDS_LOCATION + "Cards.png")); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return button.background.getWidth() / 2 + button.background.getWidth() / 5;
	}

	public int getHeight() {
		return button.background.getHeight() / 3;
	}

	public void update(int i) {
		if (remainingCards[i] > 0)
			--remainingCards[i];
		else
			System.out.println("ERROR in update availableCards function");
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(cards, 0, 0, this.getWidth(), this.getHeight(), this);
		setBounds(button.getX(), button.getY() + button.getHeight() , getWidth(), getHeight());

		g.setFont(new Font("Courier", Font.BOLD, (getWidth() + getHeight()) / 100));
		g.setColor(Color.black);
		g.drawString(remainingCards[21] + " remaining", getWidth()/80, getHeight() / 3 - getHeight() / 20);
		g.drawString(remainingCards[9] + " remaining", getWidth()/8, getHeight() / 3 - getHeight() / 20);
		g.drawString(remainingCards[14] + " remaining", getWidth()/8 + getWidth() / 9, getHeight() / 3 - getHeight() / 20);
		g.drawString(remainingCards[19] + " remaining", getWidth()/3 + getWidth() / 90 , getHeight() / 3 - getHeight() / 20);
		g.drawString(remainingCards[16] + " remaining", getWidth()/2 - getWidth() / 20, getHeight() / 3 - getHeight() / 20);
		g.drawString(remainingCards[15] + " remaining", getWidth()/2 + getWidth() / 16, getHeight() / 3 - getHeight() / 20);
		g.drawString(remainingCards[11] + " remaining", getWidth()/2 + getWidth() / 6, getHeight() / 3 - getHeight() / 20);
		g.drawString(remainingCards[20] + " remaining", getWidth() - getWidth() / 5 - getWidth() / 50, getHeight() / 3 - getHeight() / 20);
		g.drawString(remainingCards[22] + " remaining", getWidth() - getWidth() / 9, getHeight() / 3 - getHeight() / 20);



		g.drawString(remainingCards[4] + " remaining", getWidth()/80, getHeight() / 2 + getHeight() / 8);
		g.drawString(remainingCards[8] + " remaining", getWidth()/8, getHeight() / 2 + getHeight() / 8);
		g.drawString(remainingCards[17] + " remaining", getWidth()/8 + getWidth() / 9, getHeight() / 2 + getHeight() / 8);
		g.drawString(remainingCards[1] + " remaining", getWidth()/3 + getWidth() / 90 , getHeight() / 2 + getHeight() / 8);
		g.drawString(remainingCards[0] + " remaining", getWidth()/2 - getWidth() / 20, getHeight() / 2 + getHeight() / 8);
		g.drawString(remainingCards[13] + " remaining", getWidth()/2 + getWidth() / 16, getHeight() / 2 + getHeight() / 8);
		g.drawString(remainingCards[5] + " remaining", getWidth()/2 + getWidth() / 6, getHeight() / 2 + getHeight() / 8);
		g.drawString(remainingCards[3] + " remaining", getWidth() - getWidth() / 5 - getWidth() / 50, getHeight() / 2 + getHeight() / 8);
		g.drawString(remainingCards[10] + " remaining", getWidth() - getWidth() / 9, getHeight() / 2 + getHeight() / 8);	



		g.drawString(remainingCards[6] + " remaining", getWidth()/8 + getWidth() / 21, getHeight() - getHeight() / 25);
		g.drawString(remainingCards[18] + " remaining", getWidth()/8 + getWidth() / 7 + getWidth() / 60, getHeight() - getHeight() / 25);
		g.drawString(remainingCards[2] + " remaining", getWidth()/2 - getWidth() / 9, getHeight() - getHeight() / 25);
		g.drawString(remainingCards[23] + " remaining", getWidth()/2 + getWidth() / 90, getHeight() - getHeight() / 25);
		g.drawString(remainingCards[12] + " remaining", getWidth() / 2 + getWidth() / 7 - getWidth() / 40, getHeight() - getHeight() / 25);
		g.drawString(remainingCards[7] + " remaining", getWidth() - getWidth() / 4 - getWidth() / 45, getHeight() - getHeight() / 25);


		g.drawString("Card 21", getWidth()/80, getHeight() / 45);
		g.drawString("Card 9", getWidth()/8, getHeight() / 45);
		g.drawString("Card 14", getWidth()/8 + getWidth() / 9, getHeight() / 45);
		g.drawString("Card 19", getWidth()/3 + getWidth() / 90 , getHeight() / 45);
		g.drawString("Card 16", getWidth()/2 - getWidth() / 20, getHeight() / 45);
		g.drawString("Card 15", getWidth()/2 + getWidth() / 16, getHeight() / 45);
		g.drawString("Card 11", getWidth()/2 + getWidth() / 6, getHeight() / 45);
		g.drawString("Card 20", getWidth() - getWidth() / 5 - getWidth() / 50, getHeight() / 45);
		g.drawString("Card 22", getWidth() - getWidth() / 9, getHeight() / 45);



		g.drawString("Card 4", getWidth()/80, getHeight() / 3 + getHeight() / 30);
		g.drawString("Card 8", getWidth()/8, getHeight() / 3 + getHeight() / 30);
		g.drawString("Card 17", getWidth()/8 + getWidth() / 9, getHeight() / 3 + getHeight() / 30);
		g.drawString("Card 1", getWidth()/3 + getWidth() / 90 , getHeight() / 3 + getHeight() / 30);
		g.drawString("Card 0", getWidth()/2 - getWidth() / 20, getHeight() / 3 + getHeight() / 30);
		g.drawString("Card 13", getWidth()/2 + getWidth() / 16, getHeight() / 3 + getHeight() / 30);
		g.drawString("Card 5", getWidth()/2 + getWidth() / 6, getHeight() / 3 + getHeight() / 30);
		g.drawString("Card 3", getWidth() - getWidth() / 5 - getWidth() / 50, getHeight() / 3 + getHeight() / 30);
		g.drawString("Card 10", getWidth() - getWidth() / 9, getHeight() / 3 + getHeight() / 30);	



		g.drawString("Card 6", getWidth()/8 + getWidth() / 21, getHeight() / 2 + getHeight() / 5);
		g.drawString("Card 18", getWidth()/8 + getWidth() / 7 + getWidth() / 60, getHeight() / 2 + getHeight() / 5);
		g.drawString("Card 2", getWidth()/2 - getWidth() / 9, getHeight() / 2 + getHeight() / 5);
		g.drawString("Card 23", getWidth()/2 + getWidth() / 90, getHeight() / 2 + getHeight() / 5);
		g.drawString("Card 12", getWidth() / 2 + getWidth() / 7 - getWidth() / 40, getHeight() / 2 + getHeight() / 5);
		g.drawString("Card 7", getWidth() - getWidth() / 4 - getWidth() / 45, getHeight() / 2 + getHeight() / 5);
	}
}
