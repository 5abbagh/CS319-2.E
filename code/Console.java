package mazerunner;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Console extends JFrame
{
	MainMenu mainMenu;     	// 0
	Board board;			// 1 for single		// 2 for multi
	Leaderboard lb;			// 3
	Tutorial tut;			// 4
	Credits credits;		// 5
	
	String [] titles;
	JPanel [] panels;
	
	JPanel panel;
	int level = 1;
	
	public Console()
	{
		panel = new JPanel();
		panel.addKeyListener(new PanelKeysAdapter());
		mainMenu = new MainMenu( this );
		board = new Board(1,1,this);
		lb  = new Leaderboard( this);
		tut = new Tutorial( this );
		credits = new Credits(this);
		titles = new String [] {"Main Menu", "Single Player", "Multiplayer", "Leaderboard", "Tutorials", "Credits"};
		panels = new JPanel [5];
		panels[0] = mainMenu;
		panels[1] = board;
		panels[2] = lb;
		panels[3] = tut;
		panels[4] = credits;
		
		add(panel);
		
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setSize(new Dimension(1368,768));
        this.setResizable(false);
        setVisible(true);
        
        selectOpt(0 , -1);
	}
	
    public JPanel getPanel()
    {
    	return panel;
    }
    
    public void exit()
    {
    	System.exit(10);
    }
    
	// Set panel
    private int activeOp = 0;
	public void selectOpt( int panel, int level)
	{
		this.panel.requestFocusInWindow();
		if (panel == 0 )
		{
			activeOp = 0;
			this.panel.removeAll();
			mainMenu = new MainMenu(this);
			panels[0] = mainMenu;
			
			this.panel.add(panels[0]);
			this.panel.revalidate();
            this.revalidate();
			setTitle(titles[0]);
            this.pack();
            
		}
		else if (panel == 1 )
		{
			activeOp = 1;
			this.panel.removeAll();
            board = new Board(1,level, this);            
			this.panel.add(board);
			this.panel.revalidate();
            this.revalidate();
			setTitle(titles[1]);
            this.pack();
		}
		else if (panel == 2 )
		{
			activeOp = 2;                            
			this.panel.removeAll();
			board = new Board(2,level, this);
			this.panel.add(board);
			this.panel.revalidate();
            this.revalidate();
			setTitle(titles[2]);
            this.pack();
		}
		// Leaderboard
		else if (panel == 3 )
		{
			activeOp = 3;
			this.panel.removeAll();
			this.panel.add(panels [2]);
			this.panel.revalidate();
            this.revalidate();
			setTitle(titles[3]);
            this.pack();
		}
		// Tutorials
		else if (panel == 4 )
		{
			activeOp = 4;
			this.panel.removeAll();
			this.panel.add(panels [3]);
			this.panel.revalidate();
			this.revalidate();
			setTitle(titles[4]);
            this.pack();
		}
		// Credits
		else if (panel == 5 )
		{
			activeOp = 5;
			this.panel.removeAll();
			this.panel.add(panels [4]);
			this.panel.revalidate();
			this.revalidate();
			setTitle(titles[5]);
            this.pack();
		}
		repaint();
	}
	
	class PanelKeysAdapter extends KeyAdapter 
    {
		public void keyPressed(KeyEvent e)
		{
	    	switch(activeOp)
	    	{
		    	case 0:
		    		mainMenu.keyPressed(e);
		    		break;
		    	case 1:
		    		board.keyPressed(e);
		    		break;
		    	case 2:
		    		board.keyPressed(e);
		    		break;
		    	case 4:
		    		tut.keyPressed(e);
		    		break;
		    	case 3:
		    		lb.keyPressed(e);
		    		break;
		    	case 5:
		    		credits.keyPressed(e);
		    		break;
	    	}
	    }
	    
	    public void keyReleased(KeyEvent e)
	    {
	    	switch(activeOp)
	    	{
		    	case 0:
		    		mainMenu.keyReleased(e);
		    		break;
		    	case 1:
		    		board.keyReleased(e);
		    		break;
		    	case 2:
		    		board.keyReleased(e);
		    		break;
	    	}
	    }
    }
}
