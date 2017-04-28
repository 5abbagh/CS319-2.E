package mazerunner;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class BoardUI extends JPanel 
{
	// Constants
	public  final int SCORE_PANEL_WIDTH = 1366;
    public  final int SCORE_PANEL_HEIGHT = 200;
    public  final int BOARD_PANEL_WIDTH = 1366;
    public  final int BOARD_PANEL_HEIGHT = 568;
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
    private boolean firstDraw;
    private MyKeysAdapter keys;
    private Board board;
    
    // Constructor
    public BoardUI( Board board ) 
    {
    	scoresAndTime = new JPanel();
    	scoresAndTime.setPreferredSize(new Dimension (SCORE_PANEL_WIDTH, SCORE_PANEL_HEIGHT));
    	boardPanel = new JPanel();
    	boardPanel.addKeyListener(keys);
    	boardPanel.setPreferredSize(new Dimension(BOARD_PANEL_WIDTH, BOARD_PANEL_HEIGHT));
    	this.board = board;
    	this.table = board.getTable();
    	this.tableWidth = board.getTableWidth();
    	this.tableHeight = board.getTableHeight();
    	this.objects = board.getObjects();
    	firstDraw = true;
    }
    
    
    // Methods
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        if (firstDraw)
        {
        	doMazeDrawing(g);
        }
        doObjectsDrawing(g);
    }
    
    private void doObjectsDrawing(Graphics g)
    {
    	for ( GameObject ob : objects )
    	{
    		Image obImg = ob.getImage();
    		int obWidth = ob.getWidth();
    		int obHeight = ob.getHeight();
    		int obX = ob.getX();
    		int obY = ob.getY();
    		g.drawImage(obImg, obX, obY, obWidth, obHeight, null);
    		if ( ob instanceof Bomb )
    		{
    			long time = System.currentTimeMillis();
    			boolean explode = ob.isAvailableToExplosion(time);
    			if (explode)
    			{
    				int range = ob.getRange();
    				int bombX = ob.getX() / ob.getWidth();
    				int bombY = (ob.getY() - SCORE_PANEL_HEIGHT) / ob.getHeight();
    				g.setColor(Color.RED);
    				// UP
    				for ( int i = 1; i <= range; i++ )
    				{
    					if ( table[bombY - i][bombX] != 1 )
    					{
    						g.drawRect(bombX*tableWidth, (bombY-i)*tableHeight + SCORE_PANEL_HEIGHT, 
    								BOARD_PANEL_WIDTH/tableWidth, BOARD_PANEL_HEIGHT/tableHeight);
    					}
    					else
    						i = range + 1;
    				}
    				// Down
    				for ( int i = 1; i <= range; i++ )
    				{
    					if ( table[bombY + i][bombX] != 1 )
    					{
    						g.drawRect(bombX*tableWidth, (bombY+i)*tableHeight + SCORE_PANEL_HEIGHT, 
    								BOARD_PANEL_WIDTH/tableWidth, BOARD_PANEL_HEIGHT/tableHeight);
    					}
    					else
    						i = range + 1;
    				}
    				// Left
    				for ( int i = 1; i <= range; i++ )
    				{
    					if ( table[bombY][bombX - i] != 1 )
    					{
    						g.drawRect((bombX-i)*tableWidth, bombY*tableHeight + SCORE_PANEL_HEIGHT, 
    								BOARD_PANEL_WIDTH/tableWidth, BOARD_PANEL_HEIGHT/tableHeight);
    					}
    					else
    						i = range + 1;
    				}
    				// Right
    				for ( int i = 1; i <= range; i++ )
    				{
    					if ( table[bombY][bombX + i] != 1 )
    					{
    						g.drawRect((bombX+i)*tableWidth, bombY*tableHeight + SCORE_PANEL_HEIGHT, 
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
    	firstDraw = false;
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
    			g.drawRect(rectX, rectY, rectWidth, rectHeight);
				g.fillRect(rectX, rectY, rectWidth, rectHeight);
				rectX = rectX + rectWidth;
    		}
    		rectY = rectY + rectHeight;
    	}
    }
    
    // Inner Class MyKeysAdapter
    private class MyKeysAdapter extends KeyAdapter 
    {
        @Override
        public void keyReleased(KeyEvent e) 
        {
        	int keyCode = e.getKeyCode();
        	if ( keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT 
        			|| keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_D 
        			|| keyCode == KeyEvent.VK_P )
        	{
        		board.keyPressed(keyCode);
        	}
        	else if ( keyCode == KeyEvent.VK_R )
        	{
        		int option = JOptionPane.showConfirmDialog( null,"Confirm Restart", "Are you sure you want to restart game?", JOptionPane.YES_NO_OPTION);
        		if ( option == JOptionPane.YES_OPTION)
        			board.keyPressed(keyCode);
        	}
        	else if ( keyCode == KeyEvent.VK_ESCAPE )
        	{
        		int option = JOptionPane.showConfirmDialog( null,"Confirm Exit", "Are you sure you want to exit game?", JOptionPane.YES_NO_OPTION);
        		if ( option == JOptionPane.YES_OPTION)
        			board.keyPressed(keyCode);
        	}
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {
        	int keyCode = e.getKeyCode();
        	if ( keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT 
        			|| keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_D 
        			|| keyCode == KeyEvent.VK_K || keyCode == KeyEvent.VK_L || keyCode == KeyEvent.VK_G || keyCode == KeyEvent.VK_H)
        	{
        		board.keyPressed(keyCode);
        	}
        }
    }
    
}
