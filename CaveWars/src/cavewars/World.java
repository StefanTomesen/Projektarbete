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
	
	public Image background;
	
	public Camera camera;
	public static float zoomSteps = 1.2F;
	
	public World() throws SlickException
	{
		entityFactory = new EntityFactory(this);
		entityFactory.createPlayer(0, 50F, 25F, EntityPlayer.RED_TEAM);
		tileGrid = new TileGrid(100, 50);
		camera = new Camera(tileGrid.xSize / 2, tileGrid.ySize / 2, tileGrid.ySize / 5);
		
		background = ImageLoader.getImage("Deep Cave.jpg");
		
		tileGrid = TileLoader.loadTiles();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException 
	{	
		camera.updatePositon(localPlayer, tileGrid.ySize, tileGrid.ySize);
		
		drawBackground(camera, gc.getWidth(), gc.getHeight());
		
		
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
	
	public void drawBackground(Camera camera, int windowWidth, int windowHeight)
	{
		float backgroundTileSize = 10.0F;
		float panSpeed = 0.7F;
		
		float deltaCameraX = ((camera.x * panSpeed) % backgroundTileSize);
		float deltaCameraY = ((camera.y * panSpeed) % backgroundTileSize);
		
		int numberOfTilesX = (int)Math.ceil(camera.scale * ((float)windowWidth / windowHeight) / backgroundTileSize) + 1;
		int numberOfTilesY = (int)Math.ceil(camera.scale / backgroundTileSize) + 1;

		for(int x = -numberOfTilesX / 2; x < numberOfTilesX / 2 + 1; x++)
		{
			for(int y = -numberOfTilesY / 2; y < numberOfTilesY / 2 + 1; y++)
			{
				float blockSize = windowHeight / camera.scale;
				float tileSize = blockSize * backgroundTileSize;
				
				float entityScale = backgroundTileSize * blockSize / background.getHeight();

				float xPos = x * tileSize;
				float yPos = y * tileSize;
				
				float xCameraOffset = deltaCameraX * blockSize;
				float yCameraOffset = deltaCameraY * blockSize;

				float xScreenCenterOffset = windowWidth / 2;
				float yScreenCenterOffset = windowHeight / 2;
				
				float finalXPos = xPos - xCameraOffset + xScreenCenterOffset;
				float finalYPos = yPos - yCameraOffset + yScreenCenterOffset;

				background.draw(finalXPos, finalYPos, entityScale);
			}
		}
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