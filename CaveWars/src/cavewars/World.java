package cavewars;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

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
		tileGrid = new TileGrid(100, 60);
		System.out.println(tileGrid.ySize);
		camera = new Camera(tileGrid.xSize / 2, tileGrid.ySize / 2, tileGrid.ySize);
		System.out.println(1.0F / tileGrid.ySize);
		
		background = new Renderable(0, 0, 100, "stonetexture.jpg")
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
	
	public void keyPressed(int key, char character) {
		if(key == Input.KEY_LEFT)
		{
			localPlayer.doWalk(EntityPlayer.LEFT);
		}
		if(key == Input.KEY_RIGHT)
		{
			localPlayer.doWalk(EntityPlayer.RIGHT);
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
			localPlayer.velocityY = -5; // Negative values point up.
		}
	}

	public void keyReleased(int key, char character) {
		if(key == Input.KEY_LEFT && localPlayer.direction == EntityPlayer.LEFT)
		{
			localPlayer.doStop();
		}
		else if(key == Input.KEY_RIGHT && localPlayer.direction == EntityPlayer.RIGHT)
		{
			localPlayer.doStop();
		}
	}
}