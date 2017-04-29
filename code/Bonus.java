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
public abstract class Bonus extends GameObject{
    int bonusAmount;
    public Bonus(int x, int y, Image image, int amount, int width, int height){
        super(x,y,image, width,height);
        bonusAmount = amount;
    }
    public abstract void bonus(Pacman p);
}
