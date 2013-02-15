package cavewars;

import org.newdawn.slick.*;

public abstract class Entity
{
	public int entityID;
	
	public float xPosition;
	public float yPosition;
	
	/** The number of pixels of the largest side of the image. It is used to scale all entities independent of resolution. */
	public int largestSide;
	/** The size of this entity counting in blocks. */
	public float naturalScale;
	
	public float velocityX = 0.0F;
	public float velocityY = 0.0F;
	public float gravity = 9.82F;
	
	public SpriteSheet spritesheet;
	public long animationTimer = 0;
	
	public Entity(int entityID, float xPosition, float yPosition, float naturalScale, String fileName, int xTiles, int yTiles)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.naturalScale = naturalScale;
		
		Image image = ImageLoader.getImage(fileName);
		int tileWidth = image.getWidth() / xTiles;
		int tileHeight = image.getHeight() / yTiles;
		this.spritesheet = new SpriteSheet(image, tileWidth, tileHeight);
		this.largestSide = getLargestSide(spritesheet);
	}
	
	public void render(Camera camera, int windowWidth, int windowHeight)
	{
		float blockSize = camera.scale * windowHeight;
		float blockScale = blockSize / largestSide;
		float scale = blockScale * naturalScale;
		
		float xOffset = windowWidth / 2;
		float yOffset = windowHeight / 2;
		
		float xPos = xPosition * blockSize;
		float yPos = yPosition * blockSize;
		
		Image image = spritesheet.getSprite(getSpriteXIndex(), getSpriteYIndex());
		
		image.setRotation(getRotation());
		image.draw(xPos + xOffset, yPos + yOffset, scale);
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
	
	public void updateMovementAndPhysics(int delta)
	{
		float deltaX = delta * velocityX / 1000;
		xPosition += deltaX;
	}
	
	private int getLargestSide(SpriteSheet spritesheet)
	{
		int width = spritesheet.getWidth();
		int height = spritesheet.getHeight();
		
		int largestSide;
		if(height > width)
		{
			largestSide = height;
		}
		else
		{
			largestSide = width;
		}
		
		return largestSide;
	}
}