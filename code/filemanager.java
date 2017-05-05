package mazerunner;

import java.awt.Image;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.ImageIcon;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class FileManager
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
            String s = numP + "" + difficulty;
            String path = "src\\text\\" + s + ".txt";
            Scanner scan = null;
            try {
            scan = new Scanner(new File(path)); }catch(Exception e){e.printStackTrace();}
            String line = scan.nextLine();
            ArrayList<Integer[]> list = new ArrayList();
            while(!line.equals("")){
              
                String[] lineList = line.split(",");
                Integer[] intList = new Integer[lineList.length];
                for(int i = 0; i < intList.length; i++){
                    intList[i] = Integer.parseInt(lineList[i]);
                }
                list.add(intList);
                try{
                    line = scan.nextLine();
                }catch (java.util.NoSuchElementException e){
                    break;
                }
            }
            int [][] ret = new int[list.size()][list.get(0).length];
            for(int i = 0; i < ret.length; i++){
                for(int j = 0; j < ret[0].length; j++){
                    
                    ret[i][j] = list.get(i)[j];
                    
                }
            }
            return ret;
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
		Scanner fileIn = new Scanner(new File("C:\\Users\\LUL\\Documents\\NetBeansProjects\\test\\src\\text\\scores.txt"));
		for(int i=0;i<5;i++)
		{
			String s = fileIn.next();
			int x = Integer.parseInt(s);
			scorelist.add(x);
                        System.out.println(x);
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

                ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
                for (int i = 0; i < 5; i++) {
                    if (fileContent.get(i).equals(oldScore)) {
                        fileContent.set(i, newScore);
                        break;
                    }
                }

                Files.write(path, fileContent, StandardCharsets.UTF_8);
		
	}
        
        public static AudioInputStream[] getMusic(){
            
            String file1 = "src\\sound\\1.wav";
            String file2 = "src\\sound\\2.wav";
            AudioInputStream[] in = new AudioInputStream[2];
            try {
                for(int i = 0; i < 2; i++){
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("src\\sound\\" + (i + 1) + ".wav"));
                    in[i] = audioStream;
                }
            } catch (Exception e) {
                
            }
            return in;
}
	
	
}
