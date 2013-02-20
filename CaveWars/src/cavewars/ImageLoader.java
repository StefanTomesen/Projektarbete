package cavewars;

import java.util.HashMap;
import org.newdawn.slick.Image;

public class ImageLoader
{
	public static HashMap<String, Image> imageMap = new HashMap();
	
	public static void initiateImageList()
	{
		String[] imageNames = { "spritesheet_player_red.png",
								"spritesheet_player_yellow.png",
								"Aerials0022_L.jpg"};
		for(String name : imageNames)
		{
			Image image;
			try
			{
				image = new Image("resources/" + name);
			}
			catch(Exception e)
			{
				System.out.println("Invalid image path!");
				e.printStackTrace();
				continue;
			}
			
			imageMap.put(name, image);
		}
	}
	
	public static Image getImage(String fileName)
	{
		return imageMap.get(fileName);
	}
}
