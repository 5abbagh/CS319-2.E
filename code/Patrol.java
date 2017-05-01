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
    //private int speed = 1;
    private int dx = 1;
    private int dy = 0;
    private int width = 1366;
    private int height = 800;
    private int initialSpeed;
    private boolean isFollowPatrol;
    private boolean isFast;
    private boolean alive = true;
    private int[][] table;
    public Patrol(int x, int y,  int speed,Image image, boolean isFollow, int width, int height, int[][] table) {
        super(x, y, image, speed,width, height);
        isFollowPatrol = isFollow;
        initialSpeed = speed;
        this.table = table;
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
    public void move() {
            
            try{    
            ArrayList<Integer> routes = getAvailableRoutes();
            
            if(isMovementBlocked()){
               
                int pos = 0;
                if (dx == 1)
                    pos = 1;
                if (dy == 1)
                    pos = 2;
                if (dx == -1)
                    pos = 3;
                if (dy == -1)
                    pos = 4;
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
                    case 0:
                        dy = -1;
                        dx = 0;
                        break;
                }
            }
            else if (routes.size() > 2){
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
                    case 0:
                        dy = -1;
                        dx = 0;
                        break;
                }
            }
            
            x += speed * dx ;
            y += speed * dy ;
            }catch(Exception e){
                e.printStackTrace();
                throw e;
            }
    }   
    private ArrayList<Integer> getAvailableRoutes(){
        
        ArrayList<Integer> list = new ArrayList();
        
            if(!isRightBlocked(speed))
                list.add(1);
            if(!isLeftBlocked(-speed))
                list.add(3);
            if(!isTopBlocked(-speed))
                list.add(0);
            if(!isBotBlocked(speed))
                list.add(2);
        return list;
    }   
    private boolean isMovementBlocked(){
        try{
        if (dx > 0 && isRightBlocked(dx))
            return true;
        if (dx < 0 && isLeftBlocked(dx))
            return true;
        if (dy > 0 && isBotBlocked(dy))
            return true;
        if (dy < 0 && isTopBlocked(dy))
            return true;
        }catch (Exception e){
            return true;
        }
        return false;
    }   
    private boolean isRightBlocked(int dx){
        int unitX = width / table[0].length;
        int unitY = height / table.length;

        if(y % unitY != 0){
            return true;
        }
        if((x + dx) < 0 || x + dx + unitX  >= unitX * table[0].length){
            return true;            
        }
        if(dx == 1)
            if(table[y / unitY][(int)(x + dx +unitX )/unitX] != 0 || table[(y + unitY - 1) / unitY][(int)(x + dx +unitX )/unitX] != 0){
                return true;
            }
        return false;
    }  
    private boolean isLeftBlocked(int dx){
        int unitX = width / table[0].length;
        int unitY = height / table.length;
        if(y % unitY != 0){

            return true;
        }
        if((x + dx) < 0 || x + dx + unitX  > unitX * table[0].length){
            return true;            
        }
        if(dx == -1)
            if(table[y / unitY][(x + dx) /unitX] != 0 || table[(y + unitY - 1) / unitY][(x + dx) /unitX] != 0){
                return true;
            }
        return false;
    }  
    private boolean isTopBlocked(int dy){
        int unitX = width / table[0].length;
        int unitY = height / table.length;
        
        if(x % unitX != 0){
            return true;
        }
        if((y + dy) < 0 || y + dy + unitY  > unitY * table.length){
            return true;
        }    
        if(dy == -1)
            if(table[((y + dy) / unitY)][x /unitX] != 0 || table[((y + dy) / unitY)][(x + unitX - 1) /unitX] != 0){
                return true;
            }

        return false;
    }     
    private boolean isBotBlocked(int dy){
        int unitX = width / table[0].length;
        int unitY = height / table.length;
        
        if(x % unitX != 0){
            return true;
        }
        if((y + dy) < 0 || y + dy + unitY  >= unitY * table.length){
            return true;
        }
        if(dy == 1)
            if(table[(y + dy +unitY )/unitY][x /unitX] != 0 || table[(y + dy +unitY )/unitY][(x + unitX - 1) /unitX] != 0){
                return true;
            }
        
        return false;
    }
    public boolean isAlive(){
        return alive;
    }
    
    public void die() {
        alive = false;
    }
}

