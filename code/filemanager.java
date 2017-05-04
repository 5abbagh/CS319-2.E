
package mazerunner;
import java.awt.Image;
import java.util.ArrayList;

public class filemngr
{
	private ArrayList<String> namelist = new ArrayList<String>(); // initialize namelist
	public  ArrayList<Image> imagelist = new ArrayList<Image>(); // initialize image list
	
	public ArrayList<Image> getImagelist() 
	{
	    return imagelist;
	}
	
	public ArrayList<String> getNamelist() 
	{
	    return namelist;
	}
	
	public ArrayList<Image> setImagelist() 
	{
		this.imagelist = imagelist;
	}
	
	public ArrayList<Image> setnamelist() 
	{
		this.namelist = namelist;
	}
	public static int readMaze(int numPlayers,int level)
	{
		int[][] abc = new int[2][3];
		if(numPlayers == 1)
		{
			if(level == 1)
				return abc[1][1];
			else if(level==2 )
				return abc[1][2];
			else
				return abc[1][3];	
		}
		else if(numPlayers == 1)
		{
			if(level == 2)
				return abc[2][1];
			else if(level==2 )
				return abc[2][2];
			else
				return abc[2][3];	
		}
		
	}
	
	
	
}
