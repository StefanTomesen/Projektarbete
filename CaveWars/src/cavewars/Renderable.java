package cavewars;

import java.awt.image.BufferedImage;
import org.newdawn.slick.*;

public abstract class Renderable
{
	public float xPosition;
	public float yPosition;
	public float rotation;
	
	/** The number of pixels of the largest side of the image. It is used to scale all entities independent of resolution. */
	public int largestSide;

	/** The height of this entity in blocks. */
	public float height;
	/** The width of this entity in blocks. */
	public float width;
	/** The number of pixels the image is wide. */
	public int imageWidth;
	/** The number of pixels the image is tall. */
	public int imageHeight;
	
	public boolean tiledImage;
	
	public Image image;
	public SpriteSheet spritesheet;
	public long animationTimer = 0;
	
	private Renderable(float xPosition, float yPosition, float naturalHeight, boolean tiledImage)
	{
		this.tiledImage = tiledImage;
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		
		this.height = naturalHeight;
		this.width = (int)(((double)imageWidth / imageHeight) * naturalHeight);
	}
	
	public Renderable(float xPosition, float yPosition, float naturalHeight, String fileName)
	{
		this(xPosition, yPosition, naturalHeight, true);
		loadImage(fileName);
		
		this.imageWidth = image.getWidth();
		this.imageHeight = image.getHeight();
		
		tiledImage = false;
	}
	
	public Renderable(float xPosition, float yPosition, float naturalHeight, String fileName, int xTiles, int yTiles)
	{
		this(xPosition, yPosition, naturalHeight, true);
		loadImage(fileName);
		
		int tileWidth = image.getWidth() / xTiles;
		int tileHeight = image.getHeight() / yTiles;
		this.spritesheet = new SpriteSheet(image, tileWidth, tileHeight);
		
		this.imageWidth = tileWidth;
		this.imageHeight = tileHeight;
		
		tiledImage = true;
	}
	
	public final void render(Camera camera, int windowWidth, int windowHeight)
	{
		float blockSize = windowHeight / camera.scale;
		float entityScale = height * blockSize / imageHeight;
		
		float xPos = xPosition * blockSize;
		float yPos = yPosition * blockSize;
		
		float xLevelCenterOffset = windowWidth / 2;
		float yLevelCenterOffset = windowHeight / 2;
		
		float xImageCenterOffset = imageWidth / 2;
		float yImageCenterOffset = imageHeight / 2;
		
		float xCameraOffset = camera.x * blockSize;
		float yCameraOffset = camera.y * blockSize;
		
		float finalXPos = xPos + xLevelCenterOffset - xCameraOffset;// - xImageCenterOffset;
		float finalYPos = yPos + yLevelCenterOffset - yCameraOffset;// - yImageCenterOffset;
		
		Image image;
		if(tiledImage)
		{
			image = spritesheet.getSprite(getSpriteXIndex(), getSpriteYIndex());
		}
		else
		{
			image = this.image;
		}
		
		image.setRotation(rotation);
		image.draw(finalXPos, finalYPos, entityScale);
	}
	
	public void loadImage(String fileName)
	{
		this.image = ImageLoader.getImage(fileName);
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
	
}
