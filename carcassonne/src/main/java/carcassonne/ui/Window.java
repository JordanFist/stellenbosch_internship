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
	public nextTileDrawable nextTile = new nextTileDrawable(this);
	public spriteStore tileSprites = new spriteStore();
	public spriteStore meepleSprites = new spriteStore();
	public panelStore drawableStore = new panelStore();
	public Camera camera = new Camera(drawableStore);

	public Window() {
   		this.setTitle("Carcassonne");
   		this.setSize(1000, 1000);
   		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setContentPane(background);
		background.setLayout(null);
		background.add(nextTile).setBounds(getWidth() - 100, getHeight() - 100, 100, 100);
		this.addKeyListener(camera);

		tileSprites.loadTiles();
		meepleSprites.loadMeeples();

		addTile(3, new Position(0, 0), Direction.NORTH);
	}

	public void update(Move m) {
		addTile(m.tile.id, m.tile.pos, m.tile.dir);
		addMeeple(m.place, m.player);
	}

	public void changeCardToPlay(Tile t) {
		nextTile.setImage(tileSprites.sprites.get(t.id).getImage());
		nextTile.repaint();
	}

	public void addTile(int id, Position pos, Direction dir) {
		int X = getWidth() / 2 - TILE_WIDTH / 2 + TILE_WIDTH * pos.x + camera.getX();
		int Y = (getHeight() - getInsets().top) / 2 - TILE_HEIGHT / 2 - TILE_HEIGHT * pos.y + camera.getY();

		tileDrawable tile = new tileDrawable(this, camera, pos.x, pos.y, dir);
		background.add(tile).setBounds(X, Y, TILE_WIDTH, TILE_HEIGHT);

		drawableStore.add(tile, null);
		addSpriteTile(tile, id);
	}

	public void removeTile() {
		//TODO
	}

	public void addMeeple(Place place, Player player) {
		if (place != Place.NO_MEEPLE) {
			tileDrawable tile = drawableStore.getLast().tile;
			Position pos = place.meeplePosition(tile.getWidth(), tile.getHeight());

			meepleDrawable meeple = new meepleDrawable(tile, pos.x, pos.y);
			tile.add(meeple).setBounds(pos.x, pos.y, MEEPLE_WIDTH, MEEPLE_HEIGHT);

			drawableStore.change(drawableStore.getLast(), null, meeple);
			addSpriteMeeple(meeple, player.id);
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

	public void addSpriteMeeple(meepleDrawable meeple, int i) {
		meeple.setImage(meepleSprites.sprites.get(i).getImage());
		meeple.repaint();
	}

	public void addSpriteTile(tileDrawable tile, int i) {
		tile.setImage(tileSprites.sprites.get(i).getImage());
		tile.repaint();
	}
}
