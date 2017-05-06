package mazerunner;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Tutorial extends JPanel implements KeyListener
{
	Console console;
	
	ArrayList<Image> imageList;
	int count = 0;
	JPanel imagePanel;
	JLabel img1;
	Image background;
	
	public Tutorial( Console cn) 
	{
		console = cn;
		background = new ImageIcon("src\\img\\" + "background" + ".jpg").getImage();
		imageList = FileManager.getTutorialImages();
		
		this.setPreferredSize(new Dimension(1366,768));
		this.setBounds(0,0,1366,768);
		setLayout(null);
		
		
//		imagePanel = new JPanel();
//		imagePanel.setBackground(Color.PINK);
//		imagePanel.setForeground(Color.PINK);
//		imagePanel.setBounds(405, 160, 657, 357);
//		add(imagePanel);
//		
//		
//		img1 = new JLabel("");
//		img1.setIcon(new ImageIcon(imageList.get(count)));
//		imagePanel.add(img1);

//		prevButton = new JButton("Prev");
//		prevButton.setBounds(264, 340, 97, 25);
//		add(prevButton);
//
//		nextButton = new JButton("Next");
//		nextButton.setBounds(1074, 340, 97, 25);
//		add(nextButton);
//
//		menuButton = new JButton("Go back  menu");
//		menuButton.setBounds(301, 551, 97, 25);
//		add(menuButton);
//		
//		prevButton.addActionListener(this);
//		nextButton.addActionListener(this);
//		menuButton.addActionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	    g.drawImage(background, 0, 0, 1366, 768, null);
	    Image tut = new ImageIcon(imageList.get(count)).getImage();
	    g.drawImage(tut, 350, 100, 700, 400, null);
	}

	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyPressed( KeyEvent e )
	{
		if ( e.getKeyCode() == KeyEvent.VK_RIGHT && count < imageList.size() - 1 )
		{	
			count++;
//			imagePanel.removeAll();
//			img1.setIcon(new ImageIcon(imageList.get(count)));
//			imagePanel.add(img1);
//			imagePanel.revalidate();
//			this.revalidate();
		}
		else if ( e.getKeyCode() == KeyEvent.VK_LEFT && count > 0 )
		{
			count--;
//			imagePanel.removeAll();
//			img1.setIcon(new ImageIcon(imageList.get(count)));
//			imagePanel.add(img1);
//			imagePanel.revalidate();
//			this.revalidate();
		}
		else if( e.getKeyCode() == KeyEvent.VK_ESCAPE )
		{
			count = 0;
//			imagePanel.removeAll();
//			img1.setIcon(new ImageIcon(imageList.get(count)));
//			imagePanel.add(img1);
//			imagePanel.revalidate();
			console.selectOpt(0, -1);
		}
		repaint();
	}
	
}
