package cavewars;

import java.awt.image.BufferedImage;
import org.newdawn.slick.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public abstract class Renderable
{
	/** The position along the x axis in meters. */
	public float xPosition;
	/** The position along the y axis in meters. */
	public float yPosition;
	/** The rotation of the entity in degrees. */
	public float rotation = 0F;

	/** The height of this entity in blocks. */
	public float height;
	/** The width of this entity in blocks. */
	public float width;
	/** The number of pixels the image is wide. */
	public int imageWidth;
	/** The number of pixels the image is tall. */
	public int imageHeight;
	
	public boolean tiledImage;
	
	/** If the entity only uses a single image, this is it. */
	public Image image;
	/** If the entity animates of has different variants, such as with the tiles, they are stored
	 *  in this spritesheet */
	public SpriteSheet spritesheet;
	
	public long animationTimer = 0;
	
	private Renderable(float xPosition, float yPosition, float naturalHeight, String fileName, int xTiles, int yTiles, boolean tiledImage)
	{
		this.tiledImage = tiledImage;
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		
		loadImage(fileName);
		
		int tileWidth = image.getWidth() / xTiles;
		int tileHeight = image.getHeight() / yTiles;
		this.spritesheet = new SpriteSheet(image, tileWidth, tileHeight);
		
		this.imageWidth = tileWidth;
		this.imageHeight = tileHeight;
		
		this.height = naturalHeight;
		this.width = (float)(((double)imageWidth / imageHeight) * naturalHeight);
	}
	
	public Renderable(float xPosition, float yPosition, float naturalHeight, String fileName)
	{
		this(xPosition, yPosition, naturalHeight, fileName, 1, 1, false);
	}
	
	public Renderable(float xPosition, float yPosition, float naturalHeight, String fileName, int xTiles, int yTiles)
	{
		this(xPosition, yPosition, naturalHeight, fileName, xTiles, yTiles, true);
	}
	
	public void render(Camera camera, int windowWidth, int windowHeight)
	{
		float blockSize = windowHeight / camera.scale;
		float entityScale = height * blockSize / imageHeight;
		
		Position pixelPos = getPixelPos(camera, windowWidth, windowHeight);
		
		Image image;
		if(tiledImage)
		{
			image = spritesheet.getSprite(getSpriteXIndex(), getSpriteYIndex());
		}
		else
		{
			image = this.image;
		}
		
		image.setCenterOfRotation(blockSize * EntityArm.centerOffsetX, blockSize * EntityArm.centerOffsetY);
		image.setRotation(rotation);
		image.draw(pixelPos.x, pixelPos.y, entityScale);
	}
	
	public Position getPixelPos(Camera camera, int windowWidth, int windowHeight)
	{
		float blockSize = windowHeight / camera.scale;
		
		float xPos = xPosition * blockSize;
		float yPos = yPosition * blockSize;
		
		float xLevelCenterOffset = windowWidth / 2;
		float yLevelCenterOffset = windowHeight / 2;
		
		float xCameraOffset = camera.x * blockSize;
		float yCameraOffset = camera.y * blockSize;
		
		float finalXPos = xPos + xLevelCenterOffset - xCameraOffset;
		float finalYPos = yPos + yLevelCenterOffset - yCameraOffset;
		
		return new Position(finalXPos, finalYPos);
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
