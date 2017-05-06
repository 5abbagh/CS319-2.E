package mazerunner;

import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class Leaderboard extends JPanel implements KeyListener
{
	// Properties
	ArrayList<String> names;
	ArrayList<Integer> scores;
	Image background;
	Console console;
	
	// Constructor
	Leaderboard( Console cn )
	{
		console = cn;
		background = new ImageIcon("src\\img\\" + "background" + ".jpg").getImage();
		
		this.names = FileManager.getNames();
		this.scores = FileManager.getScores();
		
		this.setPreferredSize(new Dimension(1366,768));
		setLayout(null);
		
		JLabel lblLeaderboard = new JLabel("Leaderboard");
		lblLeaderboard.setForeground(Color.WHITE);
		lblLeaderboard.setBounds(661, 62, 126, 28);
		lblLeaderboard.setFont(new Font("Tahoma", Font.PLAIN, 23));
		add(lblLeaderboard);
		
		JLabel nameLabel1 = new JLabel(names.get(0));
		nameLabel1.setForeground(Color.WHITE);
		nameLabel1.setBounds(360, 153, 85, 21);
		nameLabel1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(nameLabel1);
		
		JLabel scoreLabel1 = new JLabel(scores.get(0)+"");
		scoreLabel1.setForeground(Color.WHITE);
		scoreLabel1.setBounds(1001, 153, 108, 43);
		scoreLabel1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(scoreLabel1);
		
		JLabel nameLabel2 = new JLabel(names.get(1));
		nameLabel2.setForeground(Color.WHITE);
		nameLabel2.setBounds(360, 209, 85, 21);
		nameLabel2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(nameLabel2);
		
		JLabel scoreLabel2 = new JLabel(scores.get(1)+"");
		scoreLabel2.setForeground(Color.WHITE);
		scoreLabel2.setBounds(1001, 209, 108, 43);
		scoreLabel2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(scoreLabel2);
		
		JLabel nameLabel3 = new JLabel(names.get(2));
		nameLabel3.setForeground(Color.WHITE);
		nameLabel3.setBounds(360, 265, 85, 21);
		nameLabel3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(nameLabel3);
		
		JLabel scoreLabel3 = new JLabel(scores.get(2)+"");
		scoreLabel3.setForeground(Color.WHITE);
		scoreLabel3.setBounds(1001, 265, 108, 43);
		scoreLabel3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(scoreLabel3);
		
		JLabel nameLabel4 = new JLabel(names.get(3));
		nameLabel4.setForeground(Color.WHITE);
		nameLabel4.setBounds(360, 321, 85, 21);
		nameLabel4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(nameLabel4);
		
		JLabel scoreLabel4 = new JLabel(scores.get(3)+"");
		scoreLabel4.setForeground(Color.WHITE);
		scoreLabel4.setBounds(1001, 321, 108, 43);
		scoreLabel4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(scoreLabel4);
		
		JLabel nameLabel5 = new JLabel(names.get(4));
		nameLabel5.setForeground(Color.WHITE);
		nameLabel5.setBounds(360, 377, 85, 21);
		nameLabel5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(nameLabel5);
		
		JLabel scoreLabel5 = new JLabel(scores.get(4)+"");
		scoreLabel5.setForeground(Color.WHITE);
		scoreLabel5.setBounds(1001, 377, 108, 43);
		scoreLabel5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(scoreLabel5);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	    g.drawImage(background, 0, 0, 1366, 768, null);
	}
	
	// Methods
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyPressed( KeyEvent e )
	{
		if ( e.getKeyCode() == KeyEvent.VK_ESCAPE )
		{
			console.selectOpt(0,-1);
		}
	}
}
