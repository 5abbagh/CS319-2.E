package mazerunner;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class Credits extends JPanel implements KeyListener
{
	Console console;
	Image background;
	
	public Credits( Console cn) 
	{
		console = cn;
		this.setPreferredSize(new Dimension(1366,768));
		setLayout(null);
		background = new ImageIcon("src\\img\\" + "background" + ".jpg").getImage();
		
		JLabel lblCredits = new JLabel("Credits");
		lblCredits.setForeground(Color.WHITE);
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredits.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCredits.setBounds(631, 129, 148, 55);
		add(lblCredits);
		
		JLabel lblNewLabel = new JLabel("Ali Sabbagh");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(641, 244, 128, 25);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mehmet Furkan Dogan");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(574, 322, 257, 36);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Umitcan Hesiboglu");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(599, 403, 212, 36);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Xheni Caka");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(631, 472, 170, 36);
		add(lblNewLabel_3);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	    g.drawImage(background, 0, 0, 1366, 768, null);
	}
	
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			console.selectOpt(0, -1);
		}
	}
}
