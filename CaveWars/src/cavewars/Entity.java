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
		
		ArrayList<CollisionBox> nearbyTiles = world.getNearbyTiles(this);
		CollisionBox entityCollision = new CollisionBox(this);
		
		System.out.print("Player: ");
		entityCollision.print();
		
		Vector movement = getMovementVector(deltaSeconds);
		for(CollisionBox tile : nearbyTiles)
		{
			boolean collided = entityCollision.collidesWith(tile, movement);
			if(collided)
			{
				System.out.print("Tile: ");
				tile.print();
				movement = entityCollision.getMotionVectorAfterCollision(tile, movement);
			}
		}
		
		velocityY += gravity * deltaSeconds; // Update the velocity.
		
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
	
	public Vector getMovementVector(float deltaSeconds)
	{
		float deltaX = velocityX * deltaSeconds;
		float deltaY = velocityY * deltaSeconds + gravity * deltaSeconds * deltaSeconds / 2;
		
		return new Vector(deltaX, deltaY);
	}
}