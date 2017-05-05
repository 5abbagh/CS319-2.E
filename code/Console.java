package mazerunner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Console extends JFrame
{
	MainMenu mainMenu;     	// 0
	Board board;			// 1 for single		// 2 for multi
	Leaderboard lb;		// 3
	Tutorial tut;			// 4
	int level;
	String [] titles;
	JPanel [] panels;
	JPanel panel;
	public Console()
	{
		panel = new JPanel();
		mainMenu = new MainMenu( this );
		board = new Board(2,1);
		lb  = new Leaderboard( this);
		tut = new Tutorial();
		titles = new String [] {"Main Menu", "Single Player", "Multiplayer", "Leaderboard", "Tutorials"};
		panels = new JPanel [4];
		
		panels[0] = mainMenu;
		panels[1] = board;
		panels[2] = lb;
		panels[3] = tut;
		panel.add(lb);
		add(panel);
	}
	
	// Set panel
	public void selectOpt( int panel, int level)
	{
		if (panel == 0 )
		{
			this.removeAll();
			add(panels [0]);
			this.revalidate();
			setTitle(titles[0]);
		}
		else if (panel == 1 )
		{
			this.removeAll();
			board = new Board(1,level);
			add(panels [1]);
			this.revalidate();
			setTitle(titles[1]);
		}
		else if (panel == 2 )
		{
			this.removeAll();
			board = new Board(2,level);
			add(panels [1]);
			this.revalidate();
			setTitle(titles[2]);
		}
		else if (panel == 3 )
		{
			this.removeAll();
			add(panels [2]);
			this.revalidate();
			setTitle(titles[3]);
		}
		else if (panel == 4 )
		{
			this.panel.removeAll();
			this.panel.add(panels [3]);
			this.panel.revalidate();
			this.revalidate();
			setTitle(titles[4]);
			System.out.println(this.getComponentCount());
		}
		repaint();
	}
}