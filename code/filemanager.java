import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;

public class FileMngr
{
	private static ArrayList<String> namelist;
	public static ArrayList<Image> list = new ArrayList<Image>();
	public static ArrayList<Image> getImages()
	  {
	      namelist = new ArrayList();
	      namelist.add("bomb");
	      namelist.add("expl");
	      namelist.add("ai");
	      namelist.add("ai2");
	      namelist.add("right");
	      namelist.add("left");
	      namelist.add("up");
	      namelist.add("down");
	      namelist.add("life");
	      namelist.add("score");
	      namelist.add("speed");
	      namelist.add("bonus");
	    
	      for(String s : namelist)
	          list.add((new ImageIcon("src\\img\\" + s + ".png")).getImage());
	      return list;
	  }
	
	public static int[][] getMaze(int numP, int difficulty){
		//2 for 1 start
	    //3 for 1 end
		//-2 for 2 start
	    //-3 for 2 end
	    //1 for walls
		if(numP == 1 )
		{
			if(difficulty == 1)
			{
			    int[] a1 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
			    int[] a2 = {1,2,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1};
			    int[] a3 = {1,0,1,0,1,0,1,0,0,0,0,0,1,1,1,0,1,0,1};
			    int[] a4 = {1,0,1,0,1,0,1,1,1,1,0,1,0,0,1,0,1,0,1};
			    int[] a5 = {1,0,1,1,1,0,1,0,0,0,0,0,1,0,1,1,1,0,1};
		     	    int[] a6 = {1,0,0,0,0,0,1,0,1,0,1,1,0,0,0,0,0,3,1};
			    int[] a7 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
			    int[][] b1 = {a1, a2, a3, a4, a5, a6, a7};
			    return b1;
			}
			else if(difficulty == 2)
			{
			    int[] a1 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
			    int[] a2 = {1,2,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1};
			    int[] a3 = {1,0,1,0,1,0,1,0,0,0,0,0,1,1,1,0,1,0,1};
			    int[] a4 = {1,0,1,0,1,0,1,1,1,1,0,1,0,0,1,0,1,0,1};
			    int[] a5 = {1,0,1,1,1,0,1,0,0,0,0,0,1,0,1,1,1,0,1};
		     	    int[] a6 = {1,0,0,0,0,0,1,0,1,0,1,1,0,0,0,0,0,3,1};
			    int[] a7 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
			    int[][] b1 = {a1, a2, a3, a4, a5, a6, a7};
			    return b1;
			}
			else
			{
			    int[] a1 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
			    int[] a2 = {1,2,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1};
			    int[] a3 = {1,0,1,0,1,0,1,0,0,0,0,0,1,1,1,0,1,0,1};
			    int[] a4 = {1,0,1,0,1,0,1,1,1,1,0,1,0,0,1,0,1,0,1};
			    int[] a5 = {1,0,1,1,1,0,1,0,0,0,0,0,1,0,1,1,1,0,1};
		     	    int[] a6 = {1,0,0,0,0,0,1,0,1,0,1,1,0,0,0,0,0,3,1};
			    int[] a7 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
			    int[][] b1 = {a1, a2, a3, a4, a5, a6, a7};
			    return b1;
			}
		}
		else 
		{	
			if(difficulty == 1)
			{
			    int[] a1 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
			    int[] a2 = {1,2,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1};
			    int[] a3 = {1,0,1,0,1,0,1,0,0,0,0,0,1,1,1,0,1,0,1};
			    int[] a4 = {1,0,1,0,1,0,1,1,1,1,0,1,0,0,1,0,1,0,1};
			    int[] a5 = {1,0,1,1,1,0,1,0,0,0,0,0,-2,0,1,1,1,0,1};
		     	    int[] a6 = {1,-3,0,0,0,0,1,0,1,0,1,1,0,0,0,0,0,3,1};
			    int[] a7 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
			    int[][] b1 = {a1, a2, a3, a4, a5, a6, a7};
			    return b1;
			}
			else if(difficulty == 2)
			{
			    int a1[] = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
			    int[] a2 = {1,2,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1};
			    int[] a3 = {1,0,1,0,1,0,1,0,0,0,0,0,1,1,1,0,1,0,1};
			    int[] a4 = {1,0,1,0,1,0,1,1,1,1,0,1,0,0,1,0,1,0,1};
			    int[] a5 = {1,0,1,1,1,0,1,0,0,0,0,0,-2,0,1,1,1,0,1};
		     	    int[] a6 = {1,-3,0,0,0,0,1,0,1,0,1,1,0,0,0,0,0,3,1};
			    int[] a7 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
			    int[][] b1 = {a1, a2, a3, a4, a5, a6, a7};
			    return b1;
			}
			else
			{
			    int a1[] = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
			    int[] a2 = {1,2,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,1};
			    int[] a3 = {1,0,1,0,1,0,1,0,0,0,0,0,1,1,1,0,1,0,1};
			    int[] a4 = {1,0,1,0,1,0,1,1,1,1,0,1,0,0,1,0,1,0,1};
			    int[] a5 = {1,0,1,1,1,0,1,0,0,0,0,0,-2,0,1,1,1,0,1};
		     	    int[] a6 = {1,-3,0,0,0,0,1,0,1,0,1,1,0,0,0,0,0,3,1};
			    int[] a7 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
			    int[][] b1 = {a1, a2, a3, a4, a5, a6, a7};
			    return b1;
			}
		}
			
	  }
	
	public static ArrayList<String> getNames() throws FileNotFoundException
	{
		ArrayList<String> namelist = new ArrayList<String>(); // initialize namelist
		Scanner fileIn = new Scanner(new File("/Users/umitcanhasbioglu/Desktop/names.txt"));
		for(int i=0;i<5;i++)
		{
			String s = fileIn.next();
			namelist.add(s);
		}
		return namelist;
	}
	
	
	public static ArrayList<Integer> getScores() throws FileNotFoundException
	{
		ArrayList<Integer> scorelist = new ArrayList<>(); // initialize score list
		Scanner fileIn = new Scanner(new File("/Users/umitcanhasbioglu/Desktop/scores.txt"));
		for(int i=0;i<5;i++)
		{
			String s = fileIn.next();
			int x = Integer.parseInt(s);
			scorelist.add(x);
		}
		return scorelist;
	}
	
	
	
	public static void replaceOldName(String oldScore,String name) throws IOException
	{
		int foo = Integer.parseInt(oldScore);
		Scanner fileIn = new Scanner(new File("/Users/umitcanhasbioglu/Desktop/names.txt"));
		Scanner fileIn2 = new Scanner(new File("/Users/umitcanhasbioglu/Desktop/scores.txt"));
		for(int i=0;i<5;i++)
		{
			String oldName = fileIn.next();
			String score = fileIn2.next();
			int foo1 = Integer.parseInt(score);
			if(foo == foo1)
			{
			
			Path path = Paths.get("/Users/umitcanhasbioglu/Desktop/names.txt");
			Charset charset = StandardCharsets.UTF_8;
			String content = new String(Files.readAllBytes(path), charset);
			content = content.replaceAll( oldName , name );
			Files.write(path, content.getBytes(charset));
			
			}
		}
		
	}
	
	
	public static void saveHighScore(String name,String newScore, String oldScore) throws IOException
	{
		
		replaceOldName(oldScore,name);
		Path path = Paths.get("/Users/umitcanhasbioglu/Desktop/scores.txt");
		Charset charset = StandardCharsets.UTF_8;
		String content = new String(Files.readAllBytes(path), charset);
		content = content.replaceAll( oldScore , newScore );
		Files.write(path, content.getBytes(charset));

		
	}
	
	
}
