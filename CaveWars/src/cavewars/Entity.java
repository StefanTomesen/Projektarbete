package cavewars;

import java.util.ArrayList;
import org.newdawn.slick.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public abstract class Entity extends Renderable
{
	public int entityID;
	
	/** The horizontal velocity of the entity counting in m/s. */
	public float velocityX = 0.0F;
	/** The vertical velocity of the entity counting in m/s. */
	public float velocityY = 0.0F;
	
	/** The force of gravity counting in m/s^2 */
	public static float gravity = 9.82F;
	
	public Entity(int entityID, float xPosition, float yPosition, float naturalHeight, String fileName, int xTiles, int yTiles)
	{
		super(xPosition, yPosition, naturalHeight, fileName, xTiles, yTiles);
		this.entityID = entityID;
	}
	
	public abstract float getRotation();
	
	public void update(World world, int delta)
	{
		float deltaSeconds = delta / 1000F;
		
		ArrayList<Tile> nearbyTiles = world.getNearbyTiles(this);
		CollisionBox entityCollision = new CollisionBox(this);
		
		float verticalAcceleration = getVerticalAcceleration();
		
		float deltaX = velocityX * deltaSeconds;
		float deltaY = velocityY * deltaSeconds + verticalAcceleration * deltaSeconds * deltaSeconds / 2;
		
		Vector movement = new Vector(deltaX, deltaY);
		
		resetCollisionStatus();
		for(Tile tile : nearbyTiles)
		{
			CollisionBox tileBox = new CollisionBox(tile);
			
			if(entityCollision.collidesWith(tileBox, movement))
			{
				boolean onGround = tile.isSolid() && entityCollision.collidesWith(tileBox, new Vector(0, movement.y)) && yPosition < tile.yPosition;
				hasCollided(tile.id, onGround);
				if(tile.isSolid())
				{
					movement = entityCollision.getMotionVectorAfterCollision(tileBox, movement);
				}
			}
		}
		
		velocityY += verticalAcceleration * deltaSeconds; // Update the velocity.
		
		xPosition += movement.x;
		yPosition += movement.y;
		
		/* If we haven't moved any distance frame, assume that we've been prevented by a collision.
		 * This is not entirely correct as the acceleration also affects the distance moved during
		 * the frame, but this will not cause any trouble, so it doesn't matter. */
		if(movement.x == 0) 
		{
			velocityX = 0;
		}
		if(movement.y == 0) 
		{
			velocityY = 0;
		}
	}
	
	public abstract float getVerticalAcceleration();
	
	/** 
	 * Does logic depending on what it is we've hit. Usually sets flags that we're touching somehting. 
	 */
	public abstract void hasCollided(int id, boolean onGround);
	
	/** 
	 * Resets any flags set by the hasCollided method before the collision is reevaluated the next frame.
	 */
	public abstract void resetCollisionStatus();
}