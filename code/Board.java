/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazerunner;

import java.awt.Dimension;

/**
 *
 * @author LUL
 */

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
//import sun.audio.ContinuousAudioDataStream;
import javax.sound.sampled.AudioInputStream;

public class Board extends JPanel implements ActionListener {
    public int numP = 1;
    public  final int WIDTH = 1368;
    public  final int HEIGHT = 768-125;
    
    private final int PLAYER_SPEED = 10;
    private final int PATROL_SPEED = 1;
    private final int NUMBER_OF_SLOW_PATROLS = 0;
    private final int NUMBER_OF_FAST_PATROLS = 3;
    private final int FAST_PATROL_RADIUS = 200;
    private final int DELAY = 5;
    private final int EXPLOSION_ANIMATION_DURATION = 1500;
    private final int BOMB_DURATION = 2000;
    private final int BONUS_TIME = 22;
    private final int BASE_LAMP_NUM = 7;
    private final int BASE_LAMP_RANGE = 50;
    private final int MAX_GAME_LEN = 60000;
    private int gameLength = MAX_GAME_LEN;
    private long startTime;
    private Timer timer;
    private ArrayList<Pacman> players = new ArrayList<Pacman>();
    private ArrayList<Patrol> patrols = new ArrayList<Patrol>();
    private ArrayList<Image> images = new ArrayList<Image>();
    private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
    private ArrayList<Bonus> bonuses = new ArrayList<Bonus>();
    private FileManager fileManager = new FileManager();
    private Lamp[] lamps;
    private int[][] table;
    private int result = -1;
    private Console console;
    private BoardUI ui;
    private int unitWidth;
    private int unitHeight;
    private Image BombImage;
    private Image BonusImage;
    private Image AIImage;
    private Image AI2Image;
    private Image PlayerRightImage;
    private Image PlayerLeftImage;
    private Image PlayerUpImage;
    private Image PlayerDownImage;
    private Image LifeImage;
    private Image ScoreImage;
    private Image SpeedImage;
    private Boolean isPaused = false;
    private JPanel containerPanel;
    private boolean isEnded = false;
    private long lastBonusTime = System.currentTimeMillis();
    private int level;
    //Initializers
    
    public JPanel getGamePanel(){
        return  ui;
    }
    public Board(int numberOfPlayers, int level, Console con ){
        /*
        try{
        //fileManager.getScores();
        }catch(Exception e){
            e.printStackTrace();
        }*/
        initBoard(numberOfPlayers, level, con, false);
    }
    
    protected ArrayList<Pacman> getPlayers(){
        return players;
    }
    
    private void initBoard(int numberOfPlayers, int level, Console root, boolean isRestart){
    	setSize(new Dimension(1368,768));
        this.level = level;
        console = root;
        containerPanel = root.getPanel();
        numP = numberOfPlayers;
        result = -1;
        gameLength = MAX_GAME_LEN* level;
        startTime = System.currentTimeMillis();
        table = fileManager.getMaze(numberOfPlayers, level);
        initImages();
        unitWidth = WIDTH / table[0].length;
        unitHeight = HEIGHT / table.length;
        patrols = initPatrols();
        players = initPlayers();
        lamps = initLamps();
        timer = new Timer(DELAY, this);
        timer.start();
        if(!isRestart){
            ui = new BoardUI(this);
            containerPanel.removeAll();
            containerPanel.add(ui);
            containerPanel.revalidate();
        }
        ui.initSounds(fileManager.getMusic());
        if(level == 3)
        	ui.shadowOn();
    }
    
    protected Lamp[] getLamps(){
        return lamps;
    }
    
    private Lamp[] initLamps(){
        int numLamps = (int) (BASE_LAMP_NUM * (1 + Math.random()) * level);
        int range = (int) (BASE_LAMP_RANGE * (1 + Math.random()));

        Lamp[] lamps = new Lamp[numLamps];
        for(int i = 0; i < numLamps; i++){
            int[] loc = getFreeIndex();
            lamps[i] = new Lamp(loc[1] * unitWidth, loc[0] * unitHeight, null, 0, 0 , range);
        }
        return lamps;
    }
    
    private ArrayList<Pacman> initPlayers(){
        int[] locations = new int[8];
        for(int i = 0 ; i < table.length; i++)
            for(int j = 0; j < table[0].length; j++){
                switch(table[i][j]){
                    case 2:
                        locations[0] = j * unitWidth;
                        locations[1] = i * unitHeight;
                        break;
                    case 3:
                        locations[2] = j * unitWidth;
                        locations[3] = i * unitHeight;
                        break;
                    case -2:
                        locations[4] = j * unitWidth;
                        locations[5] = i * unitHeight;
                        break;
                    case -3:
                        locations[6] = j * unitWidth;
                        locations[7] = i * unitHeight;
                        break;        
                }
            }
        ArrayList<Pacman> players = new ArrayList(2);
        players.add(new Pacman(locations[0], locations[1], PLAYER_SPEED, PlayerRightImage,  1, unitWidth / 2, unitHeight / 2 ));
        players.get(0).setFinishPoints(locations[2], locations[3]);
        if(numP == 2){
            players.add(new Pacman(locations[4], locations[5], PLAYER_SPEED, PlayerLeftImage,  2, unitWidth / 2 , unitHeight / 2 ));
            players.get(0).setFinishPoints(locations[6], locations[7]);
        }
        return players;
    }
    private ArrayList<Patrol> initPatrols(){
        patrols = new ArrayList(NUMBER_OF_SLOW_PATROLS + NUMBER_OF_FAST_PATROLS);
        for(int i = 0; i < NUMBER_OF_SLOW_PATROLS; i++){
            int[] loc = getFreeIndex();
            patrols.add(new Patrol(loc[1] * unitWidth, loc[0] * unitHeight, PATROL_SPEED, AIImage, false, unitWidth , unitHeight));
        }
        for(int i = 0; i < NUMBER_OF_FAST_PATROLS; i++){
            int[] loc = getFreeIndex();
            patrols.add(new Patrol(loc[1] * unitWidth, loc[0] * unitHeight, PATROL_SPEED, AIImage, true, unitWidth , unitHeight));
        }
        return patrols;
    }
    
    private int[] getFreeIndex(){
        int randomY = (int)(Math.random() * table.length);
        int randomX = (int)(Math.random() * table[0].length);
        int[] result = {randomY, randomX};
        if(table[randomY][randomX] == 0){
            return result;
        }
        return getFreeIndex();
    }
    
    private void initImages(){
        ArrayList<Image> imageList = fileManager.getImages();
        BombImage = imageList.get(0);
        BonusImage = imageList.get(1);
        AIImage = imageList.get(2);
        AI2Image = imageList.get(3);
        PlayerRightImage = imageList.get(4);
        PlayerLeftImage = imageList.get(5);
        PlayerUpImage = imageList.get(6);
        PlayerDownImage = imageList.get(7);
        LifeImage =imageList.get(8);
        ScoreImage = imageList.get(9);
        SpeedImage = imageList.get(10);
        BonusImage = imageList.get(11);
    }
    
    public void keyPressed(KeyEvent e){
    	ui.keyPressed(e);
    }
    
    public void keyReleased(KeyEvent e){
    	ui.keyReleased(e);
    }
    
    //Continuous Update Part
    public void actionPerformed(ActionEvent e) {
        try{
            
            if(!isEnded && !isPaused){
                updatePlayer();
                
                updateBombs();
                updatePatrols();
                
                updateResults();
                
                updateBonuses();
            }
            ui.repaint();
            containerPanel.repaint();
        }catch(Exception ex){
        }
    }
   
    //Set Get
    public int[][] getTable(){
        return table;
    }
    public ArrayList<GameObject> getObjects(){
        
        ArrayList<GameObject> objects = new ArrayList<GameObject>();
        for(int i = 0; i < players.size(); i++){
            objects.add(players.get(i));
        }
        for(int i = 0; i < patrols.size(); i++){
            objects.add(patrols.get(i));
        }
        for(int i = 0; i < bombs.size(); i++){
            objects.add(bombs.get(i));
        }
        for(int i = 0; i < bonuses.size(); i++){
            objects.add(bonuses.get(i));
        }
        return objects;
    }
    
    public int getTime(){
        return (int) ((startTime + gameLength) - System.currentTimeMillis());
    }
    public ArrayList<Integer> getScores(){
        ArrayList list = new ArrayList();
        for(int i = 0; i < numP; i++)
            list.add(players.get(i).getScore());
        return list;
    }
    public ArrayList<Integer> getLives(){
        ArrayList list = new ArrayList();
        for(int i = 0; i < numP; i++)
            list.add(players.get(i).getLives());
        return list;
    }
    
    
    //Controllers

    private boolean moveCollide(MovingObject obj, int direction){
        
        int plannedX = obj.getX() ;
        int plannedY = obj.getY() ;

        switch(direction){
            case 1:
                plannedX += obj.getSpeed() ;
                break;
            case 2:
                plannedY += obj.getSpeed() ;
                break;
            case 3:
                plannedX -= obj.getSpeed() ;
                break;
            case 4:
                plannedY -= obj.getSpeed();
                break;
        }
        if(plannedX < 0 || plannedX > WIDTH || plannedY < 0 || plannedY > HEIGHT){
            
            return false;
        }
        int rtx = plannedX + obj.getWidth();
        int rty = plannedY;
        int lbx = plannedX;
        int lby = plannedY + obj.getHeight();
        int rbx = plannedX + obj.getWidth();
        int rby = plannedY + obj.getHeight();
        rby--;
        lby--;
        rbx--;
        rtx--;
        try{
        int check1X = 0, check2X = 0, check1Y = 0, check2Y = 0;
        switch(direction){
            case 1:
                check1X = rtx / unitWidth;
                check2X = rbx / unitWidth;
                check1Y = rty / unitHeight;
                check2Y = rby / unitHeight;
                break;
            case 2:
                check1X = lbx / unitWidth;
                check2X = rbx / unitWidth;
                check1Y = lby / unitHeight;
                check2Y = rby / unitHeight;
                break;
            case 3:
                check1X = plannedX / unitWidth;
                check2X = lbx / unitWidth;
                check1Y = plannedY / unitHeight;
                check2Y = lby / unitHeight;
                break;
            case 4:
                check1X = rtx / unitWidth;
                check2X = plannedX / unitWidth;
                check1Y = rty / unitHeight;
                check2Y = plannedY / unitHeight;
                break;    
        }
        if(obj.getClass().getSimpleName().equals("Patrol"))
            {
                ArrayList<Integer> list = new ArrayList();
                list.add(-2);
                list.add(2);
                list.add(1);
                if(list.contains(table[check1Y][check1X]) || list.contains(table[check2Y][check2X]))
                    return false;
            }
        else if(table[check1Y][check1X] == 1 || table[check2Y][check2X] == 1){
            
            return false;
        }
        }catch(Exception e){
            return false;
        }
        return true;
    }
    private void out(Object s){
        System.out.println(s.toString());
    }
    private void placeBomb(int pacmanIndex, boolean ownSide){
        Bomb bombToAdd;
        if(ownSide){
            bombToAdd = new Bomb(players.get(pacmanIndex).getX(), players.get(pacmanIndex).getY(),System.currentTimeMillis(), BombImage, BOMB_DURATION, unitWidth, unitHeight, players.get(pacmanIndex));
            if(players.get(pacmanIndex).bomb())
                    bombs.add(bombToAdd);
        }
        else{
            bombToAdd = new Bomb(players.get((pacmanIndex - 1) * (pacmanIndex - 1)).getX(),players.get((pacmanIndex - 1) * (pacmanIndex - 1)).getY(), System.currentTimeMillis(), BombImage, BOMB_DURATION, unitWidth, unitHeight, players.get(pacmanIndex));
            if(players.get((pacmanIndex - 1) * (pacmanIndex - 1)).bombNearEnemy()){
                bombs.add(bombToAdd);
            }
        }
    }
    private boolean patrolCollasion(Pacman player, Patrol patrol){
        return Math.abs((player.getX() + player.getWidth() / 2) - (patrol.getX() + patrol.getWidth() / 2)) <  (player.getWidth() / 2) + (patrol.getWidth() / 2) && 
               Math.abs((player.getY() + player.getHeight() / 2) - (patrol.getY() + patrol.getHeight() / 2)) <  (player.getHeight() / 2) + (patrol.getHeight() / 2);
    }    
    private void bombCollasion(Bomb bomb){
    	ArrayList<MovingObject> mortalObjects = new ArrayList();
        for(int i = 0; i < players.size(); i++)
            mortalObjects.add(players.get(i));
        for(int i = 0; i < patrols.size(); i++)
            mortalObjects.add(patrols.get(i));
        for(int i = 0; i < mortalObjects.size(); i++){
            MovingObject obj = mortalObjects.get(i);
            boolean objCollided = false;
            int objXLoc = obj.getX() / unitWidth;
            int objYLoc = obj.getY() / unitHeight;
            int bombXLoc = bomb.getX() / unitWidth;
            int bombYLoc = bomb.getY() / unitHeight;
            for(int x = 1; x < bomb.getRange(); x++)
                if(table[bombYLoc][x + bombXLoc] == 1)
                    break;
                else{
                    if(x + bombXLoc == objXLoc && bombYLoc == objYLoc){
                        if(obj.getClass().getSimpleName().equals("Pacman")){
                            if(bomb.getOwner().getPlayerNumber() != ((Pacman) obj).getPlayerNumber())
                                bomb.getOwner().increaseScore(400);
                        }    
                        else
                            bomb.getOwner().increaseScore(200);
                        objCollided = true;
                    }
                    
                }
            for(int x = 1; x < bomb.getRange(); x--)
                if(table[bombYLoc][x + bombXLoc] == 1)
                    break;
                else{
                    if(x + bombXLoc == objXLoc && bombYLoc == objYLoc){
                        if(obj.getClass().getSimpleName().equals("Pacman")){
                            if(bomb.getOwner().getPlayerNumber() != ((Pacman) obj).getPlayerNumber())
                                bomb.getOwner().increaseScore(400);
                        }    
                        else
                            bomb.getOwner().increaseScore(200);
                        objCollided = true;
                    }
                    
                }
            for(int y = 1; y < bomb.getRange(); y++)
                if(table[bombYLoc + y][ bombXLoc] == 1)
                    break;
                else{
                    if(y + bombYLoc == objYLoc && bombXLoc == objXLoc){
                        if(obj.getClass().getSimpleName().equals("Pacman")){
                            if(bomb.getOwner().getPlayerNumber() != ((Pacman) obj).getPlayerNumber())
                                bomb.getOwner().increaseScore(400);
                        }    
                        else
                            bomb.getOwner().increaseScore(200);
                        objCollided = true;
                    }
                    
                }
            for(int y = 1; y < bomb.getRange(); y--)
                if(table[bombYLoc + y][ bombXLoc] == 1)
                    break;
                else{
                    if(y + bombYLoc == objYLoc && bombXLoc == objXLoc){
                        if(obj.getClass().getSimpleName().equals("Pacman")){
                            if(bomb.getOwner().getPlayerNumber() != ((Pacman) obj).getPlayerNumber())
                                bomb.getOwner().increaseScore(400);
                        }
                        else
                            bomb.getOwner().increaseScore(200);
                        objCollided = true;
                    }
                    
                }
            if(bombYLoc == objYLoc && bombXLoc == objXLoc){
                if(obj.getClass().getSimpleName().equals("Pacman")){
                    if(bomb.getOwner().getPlayerNumber() != ((Pacman) obj).getPlayerNumber())
                                bomb.getOwner().increaseScore(400);
                }
                else
                    bomb.getOwner().increaseScore(200);
                objCollided = true;
            }
            if(objCollided)
                obj.die();
        }
    }
    //Updaters
    private void updateBombs() {
        for(int i = 0; i < bombs.size(); i++){
            Bomb bomb = bombs.get(i);
            
            if(System.currentTimeMillis() > bomb.getPlantTime()+ BOMB_DURATION + EXPLOSION_ANIMATION_DURATION){
                bombs.remove(i);
                i--;
            }
            else if(bomb.isAvailableToExplosion(System.currentTimeMillis()) && !bomb.isExploded){
                bomb.explode();
                bombCollasion(bomb);       
            }

        }
    }
    private void updatePatrols() {
        for(int i = 0; i < patrols.size(); i++){
            Patrol currentPatrol = patrols.get(i);
            if(!currentPatrol.isAlive()){
                patrols.remove(i);
                i--;
            }
            else{
                ArrayList<Integer> list = new ArrayList();
                for(int p = 1; p <= 4; p++){
                    if(moveCollide(currentPatrol, p))
                        list.add(p);
                }
                currentPatrol.initRoutes(list);
                currentPatrol.move();
                if(currentPatrol.isFollowPatrol() ){
                    
                    boolean inRange = false;
                    if(currentPatrol.isInRange(players.get(0), FAST_PATROL_RADIUS)){
                        
                        if(!currentPatrol.isFast())
                            currentPatrol.setSpeed(PATROL_SPEED * 2);
                        inRange = true;
                        currentPatrol.image = AI2Image;
                    }
                    if(numP == 2)
                        if(currentPatrol.isInRange(players.get(1), FAST_PATROL_RADIUS)){
                            if(!currentPatrol.isFast())
                                currentPatrol.setSpeed(PATROL_SPEED * 2);
                            inRange = true;
                            currentPatrol.image = AI2Image;
                        }
                    if(!inRange){
                        currentPatrol.returnInitialSpeed();
                        currentPatrol.image = AIImage;
                    }
                }
                if(patrolCollasion(players.get(0), currentPatrol))
                    players.get(0).die();
                if(numP == 2)
                    if(patrolCollasion(players.get(1), currentPatrol))
                        players.get(1).die();
            }
        }
    }   
    private void updatePlayer() {
        for(Pacman player : players){
            if(((player.getFinishX() - player.getX())/unitWidth) == 0 && ((player.getFinishY() - player.getY())/unitHeight) == 0 )
                endGame(true, player);
            else if(!player.isAlive())
                endGame(false, player);
        }
    }    
    private void updateBonuses(){
    	
        if(System.currentTimeMillis() > lastBonusTime + 22*1000){
            int indicator = (int)(Math.random() * 3);
            int randX = (int)(Math.random()* table[0].length)* unitWidth ;
            int randY = (int)(Math.random()* table.length) * unitHeight;
            while(table[randY / unitHeight][randX / unitWidth] == 1){
            	randX = (int)(Math.random()* table[0].length)* unitWidth ;
            	randY = (int)(Math.random()* table.length) * unitHeight;
            }
            switch(indicator){
                case 0:
                    bonuses.add(new SpeedBonus(randX, randY, BonusImage, 2, unitWidth, unitHeight));
                    lastBonusTime = System.currentTimeMillis();
                    break;
                case 1:
                    bonuses.add(new ScoreBonus(randX, randY, BonusImage, 500, unitWidth, unitHeight));
                    lastBonusTime = System.currentTimeMillis();
                    break;
                case 2:
                    bonuses.add(new LifeBonus(randX, randY, BonusImage, 1, unitWidth, unitHeight));
                    lastBonusTime = System.currentTimeMillis();
                    break;
            }       
        }
        for(int i = 0; i < players.size(); i++){
            Pacman player = players.get(i);
            for(int j = 0; j < bonuses.size(); j++){
                Bonus bonus = bonuses.get(j);
                int playerXLoc = player.getX() / unitWidth;
                int playerYLoc = player.getY() / unitHeight;
                int bonusXLoc = bonus.getX() / unitWidth;
                int bonusYLoc = bonus.getY() / unitHeight;
                if(System.currentTimeMillis() < lastBonusTime + 8*1000){
                	if(playerXLoc == bonusXLoc && playerYLoc == bonusYLoc){
                        bonus.bonus(player);
                        bonuses.remove(j);
                        j--;
                    }
                }
                else{
                	
                	bonuses.remove(j);                	
                	j--;
                }
            }
            if(System.currentTimeMillis() > lastBonusTime + 10*1000){
            	int currentSpeed = player.getSpeed();
            	if(currentSpeed > PLAYER_SPEED)
            		player.setSpeed(PLAYER_SPEED);
            }
        }
    }
    private void updateResults(){
        if(System.currentTimeMillis() > startTime + gameLength){
            
            endGame(false, null);
        }
        else{
            for(int i = 0; i < players.size(); i++){
                
               // out(players.get(0).isDead() + "-" + players.get(1).isDead());
                if(table[players.get(0).getY() / unitHeight][players.get(0).getX() / unitWidth] == 3)
                    endGame(true,players.get(0));
                
                if(numP == 2){
                    if(table[players.get(1).getY() / unitHeight][players.get(1).getX() / unitWidth] == -3)
                        endGame(true,players.get(1));
                }
                
                if(players.get(0).isDead()){
                    if(numP == 2)
                        endGame(true, players.get(1));
                    endGame(false, null);
                }
             
                if(numP == 2)
                    if(players.get(1).isDead()){
                        endGame(true, players.get(0));
                }
            }    
        }
    }
    
    private String nameToSave = "";
    //End Game
    public void endGame(boolean successfulFinish, Pacman player){
        try{
            this.pause(true);
            if(!successfulFinish){
                 // create a jframe
            JFrame frame = new JFrame();

            // show a joptionpane dialog using showMessageDialog
            JOptionPane.showMessageDialog(frame,
                "You SUCK at this game","GIT GUD",JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("src\\img\\mid.png"));
            gotoMain();
            }
          
            
            else{
                if(numP == 1){
                	if(player.getScore() > fileManager.getScores().get(4) && sc == null){
                        sc = new SaveScore(this);
                        sc.run();
                    }
                }
                else{
                	JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,
                    "Player " + player.getPlayerNumber() + " won!","You won!",JOptionPane.INFORMATION_MESSAGE);
                    gotoMain();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    SaveScore sc = null;
    
    private void saveScore(){
    	boolean saved = false;
    	Pacman player = players.get(0);
        ArrayList<Integer> scores = fileManager.getScores();
        ArrayList<String> names = fileManager.getNames();
        out("IN");
        out(scores);
        out(names);
        
            names.remove(4);
            scores.remove(4);

            if(player.getScore() > fileManager.getScores().get(3))
                if(player.getScore() > fileManager.getScores().get(2))
                    if(player.getScore() > fileManager.getScores().get(1))
                        if(player.getScore() > fileManager.getScores().get(0))
                        {
                            names.add(0,nameToSave);
                            scores.add(0,player.getScore());
                            saved = true;
                        }
                        else{
                            names.add(1,nameToSave);
                            scores.add(1,player.getScore());
                            saved = true;

                        }
                    else{
                        names.add(2,nameToSave);
                            scores.add(2,player.getScore());
                        saved = true; 

                    }
                else{
                    names.add(3,nameToSave);
                    scores.add(3,player.getScore());
                    saved = true;

                }
            else{
                names.add(4,nameToSave);
                scores.add(4,player.getScore());
                saved = true;

            }

        
        if(saved){
            if(!nameToSave.equals("")){
                out(scores);
                out(names);
                fileManager.changeScores(scores);
                fileManager.changeNames(names);
                sc = null;
               
            }
        }
        gotoMain();
    }
    
    public void keyPressed(int[] key){
        for(int k = 0; k < key.length; k++)
            if(key[k] != -1){
                keyPressed(key[k]);
            }
    }
    
    public void pause(boolean stat){
        if(stat)
            timer.stop();
        else
            timer.start();
    }
    
    public void restart(){
        initBoard(numP, level, console, true);
    }
    //Key Evaluation
    public void keyPressed(int key){
       
        //private int[][] keys = {{81,69,65,68,87,83},{79,80,37,39,38,40}};

    
    //controls for player1 w a s d, q - place bomb, e - place bomb near other player;
    //controls for player2 UP LEFT DOWN RIGHT, O - place bomb, P - place bomb near other player;
        int keyStatus = determineKeyUser(key);
        Pacman pacman = null;
        boolean moveSuccess = false;
        int movementDirection = 0;
        switch(keyStatus){
            case 1:
                pacman = players.get(0);
                if(!isPaused)
                    switch(key){
                        case 81:
                            placeBomb(0, true);
                            break;
                        case 69:
                            if(numP == 2)
                                placeBomb(0, false);
                            break;
                        case 65:
                            moveSuccess = moveCollide(pacman, 3);
                            movementDirection = 3;
                            pacman.setImage(PlayerLeftImage);
                            break;
                        case 68:
                            moveSuccess = moveCollide(pacman, 1);
                            movementDirection = 1;
                            pacman.setImage(PlayerRightImage);
                            break;
                        case 87:
                            moveSuccess = moveCollide(pacman, 4);
                            movementDirection = 4;
                            pacman.setImage(PlayerUpImage);
                            break;
                        case 83:
                            moveSuccess = moveCollide(pacman, 2);
                            movementDirection = 2;
                            pacman.setImage(PlayerDownImage);
                            break;
                    }
                break;
            case 2:
                if(numP == 2 && !isPaused){
                    pacman = players.get(1);
                    switch(key){
                        case 79:
                            placeBomb(1, true);
                            break;
                        case 80:
                            placeBomb(1, false);
                            break;
                        case 37:
                            moveSuccess = moveCollide(pacman, 3);
                            movementDirection = 3;
                            pacman.setImage(PlayerLeftImage);
                            break;
                        case 39:
                            moveSuccess = moveCollide(pacman, 1);
                            movementDirection = 1;
                            pacman.setImage(PlayerRightImage);
                            break;
                        case 38:
                            moveSuccess = moveCollide(pacman, 4);
                            movementDirection = 4;
                            pacman.setImage(PlayerUpImage);
                            break;
                        case 40:                           
                            moveSuccess = moveCollide(pacman, 2);
                            movementDirection = 2;
                            pacman.setImage(PlayerDownImage);
                            break;
                    }
                }
                break;
            case 0:
                switch(key){
                    case 32:
                        isPaused = !isPaused;
                        break;
                    case 82:
                        //console.setScreen(1);
                        break;
                    case 27:
                        //console.setScreen(-1);
                        break;
                }
                break;            
        }
        if(moveSuccess)
            pacman.move(movementDirection);
        
    }
    public void keyReleased(int key){
        int keyStatus = determineKeyUser(key);
        Pacman pacman;
        switch(keyStatus){
            case 1:
                pacman = players.get(0);
                if(key == 65 && key == 68 && key == 87 && key == 83 )
                    pacman.stop();
                break;
            case 2:
                if(numP == 2){
                    pacman = players.get(1);
                    if(key == 37 && key == 38 && key == 39 && key == 40 )
                        pacman.stop();
                }
                break;
        }
    }
    public void gotoMain(){
    	this.pause(true);

    	console.selectOpt(0, -1);
    }
    public int determineKeyUser(int key){
        int[] p1Keys = {81,69,65,68,87,83};
        for(int i : p1Keys)
            if(i == key)
                return 1;
       
        int[] functionKeys = {13, 27};
        for(int i : functionKeys)
            if(i == key)
                return 0;
        
        int[] p2Keys = {79,80,37,39,38,40};
        for(int i : p2Keys)
            if(i == key)
                return 2;
        
        return -1;
    }
    
    
    class SaveScore extends javax.swing.JFrame {

        /**
         * Creates new form SaveScore
         */
            Board board;
        public SaveScore(Board b) {
            board = b;
            initComponents();
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {
        	//setVisible(true);
            prompt = new javax.swing.JLabel();
            OKButton = new javax.swing.JButton();
            jScrollPane1 = new javax.swing.JScrollPane();
            textArea = new javax.swing.JTextArea();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

            prompt.setText("Please Enter Your Name");

            OKButton.setText("OK");
            OKButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if(!textArea.getText().equals("")){
                        nameToSave = textArea.getText();
                        board.pause(false);
                        dispose();
                        board.saveScore();
                        sc = null;
                    }
                }
            });

            textArea.setColumns(20);
            textArea.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
            textArea.setRows(5);
            textArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
            jScrollPane1.setViewportView(textArea);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(OKButton)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(prompt, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(72, 72, 72))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(prompt)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(OKButton)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }// </editor-fold>                                                 
        
        public void run() {
        	try {
        	board.pause(true);
            //new SaveScore(board);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
        
        /**
         * @param args the command line arguments
         */
        

        // Variables declaration - do not modify                     
        private javax.swing.JButton OKButton;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JLabel prompt;
        private javax.swing.JTextArea textArea;
        // End of variables declaration                   
    }
}
