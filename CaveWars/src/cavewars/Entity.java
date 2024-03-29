package cavewars;

import java.util.ArrayList;
import org.newdawn.slick.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public abstract class Entity extends Renderable
{
	public int entityID;
	
	public Entity parent;
	public Entity child;
	
	/** The world the entity exists in. */
	public World world;
	
	/** The horizontal velocity of the entity counting in m/s. */
	public float velocityX = 0.0F;
	/** The vertical velocity of the entity counting in m/s. */
	public float velocityY = 0.0F;
	
	/** The force of gravity counting in m/s^2 */
	public static float gravity = 9.82F;
	
	public boolean outsideWorld = false;
	
	public Entity(World world, int entityID, Entity child, float xPosition, float yPosition, float naturalHeight, String fileName, int xTiles, int yTiles)
	{
		super(xPosition, yPosition, naturalHeight, fileName, xTiles, yTiles);
		this.world = world;
		this.entityID = entityID;
		this.child = child;
		if(child != null) child.parent = this;
	}
	
	public void update(World world, int delta)
	{		
		float deltaSeconds = delta / 1000F;
		if(deltaSeconds > 0.3f) deltaSeconds = 0.3f;
		
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
		
		if(child != null && this == world.localPlayer)
		{
			child.xPosition = xPosition + EntityArm.offsetX;
			child.yPosition = yPosition + EntityArm.offsetY;
		}
		
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
	
	public final void render(Camera camera, int windowWidth, int windowHeight)
	{
		updateRotation();
		super.render(camera, windowWidth, windowHeight);
		if(child != null) child.render(camera, windowWidth, windowHeight);
	}
	
	public abstract void updateRotation();
	
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