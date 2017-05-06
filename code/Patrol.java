/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author LUL
 */
public class Patrol extends MovingObject{
    private int dx = 1;
    private int dy = 0;
    private int initialSpeed;
    private boolean isFollowPatrol;
    private boolean isFast;
    //private boolean alive = true;
    public Patrol(int x, int y,  int speed,Image image, boolean isFollow, int width, int height) {
        super(x, y, image, speed,width, height);
        isFollowPatrol = isFollow;
        initialSpeed = speed;
    }
    public boolean isFollowPatrol(){
        return isFollowPatrol;
    } 
    public void returnInitialSpeed(){
        speed = initialSpeed;
    }  
    public boolean isFast(){
        return isFast;
    }     
    public boolean isInRange(Pacman player, int range){
        if(Math.sqrt(Math.pow(player.getX() - x,2) + Math.pow(player.getY() - y,2)) < range)
            return true;
        return false;
    }    
    ArrayList<Integer> routes;
    public void initRoutes(ArrayList<Integer> list){
        routes = list;
    }
    public void move() {
            try{    
            if(isMovementBlocked()){
                int randomizer = (int)(Math.random()*routes.size());
                int direction = routes.get(randomizer);
                switch(direction){
                    case 1:
                        dx = 1;
                        dy = 0;
                        break;
                    case 2:
                        dy = 1;
                        dx = 0;
                        break;
                    case 3:
                        dx = -1;
                        dy = 0;
                        break;
                    case 4:
                        dy = -1;
                        dx = 0;
                        break;
                }
            }
            /*
            else if(routes.size() > 2){
                int flag = (int)(Math.random()*routes.size());
                
                switch(routes.get(flag)){
                    case 1:
                        dx = 1;
                        dy = 0;
                        break;
                    case 2:
                        dy = 1;
                        dx = 0;
                        break;
                    case 3:
                        dx = -1;
                        dy = 0;
                        break;
                    case 4:
                        dy = -1;
                        dx = 0;
                        break;
                }
            }
            */
            x += speed * dx ;
            y += speed * dy ;
            }catch(Exception e){
                throw e;
            }
    }   
    private boolean isMovementBlocked(){
        
        try{
        if (dx > 0 && !routes.contains(1))
            return true;
        if (dx < 0 && !routes.contains(3))
            return true;
        if (dy > 0 && !routes.contains(2))
            return true;
        if (dy < 0 && !routes.contains(4))
            return true;
        }catch (Exception e){
            return true;
        }
        return false;
    }   
    

}

