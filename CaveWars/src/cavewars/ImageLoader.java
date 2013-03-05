package cavewars;

import java.util.HashMap;
import org.newdawn.slick.Image;

public class ImageLoader
{
	public static HashMap<String, Image> imageMap = new HashMap();
	
	public static final String redTeam = "spritesheet_player_red.png";
	public static final String yellowTeam = "spritesheet_player_yellow.png";
	public static final String background = "Deep Cave.jpg";
	public static final String tiles = "Tiles/Brevid.png";
	
	public static void initiateImageList()
	{
		String[] imageNames = { redTeam,
								yellowTeam,
								background,
								tiles};
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
