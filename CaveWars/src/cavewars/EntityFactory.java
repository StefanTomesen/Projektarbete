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
	
	public void createPlayer(int id, float x, float y, String teamColorFileName) throws SlickException
	{		
		EntityPlayer player = new EntityPlayer(id, x, y, teamColorFileName, 4, 2);
		world.localPlayer = player;
	}
}