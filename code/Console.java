package mazerunner;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Console extends JFrame
{
	MainMenu mainMenu;     	// 0
	Board board;			// 1 for single		// 2 for multi
	//Leaderboard lb;		// 3
	//Tutorial tut;			// 4
	int level = 1;
	String [] titles;
	JPanel [] panels;
	JPanel panel;
	public Console()
	{
		
		panel = new JPanel();
		mainMenu = new MainMenu( this );
		//lb  = new Leaderboard( this);
		//tut = new Tutorial();
		titles = new String [] {"Main Menu", "Single Player", "Multiplayer", "Leaderboard", "Tutorials"};
		panels = new JPanel [4];
		
		panels[0] = mainMenu;

		//panels[2] = lb;
		//panels[3] = tut;
		//panel.add(lb);
		add(panel);
                
                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                this.setVisible(true);
                selectOpt(0 , 1);
                setLocationRelativeTo(null);
                this.setSize(new Dimension(1368,768));
                this.setResizable(false);
                
                
	}
	
        public JPanel getPanel(){
            return panel;
        }
        
	// Set panel
	public void selectOpt( int panel, int level)
	{
		if (panel == 0 )
		{
			this.panel.removeAll();
			board = null;
			this.panel.add(panels [0]);
			while(!panels[0].hasFocus())
				panels[0].requestFocusInWindow();
			this.panel.revalidate();
            this.revalidate();
			setTitle(titles[0]);
            this.pack();
            System.out.println("here");
		}
		else if (panel == 1 )
		{
			
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
			this.panel.removeAll();
			this.panel.add(panels [2]);
			while(!panels[2].hasFocus())
				panels[2].requestFocusInWindow();
			this.panel.revalidate();
            this.revalidate();
			setTitle(titles[3]);
            this.pack();
		}
		else if (panel == 4 )
		{
			this.panel.removeAll();
			this.panel.add(panels [3]);
			while(!panels[3].hasFocus())
				panels[3].requestFocusInWindow();
			this.panel.revalidate();
			this.revalidate();
			setTitle(titles[4]);
            this.pack();
		}
		repaint();
	}
        

}
