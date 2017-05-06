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
	Leaderboard lb;		// 3
	Tutorial tut;			// 4
	int level = 1;
	String [] titles;
	JPanel [] panels;
	int temp;
	JPanel panel;
	public Console()
	{
		
		panel = new JPanel();
		panel.addKeyListener(new PanelKeysAdapter());
		mainMenu = new MainMenu( this );
		lb  = new Leaderboard( this);
		tut = new Tutorial();
		titles = new String [] {"Main Menu", "Single Player", "Multiplayer", "Leaderboard", "Tutorials"};
		panels = new JPanel [4];
		panels[0] = mainMenu;
		panels[2] = lb;
		panels[3] = tut;

		add(panel);
                
                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                this.setVisible(true);
                
                setLocationRelativeTo(null);
                this.setSize(new Dimension(1368,768));
                this.setResizable(false);
                setVisible(true);
                selectOpt(0 , 1);
	}
	
        public JPanel getPanel(){
            return panel;
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
			board = null;
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
		else if (panel == 6 )
		{
			System.exit(0);
		}
		repaint();
		repaint();
	}
        
	class PanelKeysAdapter extends KeyAdapter 
    {
		public void keyPressed(KeyEvent e){
	    	switch(activeOp){
	    	case 0:
	    		mainMenu.keyPressed(e);
	    		break;
	    	case 1:
	    		board.keyPressed(e);
	    		break;
	    	case 2:
	    		board.keyPressed(e);
	    		break;
	    	case 3:
	    		tut.keyPressed(e);
	    		break;
	    	case 4:
	    		lb.keyPressed(e);
	    		break;
	    	}
	    }
	    
	    public void keyReleased(KeyEvent e){
	    	switch(activeOp){
	    	case 0:
	    		mainMenu.keyReleased(e);
	    		break;
	    	case 1:
	    		board.keyReleased(e);
	    		break;
	    	case 2:
	    		board.keyReleased(e);
	    		break;
	    	case 3:
	    		tut.keyReleased(e);
	    		break;
	    	case 4:
	    		lb.keyReleased(e);
	    		break;
	    	}
	    }
    }
}
