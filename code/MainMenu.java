package mazerunner;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Console;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MainMenu extends JPanel
{
	
	// Properties
	Console console;
	int labelSelected;
	int buttonSelected;
	int level;
	ArrayList<JLabel> labels;
	ArrayList<JButton> buttons;
	
	// Constructor
	public MainMenu()
	{
		super();
		this.setPreferredSize(new Dimension(1366,768));
		setLayout(null);
		
		labels = new ArrayList<JLabel>();
		buttons = new ArrayList<JButton>();
		
		JLabel singlePlayer = new JLabel("Single Player");
		singlePlayer.setFont(new Font("Tahoma", Font.PLAIN, 24));
		singlePlayer.setHorizontalAlignment(SwingConstants.CENTER);
		singlePlayer.setBounds(837, 211, 172, 29);
		add(singlePlayer);
		labels.add(singlePlayer);
		
		JLabel multiplayer = new JLabel("Multiplayer");
		multiplayer.setHorizontalAlignment(SwingConstants.CENTER);
		multiplayer.setFont(new Font("Tahoma", Font.PLAIN, 24));
		multiplayer.setBounds(837, 281, 172, 29);
		add(multiplayer);
		labels.add(multiplayer);
		
		JLabel diff = new JLabel("Difficulty");
		diff.setHorizontalAlignment(SwingConstants.CENTER);
		diff.setFont(new Font("Tahoma", Font.PLAIN, 24));
		diff.setBounds(603, 365, 172, 29);
		add(diff);
		labels.add(diff);
		
		JLabel lblLeaderboard = new JLabel("Leaderboard");
		lblLeaderboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeaderboard.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLeaderboard.setBounds(837, 473, 172, 29);
		add(lblLeaderboard);
		labels.add(lblLeaderboard);
		
		JLabel lblTutorials = new JLabel("Tutorials");
		lblTutorials.setHorizontalAlignment(SwingConstants.CENTER);
		lblTutorials.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTutorials.setBounds(837, 527, 172, 29);
		add(lblTutorials);
		labels.add(lblTutorials);
		
		JLabel lblCredits = new JLabel("Credits");
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredits.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCredits.setBounds(837, 576, 172, 29);
		add(lblCredits);
		labels.add(lblCredits);
		
		JLabel lblExit = new JLabel("Exit");
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblExit.setBounds(837, 622, 172, 29);
		add(lblExit);
		labels.add(lblExit);
		
		JButton btnEasy = new JButton("Easy");
		btnEasy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEasy.setBounds(800, 344, 75, 75);
		add(btnEasy);
		buttons.add(btnEasy);
		
		JButton btnNormal = new JButton("Normal");
		btnNormal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNormal.setBounds(937, 344, 97, 75);
		add(btnNormal);
		buttons.add(btnNormal);
		
		JButton btnHard = new JButton("Hard");
		btnHard.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHard.setBounds(1092, 344, 75, 75);
		add(btnHard);
		buttons.add(btnHard);
		
	}
	
	// Methods
	@Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
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
        		buttons.get(i).setBackground(Color.GREEN);
        	}
        	else
        		buttons.get(i).setBackground(new JButton().getBackground());
        }
        for ( int i = 0; i < 7; i++ )
        {
        	if (labelSelected == i )
        	{
        		labels.get(i).setForeground(Color.RED);
        	}
        	else
        		labels.get(i).setForeground(Color.BLACK);
        }
    }
	
	
	// Inner Class MyKeysAdapter
    private class MyKeysAdapter extends KeyAdapter 
    {
    	
        @Override
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
        		else
        		{
        			console.selectOption( labelSelected, level);
        		}
        	}
        	repaint();
        }
    }

    
}
