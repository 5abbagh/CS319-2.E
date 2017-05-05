package mazerunner;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Tutorial extends JPanel implements ActionListener
{

	Image a;
	Image b;
	JLabel img1;
	JLabel img2;
	JPanel imagePanel;
//	My k = new My();
	JButton prevButton;
	JButton nextButton;
	public Tutorial() 
	{
		a = new ImageIcon("src\\img\\" + "right" + ".png").getImage();
		b = new ImageIcon("src\\img\\" + "score" + ".png").getImage();
		this.setPreferredSize(new Dimension(1366,768));
		setLayout(null);
		
	    imagePanel = new JPanel();
		//imagePanel.setBackground(Color.MAGENTA);
		imagePanel.setBounds(405, 160, 657, 357);
		add(imagePanel);
		
		img1 = new JLabel("");
		img1.setIcon(new ImageIcon("C:\\Users\\Ali\\Eclipse\\Workspace\\MazeRunner\\src\\img\\bomb.png"));
		imagePanel.add(img1);
		img2 = new JLabel("");
		img2.setIcon(new ImageIcon("C:\\Users\\Ali\\Eclipse\\Workspace\\MazeRunner\\src\\img\\score.png"));
		
		 prevButton = new JButton("Prev");
		prevButton.setBounds(264, 340, 97, 25);
		add(prevButton);
		
		 nextButton = new JButton("Next");
		nextButton.setBounds(1074, 340, 97, 25);
		add(nextButton);
		
		JButton menuButton = new JButton("Go back  menu");
		menuButton.setBounds(301, 551, 97, 25);
		add(menuButton);
		nextButton.addActionListener(this);
		prevButton.addActionListener(this);}
	
	 // Inner Class MyKeysAdapter
    
    	public void actionPerformed( ActionEvent e )
    	{
    		System.out.println("Here");
    		if ( (JButton) e.getSource() == nextButton )
    		{
    			imagePanel.removeAll();
    			imagePanel.add(img1);
    			imagePanel.revalidate();
    		}
    		if ( (JButton) e.getSource() == prevButton )
    		{
    			imagePanel.removeAll();
    			imagePanel.add(img2);
    			imagePanel.revalidate();
    		}
    		repaint();
    	}
//        @Override
//        public void keyPressed(KeyEvent e) 
//        {
//        	System.out.println("Here");
//        	int keyCode = e.getKeyCode();
//        	if ( keyCode == KeyEvent.VK_RIGHT)
//        	{
//        		imagePanel.add(img1);
//        	}
//        	if ( keyCode == KeyEvent.VK_LEFT)
//        	{
//        		imagePanel.add(img1);
//        	}
//
//        }
   

}
