package mazerunner;

import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class Leaderboard extends JPanel
{
	// Properties
	ArrayList<String> names;
	ArrayList<Integer> scores;
	Image background;
	FileManager fm;
	JButton backToMenuButton;
	Console console;
	
	// Constructor
	Leaderboard( Console cn )
	{
		console = cn;
		this.names = FileManager.getNames();
		this.scores = FileManager.getScores();
		//background = fm.getLeaderboardBack();
		this.setPreferredSize(new Dimension(1366,768));
		//this.drawImage(background, 0, 0, null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblLeaderboard = new JLabel("Leaderboard");
		lblLeaderboard.setFont(new Font("Tahoma", Font.PLAIN, 23));
		GridBagConstraints gbc_lblLeaderboard = new GridBagConstraints();
		gbc_lblLeaderboard.insets = new Insets(0, 0, 5, 5);
		gbc_lblLeaderboard.gridx = 20;
		gbc_lblLeaderboard.gridy = 2;
		add(lblLeaderboard, gbc_lblLeaderboard);
		
		JLabel nameLabel1 = new JLabel(names.get(0));
		nameLabel1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_nameLabel1 = new GridBagConstraints();
		gbc_nameLabel1.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel1.gridx = 12;
		gbc_nameLabel1.gridy = 5;
		add(nameLabel1, gbc_nameLabel1);
		
		JLabel scoreLabel1 = new JLabel(scores.get(0)+"");
		scoreLabel1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_scoreLabel1 = new GridBagConstraints();
		gbc_scoreLabel1.insets = new Insets(0, 0, 5, 0);
		gbc_scoreLabel1.gridx = 28;
		gbc_scoreLabel1.gridy = 5;
		add(scoreLabel1, gbc_scoreLabel1);
		
		JLabel nameLabel2 = new JLabel(names.get(1));
		nameLabel2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_nameLabel2 = new GridBagConstraints();
		gbc_nameLabel2.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel2.gridx = 12;
		gbc_nameLabel2.gridy = 7;
		add(nameLabel2, gbc_nameLabel2);
		
		JLabel scoreLabel2 = new JLabel(scores.get(1)+"");
		scoreLabel2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_scoreLabel2 = new GridBagConstraints();
		gbc_scoreLabel2.insets = new Insets(0, 0, 5, 0);
		gbc_scoreLabel2.gridx = 28;
		gbc_scoreLabel2.gridy = 7;
		add(scoreLabel2, gbc_scoreLabel2);
		
		JLabel nameLabel3 = new JLabel(names.get(2));
		nameLabel3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_nameLabel3 = new GridBagConstraints();
		gbc_nameLabel3.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel3.gridx = 12;
		gbc_nameLabel3.gridy = 9;
		add(nameLabel3, gbc_nameLabel3);
		
		JLabel scoreLabel3 = new JLabel(scores.get(2)+"");
		scoreLabel3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_scoreLabel3 = new GridBagConstraints();
		gbc_scoreLabel3.insets = new Insets(0, 0, 5, 0);
		gbc_scoreLabel3.gridx = 28;
		gbc_scoreLabel3.gridy = 9;
		add(scoreLabel3, gbc_scoreLabel3);
		
		JLabel nameLabel4 = new JLabel(names.get(3));
		nameLabel4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_nameLabel4 = new GridBagConstraints();
		gbc_nameLabel4.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel4.gridx = 12;
		gbc_nameLabel4.gridy = 11;
		add(nameLabel4, gbc_nameLabel4);
		
		JLabel scoreLabel4 = new JLabel(scores.get(3)+"");
		scoreLabel4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_scoreLabel4 = new GridBagConstraints();
		gbc_scoreLabel4.insets = new Insets(0, 0, 5, 0);
		gbc_scoreLabel4.gridx = 28;
		gbc_scoreLabel4.gridy = 11;
		add(scoreLabel4, gbc_scoreLabel4);
		
		JLabel nameLabel5 = new JLabel(names.get(4));
		nameLabel5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_nameLabel5 = new GridBagConstraints();
		gbc_nameLabel5.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel5.gridx = 12;
		gbc_nameLabel5.gridy = 13;
		add(nameLabel5, gbc_nameLabel5);
		
		JLabel scoreLabel5 = new JLabel(scores.get(4)+"");
		scoreLabel5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_scoreLabel5 = new GridBagConstraints();
		gbc_scoreLabel5.insets = new Insets(0, 0, 5, 0);
		gbc_scoreLabel5.gridx = 28;
		gbc_scoreLabel5.gridy = 13;
		add(scoreLabel5, gbc_scoreLabel5);
		
		backToMenuButton = new JButton("Back to Main Menu");
		GridBagConstraints gbc_backToMenuButton = new GridBagConstraints();
		gbc_backToMenuButton.insets = new Insets(0, 0, 0, 5);
		gbc_backToMenuButton.gridx = 2;
		gbc_backToMenuButton.gridy = 24;
		add(backToMenuButton, gbc_backToMenuButton);
	}
	
	// Methods
	public class MyActionListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			if ( (JButton) e.getSource() == backToMenuButton )
			{
				console.selectOpt(0,-1);
			}
		}
	}
}
