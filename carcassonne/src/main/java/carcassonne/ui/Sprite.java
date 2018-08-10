package carcassonne.ui;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * A sprite is a reprentation of an image. It is loaded <b>ONLY</b> by the SpriteStore, to ensure that there is only one reprenstation of each image in RAM.
 * 
 * @author Stephan
 */
public class Sprite
{
    Image image;

    /**
     * Constructor for objects of class Sprite
     */
    protected Sprite(Image image)
    {
        this.image = image;
    }

    public int getWidth(){
        return image.getWidth(null);
    }
    
    public int getHeight(){
        return image.getHeight(null);
    }
    
    public Image getImage(){
        return image;
    }
}
