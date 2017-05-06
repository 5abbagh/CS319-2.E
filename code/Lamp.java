/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner;

import java.awt.Image;

/**
 *
 * @author Mehmet
 */
public class Lamp extends GameObject {
    int radius = 0;
    public Lamp(int x, int y, Image image, int width, int height, int radius) {
        super(x, y, null, height, height);
        this.radius = radius;
    }
    
    public int getRadius(){
        return radius;
    }
}
