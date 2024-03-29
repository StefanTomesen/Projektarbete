package cavewars;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import static cavewars.CaveWars.windowHeight;
import static cavewars.CaveWars.windowWidth;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class World
{
	public boolean isServer;
	
	public static float zoomStep = 1.1F;
	
	public EntityFactory entityFactory;
	public TileGrid tileGrid;
	
	public Position RED_SPAWN;
	public Position YELLOW_SPAWN;
	
	public Camera camera;
	
	public CopyOnWriteArrayList<Entity> entityList = new CopyOnWriteArrayList();
	public CopyOnWriteArrayList<EntityPlayer> playerList = new CopyOnWriteArrayList();
	
	public EntityPlayer localPlayer;
	public Image background;
	
	public int currentBrushTile = 2; // The current tile we're painting.
	
	public boolean isRightPressed = false;
	public boolean isLeftPressed = false;
	public boolean isSpacePressed = false;
	public boolean isUpPressed = false;
	
	public Position cursorPosition = new Position(0F,0F);
	
	public World(boolean isServer) throws SlickException
	{	
		this.isServer = isServer;
		
		background = ImageLoader.getImage(ImageLoader.background);
		entityFactory = new EntityFactory(this);
	}
		
	public void serverInit() throws SlickException
	{
		tileGrid = TileLoader.loadTiles(MapMenu.chosenMap);
		
		RED_SPAWN = new Position(tileGrid.xSize/4, tileGrid.ySize * 2 / 3);
		YELLOW_SPAWN = new Position(tileGrid.xSize*3/4, tileGrid.ySize * 2 / 3);
    }
	
	public void clientInit(int tileWidth, int tileHeight)
	{
		tileGrid = new TileGrid(tileWidth, tileHeight);
		
		camera = new Camera(tileGrid.xSize * 2 / 3,tileGrid.ySize * 2 / 3, tileGrid.ySize / 5);
	}
	
	public EntityPlayer getPlayer(int entityID)
	{
		EntityPlayer targetPlayer = null;
		for(EntityPlayer player : playerList)
		{
			if(player.entityID == entityID)
			{
				targetPlayer = player;
			}
		}
		return targetPlayer;
	}
	
	public Entity getEntity(int entityID)
	{
		Entity targetEntity = null;
		for(Entity entity : entityList)
		{
			if(entity.entityID == entityID)
			{
				targetEntity = entity;
			}
		}
		return targetEntity;
	}
	
	public void removePlayer(EntityPlayer player)
	{
		playerList.remove(player);
		entityList.remove(player);
	}
	
	public void removeEntity(Entity entity)
	{
		entityList.remove(entity);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException 
	{	
		if(localPlayer == null)
		{
			return;
		}
		
		camera.updatePositon(localPlayer, tileGrid.ySize, tileGrid.ySize);
		
		drawBackground(camera, windowWidth, windowHeight);
		
		
		for(Entity entity : entityList){	
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
		
		for(EntityPlayer player : playerList)
		{
			player.render(camera, gc.getWidth(), gc.getHeight());
		}
		grphcs.drawString("Entities: " + entityList.size(), 10, 30);
	}
	
	public void update(int delta)
	{
		if(localPlayer != null)
		{
			if(isSpacePressed) localPlayer.jump();

			if(isUpPressed) localPlayer.climb();
			else localPlayer.pauseClimbing();

			localPlayer.updateMovement(isLeftPressed, isRightPressed);
		}
		//System.out.println("Gamelogic");
		
		for(Entity entity : entityList)
		{
			// Run all the physics (movement + collision)
			entity.update(this, delta);
			entity.updateAnimation(delta);
			
			if(entity.yPosition > tileGrid.ySize)
			{
				if(entity instanceof EntityPlayer)
				{
					if(entity == localPlayer)
					{
						CaveWars.caveWars.enterState(CaveWars.DEAD_MENU_STATE);
					}
				}
				else
				{
					entity.outsideWorld = true;
				}
			}
		}
	
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
		if(key == Input.KEY_A)
		{
			isLeftPressed = true;
		}
		if(key == Input.KEY_D)
		{
			isRightPressed = true;
		}
		if(key == Input.KEY_W)
		{
			isUpPressed = true;
		}
		if(key == Input.KEY_S)
		{
			localPlayer.dropFromLadder();
		}
		
		if(key == Input.KEY_SPACE)
		{
			isSpacePressed = true;
		}
		
		if(key == Input.KEY_E)
		{
			cycleBrush(1);
		}
		if(key == Input.KEY_Q)
		{
			cycleBrush(-1);
		}

	}

	public void keyReleased(int key, char character) {
		if(key == Input.KEY_A)
		{
			isLeftPressed = false;
		}
		if(key == Input.KEY_D)
		{
			isRightPressed = false;
		}
		if(key == Input.KEY_W)
		{
			isUpPressed = false;
		}
		if(key == Input.KEY_SPACE)
		{
			isSpacePressed = false;
		}
	}
	
	public void mousePressed(PacketCentral serverConnection, int button, int x, int y)
	{
		Position mousePosition = getWorldPosition(x, y, windowWidth, windowHeight);
			
		int xPos = (int)mousePosition.x;
		int yPos = (int)mousePosition.y;

		if(xPos < 0 || xPos >= tileGrid.xSize) return;
		if(yPos < 0 || yPos >= tileGrid.ySize) return;
		
		if(button == 0)
		{
			Tile tile = new Tile(currentBrushTile, xPos, yPos);
			serverConnection.sendPacket(new Packet4AddTile(tile));
		}
		
		if(button == 1)
		{
			serverConnection.sendPacket(new Packet5RemoveTile(xPos, yPos));
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
	
	public void mouseWheelMoved(int steps)
	{	
		if(steps > 0)
		{
			if(camera.scale > 4)
			{
				camera.zoom(1 / zoomStep);
			}
		}
		else
		{
			if(camera.scale < tileGrid.ySize)
			{
				camera.zoom(zoomStep);
			}
		}
	}
	
	/**
	 * Modulus that also works properly with negative numbers.
	 * @param value The value to the left of the modulus, the value that is operated on.
	 * @param limit The limiting value, the cap. The number to ther right of the modulus sign.
	 */
	public int properModulus(int value, int limit)
	{
		return ((value % limit) + limit) % limit;
	}
	
	/**
	 * 
	 * @param direction 
	 */
	public void cycleBrush(int offset)
	{
		currentBrushTile = properModulus(currentBrushTile + offset - 1, Tile.getNumberOfTiles()) + 1; 
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