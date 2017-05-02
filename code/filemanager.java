package mazerunner;
import java.util.ArrayList;

public class filemanager
{
	private ArrayList<String> namelist = new ArrayList<String>; // initialize namelist
	public  ArrayList<Image> imagelist = new ArrayList<Image>; // initialize image list
	
	public ArrayList<Image> getImagelist() 
	{
	    return imagelist;
	}
	
	public ArrayList<String> getNamelist() 
	{
	    return namelist;
	}
	
	
	
	
}