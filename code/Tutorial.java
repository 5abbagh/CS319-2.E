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

public class Tutorial extends JPanel implements ActionListener
{
//	private static ArrayList<String> namelist;
//	public static ArrayList<Image> getImages(){
//		namelist = new ArrayList();
//		namelist.add("bomb");
//		namelist.add("expl");
//		namelist.add("ai");
//		namelist.add("ai2");
//		namelist.add("right");
//		namelist.add("left");
//		namelist.add("up");
//		namelist.add("down");
//		ArrayList<Image> list = new ArrayList<Image>();
//		for(String s : namelist)
//			list.add((new ImageIcon("src\\img\\tutorials" + s + ".png")).getImage());
//		return list;
//	}
//    private Image BombImage;
//    private Image BonusImage;
//    private Image AIImage;
//    private Image AI2Image;
//    private Image PlayerRightImage;
//    private Image PlayerLeftImage;
//    private Image PlayerUpImage;
//    private Image PlayerDownImage;
	private FileManager fileManager = new FileManager();

	ArrayList<Image> imageList = FileManager.getImages();
	int count = 0;
	Image a;
	Image b;
	JLabel img1;
	JLabel img2;
	JPanel imagePanel;
	//My keys = new My();
	JButton prevButton;
	JButton nextButton;
	public Tutorial() 
	{
		ArrayList<Image> imageList = fileManager.getImages();
		//initImages();
		
		a = new ImageIcon("src\\img\\" + "right" + ".png").getImage();
		b = new ImageIcon("src\\img\\" + "score" + ".png").getImage();
		this.setPreferredSize(new Dimension(1366,768));
		setLayout(null);

		imagePanel = new JPanel();
		//imagePanel.setBackground(Color.MAGENTA);
		imagePanel.setBounds(405, 160, 657, 357);
		add(imagePanel);
		
		img1 = new JLabel("");
	
		img1.setIcon(new ImageIcon(imageList.get(count)));
		imagePanel.add(img1);
		img2 = new JLabel("");
		img2.setIcon(new ImageIcon(imageList.get(count + 1)));

		prevButton = new JButton("Prev");
		prevButton.setBounds(264, 340, 97, 25);
		add(prevButton);

		nextButton = new JButton("Next");
		nextButton.setBounds(1074, 340, 97, 25);
		add(nextButton);

		JButton menuButton = new JButton("Go back  menu");
		menuButton.setBounds(301, 551, 97, 25);
		add(menuButton);
		prevButton.addActionListener(this);
		nextButton.addActionListener(this);
	}


	// Inner Class MyKeysAdapter
	class My implements ActionListener 
	{
		
		@Override
		public void actionPerformed( ActionEvent e )
		
		{
			if ( (JButton) e.getSource() == nextButton )
			{	
				//this.removeAll();
				imagePanel.add(img2);
		//		this.revalidate();0
				count++;
			}
			if ( (JButton) e.getSource() == prevButton )
			{
				imagePanel.add(img1);
				count--;
				if(count == -1)
					count = 7;
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//	private void initImages(){
//        ArrayList<Image> imageList = fileManager.getImages();
//        BombImage = imageList.get(0);
//        BonusImage = imageList.get(1);
//        AIImage = imageList.get(2);
//        AI2Image = imageList.get(3);
//        PlayerRightImage = imageList.get(4);
//        PlayerLeftImage = imageList.get(5);
//        PlayerUpImage = imageList.get(6);
//        PlayerDownImage = imageList.get(7);
//  
//    }

}
