package mazerunner;

/**
 *
 * @author Mehmet
 */
import java.util.ArrayList;
import java.awt.Image;
public class Pacman extends MovingObject {
    private final int START_SCORE = 250;
    private final int START_HEALTH = 3;
    private final int OWN_BOMB_COST = 100;
    private final int ENEMY_BOMB_COST = 100;
    private long finalBombTime;
    private long bombDebounce = 500;
    private int startX;
    private int startY;
    private int finishX;
    private int finishY;
    private int dx;
    private int dy;
    private int lives = 3;
    private int score;
    private boolean isDead = false;
    private ArrayList<Bomb> bombs;
    private int playerNumber;
    private int[][] keys = {{81,69,65,68,87,83},{79,80,37,39,38,40}};

    
    //controls for player1 w a s d, q - place bomb, e - place bomb near other player;
    //controls for player2 UP LEFT DOWN RIGHT, O - place bomb, P - place bomb near other player;
    public Pacman(int x, int y, int speed, Image image, int playerNumber, int width, int height) {
        super(x, y, image, speed, width, height);
        startX = x;
        startY = y;
        bombs = new ArrayList<Bomb>();
        this.playerNumber = playerNumber;
        finalBombTime = System.currentTimeMillis();
        score = START_SCORE;
        lives = START_HEALTH;
    }  
    public void setFinishPoints(int x, int y){
        finishX = x;
        finishY = y;
    }   
    public int getFinishX(){
        return finishX;
    }    
    public int getFinishY(){
        return finishY;
    }   
    public int getPlayerNumber(){
        return playerNumber;
    }    
      
    public void setImage(Image img){
        image = img;
    }    
    public void setScore(int score){
        this.score = score;
    }    
    public int getScore(){
        return score;
    }    
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }   
    public void decreaseLife(){
        lives--;
        if (lives <= 0)
            isDead = true;
    }   
    public void setLife(int life){
        lives = life;
    }   
    public int getLives(){
        return lives;
    }   

    public boolean bomb() {
        if(score >= OWN_BOMB_COST && System.currentTimeMillis() > finalBombTime + bombDebounce){
            finalBombTime = System.currentTimeMillis();
            score -= OWN_BOMB_COST;
            
            return true;
        }
        return false;
    }
    public boolean bombNearEnemy(){       
        if(score >= ENEMY_BOMB_COST && System.currentTimeMillis() > finalBombTime + bombDebounce){
            finalBombTime = System.currentTimeMillis();
            score -= ENEMY_BOMB_COST;
            return true;
        }
        return false;
    }
    public void resetLocation(){
        x = startX;
        y = startY;
    }
    public void stop(){
        dx = 0;
        dy = 0;
    }    
    public boolean isFinished(){
        return(x - finishX < width)&&(y - finishY < height);
    }   
    public void die(){
        decreaseLife();
        resetLocation();
    }
    public void increaseScore(int amount){
        setScore(getScore() + amount);
    }
}
