package cavewars;

import org.newdawn.slick.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class EntityFactory
{
	/** A reference to the world this entity factory is linked to. */
	public World world;
	
	public EntityFactory(World world)
	{
		this.world = world;
	}
	
	public EntityPlayer createPlayer(int id, int team) throws SlickException
	{
		Position spawn;
		if(team == EntityPlayer.RED_TEAM)
		{
			spawn = world.RED_SPAWN;
		}
		else
		{
			spawn = world.YELLOW_SPAWN;
		}
		
		if(spawn == null)
		{
			System.out.println("Spawn = null");
		}
		
		EntityPlayer player = new EntityPlayer(id, spawn.x, spawn.y, team);
		world.playerList.add(player);
		
		return player;
	}
}