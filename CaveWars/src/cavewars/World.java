package cavewars;

import converter.Camera;
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class World
{
	public TileGrid tileGrid;
	
	public EntityFactory entityFactory;
	public ArrayList<Entity> entityList = new ArrayList();
	//public ArrayList<EntityPlayer> playerList = new ArrayList();
	public EntityPlayer localPlayer;
	
	public Camera camera;
	
	public World() throws SlickException
	{
		entityFactory = new EntityFactory(this);
		entityFactory.createPlayer(0, 0.0F, 0.0F, EntityPlayer.RED_TEAM);
		tileGrid = new TileGrid(100, 60);
		System.out.println(tileGrid.ySize);
		camera = new Camera(tileGrid.xSize / 2, tileGrid.ySize / 2, 4.0F / tileGrid.ySize);
		System.out.println(1.0F / tileGrid.ySize);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException 
	{
		float gameScale = gc.getHeight() / tileGrid.ySize;
		
		localPlayer.render(camera, gc.getWidth(), gc.getHeight());
		for(Entity entity : entityList)
		{	
			entity.render(camera, gc.getWidth(), gc.getHeight());
		}
	}
	
	public void update(int delta)
	{
		localPlayer.updateAnimation(delta);
		localPlayer.updateMovementAndPhysics(delta);
	}
}