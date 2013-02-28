package cavewars;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class World
{
	public TileGrid tileGrid;
	
	public EntityFactory entityFactory;
	public ArrayList<Entity> entityList = new ArrayList();
	public ArrayList<EntityPlayer> playerList = new ArrayList();
	public EntityPlayer localPlayer;
	
	public Renderable background;
	
	public Camera camera;
	public static float zoomSteps = 1.2F;
	
	public World() throws SlickException
	{
		entityFactory = new EntityFactory(this);
		entityFactory.createPlayer(0, 50F, 25F, EntityPlayer.RED_TEAM);
		tileGrid = new TileGrid(100, 50);
		camera = new Camera(tileGrid.xSize / 2, tileGrid.ySize / 2, tileGrid.ySize / 5);
		
		background = new Renderable(0, 0, 50, "stonetexture.jpg")
		{

			@Override
			public int getSpriteXIndex(){return 0;}

			@Override
			public int getSpriteYIndex(){return 0;}

			@Override
			public void updateAnimation(long delta){}
			
		};
		
		tileGrid = TileLoader.loadTiles();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException 
	{	
		camera.updatePositon(localPlayer, tileGrid.ySize, tileGrid.ySize);
		
		background.render(camera, gc.getWidth(), gc.getHeight());
		
		for(Entity entity : entityList)
		{	
			entity.render(camera, gc.getWidth(), gc.getHeight());
		}
		for(int x = 0; x < tileGrid.xSize; x++)
		{
			for(int y = 0; y < tileGrid.ySize; y++)
			{
				Tile tile = tileGrid.get(x, y);
				if(tile != null)
				{
					tile.render(camera, gc.getWidth(), gc.getHeight());
				}
			}
		}
		
		localPlayer.render(camera, gc.getWidth(), gc.getHeight());

	}
	
	public void update(int delta)
	{
		localPlayer.updateAnimation(delta);
		localPlayer.update(this, delta);
	}
	
	public ArrayList<CollisionBox> getNearbyTiles(Entity entity)
	{
		ArrayList<CollisionBox> tiles = new ArrayList();
		
		int x = (int)Math.round(entity.xPosition);
		int y = (int)Math.round(entity.yPosition);
		
		for(int i = x - 2; i <= x + 2; i++)
		{
			for(int j = y - 2; j <= y + 2; j++)
			{	
				Tile tile = tileGrid.get(i, j);
				if(tile == null)
				{
					continue;
				}
				
				tiles.add(new CollisionBox(tile));
			}
		}
		
		return tiles;
	}
	
	public void keyPressed(int key, char character) {
		if(key == Input.KEY_LEFT)
		{
			localPlayer.setWalking(EntityPlayer.LEFT);
		}
		if(key == Input.KEY_RIGHT)
		{
			localPlayer.setWalking(EntityPlayer.RIGHT);
		}
		if(key == Input.KEY_A)
		{
			if(camera.scale < tileGrid.ySize)
			{
				camera.zoom(zoomSteps);
			}
		}
		if(key == Input.KEY_Z)
		{
			if(camera.scale > 0)
			{
				camera.zoom(1 / zoomSteps);
			}
		}
		
		if(key == Input.KEY_SPACE)
		{
			localPlayer.jump();
		}
	}

	public void keyReleased(int key, char character) {
		if(key == Input.KEY_LEFT && localPlayer.direction == EntityPlayer.LEFT)
		{
			localPlayer.setStop();
		}
		else if(key == Input.KEY_RIGHT && localPlayer.direction == EntityPlayer.RIGHT)
		{
			localPlayer.setStop();
		}
	}
}