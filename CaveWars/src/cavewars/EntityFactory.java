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
	
	public EntityPlayer createPlayer(int id, int id2, int team) throws SlickException
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
		
		EntityPlayer player = new EntityPlayer(world, id, id2, spawn.x, spawn.y, team);
		world.playerList.add(player);
		world.entityList.add(player);
		
		return player;
	}
}