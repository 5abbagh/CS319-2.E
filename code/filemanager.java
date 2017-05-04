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


public class FileMngr
{

	public static ArrayList<String> namelist = new ArrayList<String>(); // initialize namelist
	public static ArrayList<Integer> scorelist = new ArrayList<>(); // initialize score list
	
	public ArrayList<String> getNames() throws FileNotFoundException
	{
		Scanner fileIn = new Scanner(new File("/Users/umitcanhasbioglu/Desktop/names.txt"));
		for(int i=0;i<5;i++)
		{
			String s = fileIn.next();
			namelist.add(s);
		}
		return namelist;
	}
	
	public ArrayList<String> getScores() throws FileNotFoundException
	{
		Scanner fileIn = new Scanner(new File("/Users/umitcanhasbioglu/Desktop/scores.txt"));
		for(int i=0;i<5;i++)
		{
			String s = fileIn.next();
			int x = Integer.parseInt(s);
			scorelist.add(x);
		}
		return namelist;
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
	public static void main(String[] args) throws IOException
	{
		saveHighScore("xxx","1243434","55");
		System.out.println(namelist);
		System.out.println(scorelist);
		
		
	}
	
}

