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
	
	public float velocityX;
	public float velocityY;
	public float gravity = 9.82F;
	
	public SpriteSheet spritesheet;
	public long animationTimer = 0;
	public long animationFPS;
	
	public Entity(int entityID, float xPosition, float yPosition, float naturalScale, SpriteSheet spritesheet)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.naturalScale = naturalScale;
		
		this.spritesheet = spritesheet;
		this.largestSide = getLargestSide(spritesheet);
		
		this.velocityX = 0;
		this.velocityY = 0;
	}
	
	public Entity(int entityID, float xPosition, float yPosition, float naturalScale, Image image)
	{
		this(entityID, xPosition, yPosition, naturalScale, new SpriteSheet(image, image.getWidth(), image.getHeight()));
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
		System.out.println("Scale" + scale);
		image.draw(xPos + xOffset, yPos + yOffset, scale);
	}
	
	/**
	 * Returns the index of the current sprite in the spritesheet along the x axis. By default, it returns the first
	 * sprite so this inly has to be specified when the entity actually switches sprites.
	 */
	public int getSpriteXIndex()
	{
		return 0;
	}
	
	/**
	 * Returns the index of the current sprite in the spritesheet along the y axis. By default, it returns the first
	 * sprite so this only has to be specified when the entity actually switches sprites.
	 */
	public int getSpriteYIndex()
	{
		return 0;
	}
	
	public void updateAnimation(long delta){}
	
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
	
	public float getRotation()
	{
		return 0;
	}
}