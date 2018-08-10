package carcassonne.ui;

import java.util.ArrayList;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class SpriteStore {
	public static final int NUMBER_OF_TILE_SPRITES = 24;
	public static final int NUMBER_OF_MEEPLE_SPRITES = 5;
	public static final String TILE_LOCATIONS = "/home/23242965/Desktop/trainee/stellenbosch-internship/carcassonne/src/main/resources/carcassonne/tiles/tile";
	public static final String MEEPLE_LOCATIONS = "/home/23242965/Desktop/trainee/stellenbosch-internship/carcassonne/src/main/resources/carcassonne/meeples/meeple";
	
	public ArrayList<Sprite> sprites;

	public SpriteStore() {
        	sprites = new ArrayList<Sprite>();
    	}

	public void loadTiles() {
		for (int i = 0; i < NUMBER_OF_TILE_SPRITES; ++i)
			loadSprite(TILE_LOCATIONS + i + ".jpg");
	}

	public void loadMeeples() {
		for (int i = 0; i < NUMBER_OF_MEEPLE_SPRITES; ++i)
			loadSprite(MEEPLE_LOCATIONS + i + ".png");
	}

    	public boolean loadSprite(String ref) {
       		BufferedImage sourceImage = null;
        	try {
           		sourceImage = ImageIO.read(new File(ref));
        	} catch (IOException e) {
            		System.out.println("Failed to load image " + ref);
            		e.printStackTrace();
            		return false;
        	}
		Sprite sprite = new Sprite(sourceImage);
       		sprites.add(sprite);
        	return true;
	}
	/*
	public Sprite getSprite(String ref) {
      		Sprite sprite = sprites.get(ref);
      	  	if (sprite == null) {
            		if (loadSprite(ref)) {
                		return sprites.get(ref);
            		}
        	}
        	return sprite;
    	}*/

}
