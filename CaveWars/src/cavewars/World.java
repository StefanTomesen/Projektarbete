package cavewars;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import static cavewars.CaveWars.windowHeight;
import static cavewars.CaveWars.windowWidth;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class World
{
	public static float zoomSteps = 1.2F;
	
	public TileGrid tileGrid;
	public EntityFactory entityFactory;
	
	public Camera camera;
	
	public ArrayList<Entity> entityList = new ArrayList();
	public ArrayList<EntityPlayer> playerList = new ArrayList();
	
	public EntityPlayer localPlayer;
	public Image background;
	
	public int currentBrushTile = 2; // The current tile we're painting.
	public int numberOfBrushes;
	
	public World() throws SlickException
	{	
		entityFactory = new EntityFactory(this);
		
		background = ImageLoader.getImage("Deep Cave.jpg");
		
		tileGrid = TileLoader.loadTiles();
		
		
		entityFactory.createPlayer(0, 50F, 25F, EntityPlayer.RED_TEAM);
		tileGrid = new TileGrid(100, 50);
		camera = new Camera(tileGrid.xSize / 2, tileGrid.ySize / 2, tileGrid.ySize / 5);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException 
	{	
		camera.updatePositon(localPlayer, tileGrid.ySize, tileGrid.ySize);
		
		drawBackground(camera, windowWidth, windowHeight);
		
		
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
	
	public ArrayList<Tile> getNearbyTiles(Entity entity)
	{
		ArrayList<Tile> tiles = new ArrayList();
		
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
				
				tiles.add(tile);
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
	
	public void mousePressed(int button, int x, int y)
	{
		System.out.println("Button: " + button);
		Position mousePosition = getWorldPosition(x, y, windowWidth, windowHeight);
			
		int xPos = (int)mousePosition.x;
		int yPos = (int)mousePosition.y;

		if(xPos < 0 || xPos >= tileGrid.xSize) return;
		if(yPos < 0 || yPos >= tileGrid.ySize) return;
		
		if(button == 0)
		{
			tileGrid.add(xPos, yPos, new Tile(currentBrushTile, xPos, yPos));
		}
		
		if(button == 1)
		{
			tileGrid.remove(xPos, yPos);
		}
		
		if(button == 2)
		{
			Tile tile = tileGrid.get(xPos, yPos);
			if(tile != null)
			{
				currentBrushTile = tile.id;
			}
		}
	}
	
	public Position getWorldPosition(int x, int y, int windowWidth, int windowHeight)
	{
		float blockSize = windowHeight / camera.scale;
		
		float xPos = x / blockSize;
		float yPos = y / blockSize;
		
		float xLevelCenterOffset = windowWidth / (2  * blockSize);
		float yLevelCenterOffset = windowHeight / (2  * blockSize);
		
		float xCameraOffset = camera.x;
		float yCameraOffset = camera.y;
		
		float finalXPos = xPos - xLevelCenterOffset + xCameraOffset;
		float finalYPos = yPos - yLevelCenterOffset + yCameraOffset;
		
		return new Position(finalXPos, finalYPos);
	}
	
	public Position getScreenPosition(float x, float y, int windowWidth, int windowHeight)
	{
		float blockSize = windowHeight / camera.scale;
		
		float xPos = x * blockSize;
		float yPos = y * blockSize;
		
		float xLevelCenterOffset = windowWidth / 2;
		float yLevelCenterOffset = windowHeight / 2;
		
		float xCameraOffset = camera.x * blockSize;
		float yCameraOffset = camera.y * blockSize;
		
		float finalXPos = xPos + xLevelCenterOffset - xCameraOffset;
		float finalYPos = yPos + yLevelCenterOffset - yCameraOffset;
		
		return new Position(finalXPos, finalYPos);
	}
}