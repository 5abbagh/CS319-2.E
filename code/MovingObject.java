/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner;

import java.awt.Image;

/**
 *
 * @author LUL
 */
public abstract class MovingObject extends GameObject{
    int speed;
    boolean isAlive = true;
    public MovingObject(int x, int y, Image image, int speed, int width, int height){
        super(x,y,image, width, height);
        this.speed = speed;
    }
    public void move(int direction){
        switch(direction){
            case 1:
                x += speed; 
                break;
            case 2:
                y += speed; 
                break;
            case 3:
                x -= speed; 
                break;
            case 4:
                y -= speed; 
                break;
        }
    }
    public void die(){
        isAlive= false;
    }
    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    
    public boolean isAlive(){
        return isAlive;
    }
}
