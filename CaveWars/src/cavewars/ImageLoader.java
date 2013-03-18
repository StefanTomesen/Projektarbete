package cavewars;

import java.util.HashMap;
import org.newdawn.slick.Image;

public class ImageLoader
{
    public static boolean initiated = false;
    
	public static HashMap<String, Image> imageMap = new HashMap();
	
	public static final String redPlayer = "spritesheet_player_red.png";
	public static final String yellowPlayer = "spritesheet_player_yellow.png";
	public static final String redArm = "player_arm_red.png";
	public static final String yellowArm = "player_arm_yellow.png";
	public static final String background = "Deep Cave.jpg";
	public static final String tiles = "Tiles/Brevid.png";
    public static final String dedScreen = "2-1.jpg";
	
	public static void initiateImageList()
	{
        if(initiated)
        {
             return;
        }
        initiated = true;
        
		String[] imageNames = { redPlayer,
								yellowPlayer,
								redArm,
								yellowArm,
								background,
								tiles, 
                                dedScreen };
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
