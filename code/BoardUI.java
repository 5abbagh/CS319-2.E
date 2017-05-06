/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




package mazerunner;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.*;
//import sun.audio.AudioData;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
//import sun.audio.ContinuousAudioDataStream;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class BoardUI extends JPanel 
{
	// Constants
    public  final int SCORE_PANEL_WIDTH = 1368;
    public  final int SCORE_PANEL_HEIGHT = 200;
    public  final int BOARD_PANEL_WIDTH = 1368;
    public  final int BOARD_PANEL_HEIGHT = 768;
    private final int DELAY = 10;
    
    
    // Properties
    private JPanel scoresAndTime;
    private JPanel boardPanel;
    private JLabel [] scores;
    private JLabel [] lives;
    private JLabel time;
    private ArrayList<GameObject> objects;
    private int[][] table;
    private int tableWidth;
    private int tableHeight;
    private int unitHeight;
    private int unitWidth;
    private AudioInputStream patrolSound;
    private MyKeysAdapter keys = new MyKeysAdapter();
    private Board board;
    
    // Constructor
    public BoardUI( Board board ) 
    {
    	scoresAndTime = new JPanel();
    	scoresAndTime.setPreferredSize(new Dimension (SCORE_PANEL_WIDTH, SCORE_PANEL_HEIGHT));
    	boardPanel = new JPanel();
    	setPreferredSize(new Dimension(BOARD_PANEL_WIDTH, BOARD_PANEL_HEIGHT));
    	this.board = board;
    	this.table = board.getTable();
    	this.tableWidth = table[0].length;
    	this.tableHeight = table.length;
    	this.objects = board.getObjects();
        unitHeight = BOARD_PANEL_HEIGHT / tableHeight;
        unitWidth = BOARD_PANEL_WIDTH /tableWidth;
  
    	this.addKeyListener(keys);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    
    public void filter(Graphics x){
        BufferedImage img = new BufferedImage(SCORE_PANEL_WIDTH, BOARD_PANEL_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        int ovalX = 50;
        int ovalY = 70;
        float fRatio = 1.0f;
        int ovalRadius1 = 20;
        int ovalRadius2 = 20;
        int ovalRadius3 = 20;
        int ovalRadius4 = 20;
        int ovalRadius5 = 20;
        /* Draw the grey rectangle */
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, SCORE_PANEL_WIDTH, BOARD_PANEL_HEIGHT);
        Lamp[] lamps = board.getLamps();
        g.setColor(Color.RED);
        
        for(int i = 0;i < lamps.length; i++){
            Lamp p = lamps[i];
            g.fillOval(p.getX() - p.getRadius() / 2, p.getY() - p.getRadius() / 2, 2 * p.getRadius(), 2 * p.getRadius());
        }
        g.setColor(Color.BLACK);
        /* Enable Anti-Alias */
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        /* Clear the circle away */
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 1.0f));
        ArrayList<Pacman> list = board.getPlayers();
        ovalRadius1 = ((list.get(0).getWidth() + list.get(0).getHeight()) / 2) * 1;
        ovalRadius2 = (int)(((list.get(0).getWidth() + list.get(0).getHeight()) / 2) * 2);
        ovalRadius3 = (int)(((list.get(0).getWidth() + list.get(0).getHeight()) / 2) * 2.75);
        ovalRadius4 = (int)(((list.get(0).getWidth() + list.get(0).getHeight()) / 2) * 3.25);
        ovalRadius5 = (int)(((list.get(0).getWidth() + list.get(0).getHeight()) / 2) * 3.5);
        ovalRadius1 /= 2; 
        ovalRadius2 /= 2; 
        ovalRadius3 /= 2; 
        ovalRadius4 /= 2; 
        ovalRadius5 /= 2; 
        for(int i = 0; i < list.size(); i++){
            ovalX = list.get(i).getX() + list.get(i).getWidth() / 2;
            ovalY = list.get(i).getY() + list.get(i).getHeight() / 2;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
            g.fillOval(ovalX - ovalRadius5, ovalY - ovalRadius5, 2 * ovalRadius5, 2 * ovalRadius5);
            g.setComposite(AlphaComposite.SrcOver.derive(1.0f-(fRatio/5)));
            g.fillOval(ovalX - ovalRadius5, ovalY - ovalRadius5, 2 * ovalRadius5, 2 * ovalRadius5);
        }
        for(int i = 0; i < list.size(); i++){
            ovalX = list.get(i).getX() + list.get(i).getWidth() / 2;
            ovalY = list.get(i).getY() + list.get(i).getHeight() / 2;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
            g.fillOval(ovalX - ovalRadius4, ovalY - ovalRadius4, 2 * ovalRadius4, 2 * ovalRadius4);
            g.setComposite(AlphaComposite.SrcOver.derive(1.0f-(fRatio/4)));
            g.fillOval(ovalX - ovalRadius4, ovalY - ovalRadius4, 2 * ovalRadius4, 2 * ovalRadius4);
        }
        for(int i = 0; i < list.size(); i++){
            ovalX = list.get(i).getX() + list.get(i).getWidth() / 2;
            ovalY = list.get(i).getY() + list.get(i).getHeight() / 2;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
            g.fillOval(ovalX - ovalRadius3, ovalY - ovalRadius3, 2 * ovalRadius3, 2 * ovalRadius3);
            g.setComposite(AlphaComposite.SrcOver.derive(1.0f-(fRatio/3)));
            g.fillOval(ovalX - ovalRadius3, ovalY - ovalRadius3, 2 * ovalRadius3, 2 * ovalRadius3);
        }
        for(int i = 0; i < list.size(); i++){
            ovalX = list.get(i).getX() + list.get(i).getWidth() / 2;
            ovalY = list.get(i).getY() + list.get(i).getHeight() / 2;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
            g.fillOval(ovalX - ovalRadius2, ovalY - ovalRadius2, 2 * ovalRadius2, 2 * ovalRadius2);
            g.setComposite(AlphaComposite.SrcOver.derive(1.0f-(fRatio/2)));
            g.fillOval(ovalX - ovalRadius2, ovalY - ovalRadius2, 2 * ovalRadius2, 2 * ovalRadius2);
        }
        for(int i = 0; i < list.size(); i++){
            ovalX = list.get(i).getX() + list.get(i).getWidth() / 2;
            ovalY = list.get(i).getY() + list.get(i).getHeight() / 2;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
            g.fillOval(ovalX - ovalRadius1, ovalY - ovalRadius1, 2 * ovalRadius1, 2 * ovalRadius1);
            g.setComposite(AlphaComposite.SrcOver.derive(0.0f));
            g.fillOval(ovalX - ovalRadius1, ovalY - ovalRadius1, 2 * ovalRadius1, 2 * ovalRadius1);
        }
        
        for(int i = 0; i < lamps.length; i++){
            g.setColor(Color.RED);
            Lamp lamp = lamps[i];
            ovalX = lamp.getX() + lamp.getRadius() / 2;
            ovalY = lamp.getY() + lamp.getRadius() / 2;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
            g.fillOval(ovalX - lamp.getRadius(), ovalY - lamp.getRadius(), 2 * lamp.getRadius(), 2 * lamp.getRadius());
            g.setComposite(AlphaComposite.SrcOver.derive(0.35f));
            g.fillOval(ovalX - lamp.getRadius(), ovalY - lamp.getRadius(), 2 * lamp.getRadius(), 2 * lamp.getRadius());
        }
        
        ((Graphics2D)x).drawImage(img, null, 0, SCORE_PANEL_HEIGHT);
        g.dispose();
    }
    // Methods
    //@Override
    public void paintComponent(Graphics x) 
    {
        super.paintComponent(x);
        doMazeDrawing(x);       
        doObjectsDrawing(x);
        doPanelDrawing(x);
        //filter(x);   
    }
    
    public void initSounds(AudioInputStream[] list){
        
        startSong(list[0]);

        
    }
    
    public void startSong(AudioInputStream in){
        try{
        Clip clip = AudioSystem.getClip();
        clip.open(in);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        //Thread.sleep(10000); // looping as long as this thread is alive
        }catch(Exception e){
            
        }
    }
    
    private void doPanelDrawing(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, BOARD_PANEL_WIDTH, SCORE_PANEL_HEIGHT);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.setColor(Color.BLACK);
        g.drawString("" + board.getTime()/60000 + ":" + (board.getTime()/10000) % 10+ ""+(board.getTime()/1000) % 10, BOARD_PANEL_WIDTH / 2 - 30, 100);
        g.drawString("Player 1 Score: " + board.getScores().get(0), 10, SCORE_PANEL_HEIGHT - 75);
        g.drawString("Player 1 Lives: " + board.getLives().get(0), 10, SCORE_PANEL_HEIGHT - 30);
        if(board.numP == 2){
            g.drawString("Player 2 Score: " + board.getScores().get(1), BOARD_PANEL_WIDTH - 300, SCORE_PANEL_HEIGHT - 75);
            g.drawString("Player 2 Lives: " + board.getLives().get(1), BOARD_PANEL_WIDTH - 300, SCORE_PANEL_HEIGHT - 30);
        }
    }
    
    private void doObjectsDrawing(Graphics g)
    {   
        objects = board.getObjects();
    	for ( GameObject ob : objects )
    	{
    		Image obImg = ob.getImage();
    		int obWidth = ob.getWidth();
    		int obHeight = ob.getHeight();
    		int obX = ob.getX();
    		int obY = ob.getY();
    		g.drawImage(obImg, obX, obY + SCORE_PANEL_HEIGHT, obWidth, obHeight, null);
    		if ( ob instanceof Bomb )
    		{       
                        Bomb bomb = (Bomb) ob;
    			long time = System.currentTimeMillis();
    			boolean explode = bomb.isAvailableToExplosion(time);
                        
    			if (explode)
    			{
    				int range = bomb.getRange();
    				int bombX = bomb.getX() / unitWidth;
    				int bombY = (bomb.getY()) / unitHeight;
    				g.setColor(Color.RED);
                                
    				// UP
    				for ( int i = 0; i <= range; i++ )
    				{
    					if ( table[bombY - i][bombX] != 1 )
    					{
    						g.fillRect(bombX * unitWidth, (bombY-i)*unitHeight + SCORE_PANEL_HEIGHT, 
    								BOARD_PANEL_WIDTH/tableWidth, BOARD_PANEL_HEIGHT/tableHeight);
    					}
    					else
    						i = range + 1;
    				}
    				// Down
    				for ( int i = 0; i <= range; i++ )
    				{
    					if ( table[bombY + i][bombX] != 1 )
    					{
    						g.fillRect(bombX * unitWidth, (bombY+i)*unitHeight + SCORE_PANEL_HEIGHT, 
    								BOARD_PANEL_WIDTH/tableWidth, BOARD_PANEL_HEIGHT/tableHeight);
    					}
    					else
    						i = range + 1;
    				}
    				// Left
    				for ( int i = 0; i <= range; i++ )
    				{
    					if ( table[bombY][bombX - i] != 1 )
    					{
    						g.fillRect((bombX-i)*unitWidth, bombY * unitHeight + SCORE_PANEL_HEIGHT, 
    								BOARD_PANEL_WIDTH/tableWidth, BOARD_PANEL_HEIGHT/tableHeight);
    					}
    					else
    						i = range + 1;
    				}
    				// Right
    				for ( int i = 0; i <= range; i++ )
    				{
    					if ( table[bombY][bombX + i] != 1 )
    					{
    						g.fillRect((bombX+i)*unitWidth, bombY * unitHeight + SCORE_PANEL_HEIGHT, 
    								BOARD_PANEL_WIDTH/tableWidth, BOARD_PANEL_HEIGHT/tableHeight);
    					}
    					else
    						i = range + 1;
    				}
    			}
    		}
    	}
    }
    
    
    private void doMazeDrawing(Graphics g) 
    {
    	int rectWidth = BOARD_PANEL_WIDTH / tableWidth;
    	int rectHeight = BOARD_PANEL_HEIGHT / tableHeight;
    	int rectX = 0;
    	int rectY = SCORE_PANEL_HEIGHT;
    	for ( int i = 0; i < tableHeight; i++ )
    	{
    		for ( int j = 0; j < tableWidth; j++ )
    		{
    			if ( table[i][j] == 1 )
    			{
    				g.setColor(Color.BLACK);
    			}
    			if ( table[i][j] == 0 )
    			{
    				g.setColor(Color.WHITE);
    			}
                        if(table[i][j] == 2 || table[i][j] == -2)
                            g.setColor(Color.ORANGE);
                        if(table[i][j] == 3 )
                            g.setColor(Color.GREEN);
                        if(table[i][j] == -3 )
                            g.setColor(Color.CYAN);
    			g.drawRect(rectX, rectY, rectWidth, rectHeight);
				g.fillRect(rectX, rectY, rectWidth, rectHeight);
				rectX = rectX + rectWidth;
    		}
    		rectY = rectY + rectHeight;
                rectX = 0;
    	}
    }
    
    // Inner Class MyKeysAdapter
    class MyKeysAdapter extends KeyAdapter 
    {
        private boolean[] list = new boolean[100];
        @Override
        public void keyReleased(KeyEvent e) 
        {
            
        	int keyCode = e.getKeyCode();
        	if ( keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT 
        			|| keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_D 
        			|| keyCode == KeyEvent.VK_P || keyCode == KeyEvent.VK_O || keyCode == KeyEvent.VK_Q || keyCode == KeyEvent.VK_E|| keyCode == KeyEvent.VK_SPACE)
        	{
        		list[keyCode] = false;
        	}
        	if ( keyCode == KeyEvent.VK_R )
        	{       
                        board.pause(true);
        		int option = JOptionPane.showConfirmDialog( null,"Confirm Restart", "Are you sure you want to restart game?", JOptionPane.YES_NO_OPTION);
                        
        		if ( option == JOptionPane.YES_OPTION)
                            board.restart();
                        board.pause(false);
        	}
        	if ( keyCode == KeyEvent.VK_ESCAPE )
        	{
                        board.pause(true);
        		int option = JOptionPane.showConfirmDialog( null,"Confirm Exit", "Are you sure you want to exit game?", JOptionPane.YES_NO_OPTION);
               		if ( option == JOptionPane.YES_OPTION)
        			board.gotoMain();
               		board.pause(false);
        	}
            
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {
            
        	int keyCode = e.getKeyCode();
        	if ( keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT 
        			|| keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_D 
        			|| keyCode == KeyEvent.VK_Q || keyCode == KeyEvent.VK_E || keyCode == KeyEvent.VK_O || keyCode == KeyEvent.VK_P)
        	{
        		list[keyCode] = true;
        	}
                int[] keys = new int[12];
                keys[0] = -1;
                keys[1] = -1;
                keys[2] = -1;
                keys[3] = -1;
                keys[4] = -1;
                keys[5] = -1;
               
                for(int i = 0; i < list.length; i++){
                    if(list[i])
                        for(int p = 0; p < 6; p++)
                            if(keys[p] == -1){
                                keys[p] = i;
                                break;
                            }
                }
                board.keyPressed(keys);
        }
    }

    
    
}




