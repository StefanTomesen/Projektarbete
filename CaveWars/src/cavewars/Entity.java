package cavewars;

import org.newdawn.slick.*;

public abstract class Entity extends Renderable
{
	public int entityID;
	
	/** The horizontal velocity of the entity counting in m/s. */
	public float velocityX = 0.0F;
	/** The vertical velocity of the entity counting in m/s. */
	public float velocityY = 0.0F;
	/** The force of gravity counting in m/s^2 */
	public float gravity = 9.82F;
	
	public Entity(int entityID, float xPosition, float yPosition, float naturalHeight, String fileName, int xTiles, int yTiles)
	{
		super(xPosition, yPosition, naturalHeight, fileName, xTiles, yTiles);
		this.entityID = entityID;
	}
	
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