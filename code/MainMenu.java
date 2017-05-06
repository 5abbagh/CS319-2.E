package mazerunner;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mazerunner.BoardUI.MyKeysAdapter;

import java.awt.Font;

public class MainMenu extends JPanel implements KeyListener
{
	
	// Properties
	Console console;
	int labelSelected = 0;
	int buttonSelected = 0;
	int level;
	ArrayList<JLabel> labels;
	ArrayList<JButton> buttons;
	Image background;
	
	// Constructor
	public MainMenu( Console cn)
	{
		console = cn;
		this.setPreferredSize(new Dimension(1366,768));
		level = 0;
		labels = new ArrayList<JLabel>();
		buttons = new ArrayList<JButton>();
		background = new ImageIcon("src\\img\\" + "background" + ".jpg").getImage();
		setLayout(null);
		
		JLabel singlePlayer = new JLabel("Single Player");
		singlePlayer.setForeground(Color.WHITE);
		singlePlayer.setBounds(605, 88, 172, 29);
		singlePlayer.setFont(new Font("Tahoma", Font.PLAIN, 24));
		singlePlayer.setHorizontalAlignment(SwingConstants.CENTER);
		add(singlePlayer);
		labels.add(singlePlayer);
		
		JLabel multiplayer = new JLabel("Multiplayer");
		multiplayer.setForeground(Color.WHITE);
		multiplayer.setBounds(605, 146, 172, 29);
		multiplayer.setHorizontalAlignment(SwingConstants.CENTER);
		multiplayer.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(multiplayer);
		labels.add(multiplayer);
		
		JLabel diff = new JLabel("Difficulty");
		diff.setForeground(Color.WHITE);
		diff.setBounds(334, 243, 172, 29);
		diff.setHorizontalAlignment(SwingConstants.CENTER);
		diff.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(diff);
		labels.add(diff);
		
		JLabel lblLeaderboard = new JLabel("Leaderboard");
		lblLeaderboard.setForeground(Color.WHITE);
		lblLeaderboard.setBounds(603, 345, 172, 29);
		lblLeaderboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeaderboard.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(lblLeaderboard);
		labels.add(lblLeaderboard);
		
		JLabel lblTutorials = new JLabel("Tutorials");
		lblTutorials.setForeground(Color.WHITE);
		lblTutorials.setBounds(605, 408, 172, 29);
		lblTutorials.setHorizontalAlignment(SwingConstants.CENTER);
		lblTutorials.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(lblTutorials);
		labels.add(lblTutorials);
		
		JLabel lblCredits = new JLabel("Credits");
		lblCredits.setForeground(Color.WHITE);
		lblCredits.setBounds(602, 469, 172, 29);
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredits.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(lblCredits);
		labels.add(lblCredits);
		
		JLabel lblExit = new JLabel("Exit");
		lblExit.setForeground(Color.WHITE);
		lblExit.setBounds(603, 523, 172, 29);
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(lblExit);
		labels.add(lblExit);
		
		JButton btnEasy = new JButton("Easy");
		btnEasy.setBounds(536, 222, 75, 75);
		btnEasy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(btnEasy);
		buttons.add(btnEasy);
		
		JButton btnNormal = new JButton("Normal");
		btnNormal.setBounds(645, 222, 117, 75);
		btnNormal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(btnNormal);
		buttons.add(btnNormal);
		
		JButton btnHard = new JButton("Hard");
		btnHard.setBounds(790, 222, 89, 75);
		btnHard.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(btnHard);
		buttons.add(btnHard);
		
	}
	
	// Methods
	@Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 1366, 768, null);
        for ( int i = 0; i < 3; i++ )
        {
        	if (buttonSelected == i )
        	{
        		buttons.get(i).setSelected(true);
        	}
        	else
        		buttons.get(i).setSelected(false);
        	if (level == i )
        	{
        		buttons.get(i).setBackground(Color.ORANGE);
        	}
        	else if(buttonSelected == i)
        		buttons.get(i).setBackground(Color.YELLOW);
        	else
        		buttons.get(i).setBackground(Color.GRAY);
        }
        for ( int i = 0; i < 7; i++ )
        {
        	if (labelSelected == i )
        	{
        		labels.get(i).setForeground(Color.RED);
        	}
        	else
        		labels.get(i).setForeground(Color.WHITE);
        }
    }
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
        if ( keyCode == KeyEvent.VK_UP )
       	{
       		if (labelSelected > 0)
       			labelSelected--;
       	}
       	else if ( keyCode == KeyEvent.VK_DOWN )
       	{
       		if (labelSelected < 6)
       			labelSelected++;
       	}
       	else if ( keyCode == KeyEvent.VK_LEFT )
       	{
       		if (labelSelected == 2 )
       		{
       			if (buttonSelected > 0 )
       				buttonSelected--;
       		}
       	}        	
       	else if ( keyCode == KeyEvent.VK_RIGHT )
       	{
       		if (labelSelected == 2 )
       		{
       			if (buttonSelected < 2 )
       				buttonSelected++;
       		}
       	}        	
       	else if ( keyCode == KeyEvent.VK_ENTER )
       	{
       		if ( labelSelected == 2 )
       		{
       			level = buttonSelected;
       		}
       		if ( labelSelected == 6 )
       		{
       			new JOptionPane();
				int selection = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit", "Choose one", JOptionPane.YES_NO_OPTION);
       			if ( selection == JOptionPane.YES_OPTION )
       			{
       				console.exit();
       			}
       		}
       		else
       		{
        		if ( labelSelected < 2 )
         		{
         			console.selectOpt( labelSelected + 1 , level + 1);
        		}
        		if ( labelSelected > 2 )
         		{
        			console.selectOpt( labelSelected , -1);
        		}
       		}
       	}
        repaint();
	}
}
