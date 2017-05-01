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
public class Bomb extends GameObject{
    private long plantTime;
    private long duration;
    private int rangeOfExplosion = 1;
    private int plantedBy;
    public boolean isExploded = false;
    Pacman owner;
    public Bomb(int x, int y, long time, Image image, long duration, int width, int height,Pacman owner) {
        super(x,y, image,  width,  height);
        plantTime = time;
        this.duration = duration;
        this.owner = owner;
    }
    public void setRange(int range){
        rangeOfExplosion = range;
    }
    public int getRange(){
        return rangeOfExplosion;
    }
    public boolean isAvailableToExplosion(long time){
            if (time - plantTime - duration > 0){
            	/*
            	System.out.println("Time : " + time + "\n");
            	System.out.println("Plant Time : " + plantTime + "\n");
            	System.out.println("Duration : " + duration + "\n");
            	System.out.println("LUL : " + (time - plantTime - duration) + "\n");
                */
            	//System.out.println("true");
            	return true;
            }
            else {
            	//System.out.println("false");
                return false;
                }
    }
    public boolean isInAnimationPhase(long currentTime, int animationDuration){
        return ((currentTime - plantTime - duration) <= animationDuration) && isAvailableToExplosion(currentTime);
    }
    public long getPlantTime(){
        return plantTime;
    }
    public Pacman getOwner(){
        return owner;
    }
    public void explode(){
    	System.out.println("Bomb exploded.");
        isExploded = true;
    }
}
