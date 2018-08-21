package carcassonne.ui;

import carcassonne.core.Direction;
import carcassonne.core.Position;
import carcassonne.core.Move;
import carcassonne.core.Place;
import carcassonne.core.Player;
import carcassonne.core.Tile;

import javax.swing.JFrame;
import java.awt.Color;

public class Window extends JFrame {
	public static final int TILE_WIDTH = 70;
	public static final int TILE_HEIGHT = 70;
	public static final int MEEPLE_WIDTH = 10;
	public static final int MEEPLE_HEIGHT = 10;

	public Background background = new Background(new Color(245, 245, 220));
	public spriteStore tileSprites = new spriteStore();
	public spriteStore meepleSprites = new spriteStore();
	public panelStore drawableStore = new panelStore();
	public Camera camera = new Camera(drawableStore);
	public Button availableCards = new Button(background, "Available Cards");

	public Window() {
   		this.setTitle("Carcassonne");
   		this.setSize(1000, 1000);
   		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		init();
	}

	public void init() {
		this.setContentPane(background);
		background.setLayout(null);
		this.addKeyListener(camera);
		this.add(availableCards).setBounds(0, 0, 1, 1);
		this.requestFocus();

		tileSprites.loadTiles();
		meepleSprites.loadMeeples();

		addTile(3, new Position(0, 0), Direction.NORTH);
	}

	public void update(Move m) {
		addTile(m.tile.id, m.tile.pos, m.tile.dir);
		addMeeple(m.place, m.player);
		availableCards.Cards.update(m.tile.id);
	}

	public void addTile(int id, Position pos, Direction dir) {
		int X = getWidth() / 2 - TILE_WIDTH / 2 + TILE_WIDTH * pos.x + camera.getX();
		int Y = (getHeight() - getInsets().top) / 2 - TILE_HEIGHT / 2 - TILE_HEIGHT * pos.y + camera.getY();

		tileDrawable tile = new tileDrawable(this, camera, pos, dir);
		background.add(tile).setBounds(X, Y, TILE_WIDTH, TILE_HEIGHT);

		drawableStore.add(tile, null);
		tile.addSprite(id);
	}

	public void addMeeple(Place place, Player player) {
		if (place != Place.NO_MEEPLE) {
			tileDrawable tile = drawableStore.getLast().tile;
			Position pos = place.meeplePosition(tile.getWidth(), tile.getHeight());

			meepleDrawable meeple = new meepleDrawable(this, pos);
			tile.add(meeple).setBounds(pos.x, pos.y, MEEPLE_WIDTH, MEEPLE_HEIGHT);

			drawableStore.change(drawableStore.getLast(), null, meeple);
			meeple.addSprite(player.id);
		}		
	}

	public void removeMeeple(int round) {
		drawableStore.getPanel(round).meeple.setImage(null);
		drawableStore.getPanel(round).meeple.repaint();
		drawableStore.getPanel(round).tile.repaint();
	}

	public void Wait(int seconde) {
		try {
			Thread.sleep(seconde * 1000);
		} catch (InterruptedException e) {
        		e.printStackTrace();
      		}
	}
}
