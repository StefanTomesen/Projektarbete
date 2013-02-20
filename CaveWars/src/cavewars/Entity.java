package cavewars;

import org.newdawn.slick.*;

public abstract class Entity
{
	public int entityID;
	
	public float xPosition;
	public float yPosition;
	
	/** The number of pixels of the largest side of the image. It is used to scale all entities independent of resolution. */
	public int largestSide;

	/** The height of this entity in blocks. */
	public float height;
	/** The width of this entity in blocks. */
	public float width;
	
	/** The horizontal velocity of the entity counting in m/s. */
	public float velocityX = 0.0F;
	/** The vertical velocity of the entity counting in m/s. */
	public float velocityY = 0.0F;
	/** The force of gravity counting in m/s^2 */
	public float gravity = 9.82F;
	
	public SpriteSheet spritesheet;
	public long animationTimer = 0;
	
	public Entity(int entityID, float xPosition, float yPosition, float naturalHeight, String fileName, int xTiles, int yTiles)
	{
		this.entityID = entityID;
		
		Image image = ImageLoader.getImage(fileName);
		int tileWidth = image.getWidth() / xTiles;
		int tileHeight = image.getHeight() / yTiles;
		this.spritesheet = new SpriteSheet(image, tileWidth, tileHeight);
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		
		int imageWidth = spritesheet.getWidth();
		int imageHeight = spritesheet.getHeight();
		
		this.height = naturalHeight;
		this.width = (int)(((double)imageWidth / imageHeight) * naturalHeight);
	}
	
	public void render(Camera camera, int windowWidth, int windowHeight)
	{
		float blockSize = windowHeight / camera.scale;
		float blockScale = blockSize / height;
		float scale = blockScale * height;
		
		float xPos = (xPosition) * blockSize;
		float yPos = (yPosition) * blockSize;
		
		float xCenterOffset = windowWidth / 2;
		float yCenterOffset = windowHeight / 2;
		
		float xCameraOffset = camera.x * blockSize;
		float yCameraOffset = camera.y * blockSize;
		
		float finalXPos = xPos + xCenterOffset - xCameraOffset;
		float finalYPos = yPos + yCenterOffset - yCameraOffset;
		
		Image image = spritesheet.getSprite(getSpriteXIndex(), getSpriteYIndex());
		
		image.setRotation(getRotation());
		image.draw(finalXPos, finalYPos, scale);
	}
	
	/**
	 * Returns the index of the current sprite in the spritesheet along the x axis.  If it is an image it should return 0.
	 */
	public abstract int getSpriteXIndex();
	
	/**
	 * Returns the index of the current sprite in the spritesheet along the y axis. If it is an image it should return 0.
	 */
	public abstract int getSpriteYIndex();
	
	public abstract void updateAnimation(long delta);
	
	public abstract float getRotation();
	
	public void update(World world, int delta)
	{
		float deltaSeconds = delta / 1000F;
		
		xPosition += velocityX * deltaSeconds;;
		
		yPosition += velocityY * deltaSeconds + gravity * deltaSeconds * deltaSeconds;
		velocityY += gravity * deltaSeconds; 
		
		if(yPosition >= world.tileGrid.ySize - 1)
		{
			velocityY = 0;
			yPosition = world.tileGrid.ySize - 1;
		}
	}
}