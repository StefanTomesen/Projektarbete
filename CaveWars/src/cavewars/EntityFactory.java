package cavewars;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.*;

public class EntityFactory
{
	/** A reference to the world this entity factory is linked to. */
	public World world;
	
	public EntityFactory(World world)
	{
		this.world = world;
	}
	
	public void createPlayer(int id, float x, float y, int team) throws SlickException
	{
		Image image = getTeamSpritesheetImage(team);
		SpriteSheet spritesheet = new SpriteSheet(image, 280, 500);
		
		EntityPlayer player = new EntityPlayer(id, x, y, spritesheet);
		world.player = player;
	}
	
	public static Image getTeamSpritesheetImage(int team)//throws SlickException
	{
		String fileName;
		switch(team) 
		{
			case EntityPlayer.RED_TEAM: fileName = "spritesheet_player_red.png"; break;
			case EntityPlayer.YELLOW_TEAM: fileName = "spritesheet_player_yellow.png"; break;
			default: fileName = "spritesheet_player_yellow"; break;
		}

		Image image;
		try {
			image = new Image("./resources/" + fileName);
		} 
		catch (SlickException ex) 
		{
			image = null;
			ex.printStackTrace();
		}
		
		return image;
	}
}