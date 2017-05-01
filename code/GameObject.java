
package mazerunner;

/**
 *
 * @author Mehmet
 */

import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class GameObject
{
    
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image image;

    public GameObject(int x, int y, Image image, int width, int height) {
        this.x = x;
        this.y = y;
        vis = true;
        this.image = image;
        this.width = width;
        this.height = height;
    }


    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }
}
