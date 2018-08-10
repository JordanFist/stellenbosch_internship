package carcassonne.ui;

import carcassonne.core.Direction;
import carcassonne.core.Position;
import carcassonne.core.Move;
import carcassonne.core.Place;
import carcassonne.core.Player;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.awt.Graphics;

class Panel {
	tileDrawable tile;
	meepleDrawable meeple;

	public Panel(tileDrawable tile, meepleDrawable meeple) {
		this.tile = tile;	
		this.meeple = meeple;	
	}
}

public class Window extends JFrame {
	public Background background = new Background();
	public SpriteStore tileSprites = new SpriteStore();
	public SpriteStore meepleSprites = new SpriteStore();

	public ArrayList<Panel> Panels = new ArrayList<Panel>();
	

	public Window() {
   		this.setTitle("Carcassonne");
   		this.setSize(1000, 1000);
   		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setContentPane(background);
		background.setLayout(null);


		tileSprites.loadTiles();
		meepleSprites.loadMeeples();
		addTile(3, new Position(0, 0), Direction.NORTH);
		
	}

	public void update(Move m) {
		addTile(m.tile.id, m.tile.pos, m.tile.dir);
		addMeeple(m.place, m.player);
	}

	public void addTile(int id, Position pos, Direction dir) {
		tileDrawable panel = new tileDrawable(this, pos.x, -pos.y, dir);
		int width = 86;
		int height = 86;		
		int X = getWidth() / 2 - width / 2 + width * pos.x;
		int Y = (getHeight() - getInsets().top) / 2 - height / 2 - height * pos.y;
		
		background.add(panel).setBounds(X, Y, width, height);
		Panels.add(new Panel(panel, null));
		addSpriteTile(panel, id);
	}

	public void removeTile() {
		
	}

	public void addMeeple(Place place, Player player) {
		if (place != Place.NO_MEEPLE) {
			int i = Panels.size() - 1;
			tileDrawable tilePanel = Panels.get(i).tile;

			Position pos = place.meeplePosition(tilePanel.getWidth(), tilePanel.getHeight());
			meepleDrawable meeplePanel = new meepleDrawable(tilePanel, pos.x, pos.y);

			int width = 15;
			int height = 15;
		
			tilePanel.add(meeplePanel).setBounds(pos.x, pos.y, width, height);
			Panels.get(i).meeple = meeplePanel;
			addSpriteMeeple(meeplePanel, player.id);
		}		
	}

	public void removeMeeple(int round) {
		//Panels.get(round).tile.setImage(null);
		//Panels.get(round).tile.repaint();
	}

	public void Wait(int seconde) {
		try {
			Thread.sleep(seconde * 1000);
		} catch (InterruptedException e) {
        		e.printStackTrace();
      		}
	}

	public void addSpriteMeeple(meepleDrawable panel, int i) {
		panel.setImage(meepleSprites.sprites.get(i).getImage());
		panel.repaint();
	}

	public void addSpriteTile(tileDrawable panel, int i) {
		panel.setImage(tileSprites.sprites.get(i).getImage());
		panel.repaint();
	}
}
