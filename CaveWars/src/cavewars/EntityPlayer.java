package cavewars;

import org.newdawn.slick.*;

public class EntityPlayer extends Entity
{
	public static final String RED_TEAM = "spritesheet_player_red.png";
	public static final String YELLOW_TEAM = "spritesheet_player_yellow.png";
	
	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	
	public static final int animationFPS = 300; //Milliseconds
	public static final float speed = 10.0F; // m/s
	
	/** Whether the entity is standing in the ground. This determines if it is allowed to jump. */
	public boolean onGround = false;
	
	public int direction = RIGHT;
	
	public EntityPlayer(int id, float x, float y, String filName, int xTiles, int yTiles)
	{
		super(id, x, y, 1.0F, filName, xTiles, yTiles);
	}
	
	@Override
	public float getRotation()
	{
		return 0;
	}
	
	@Override
	public void updateAnimation(long delta)
	{
		animationTimer += delta;
	}
	
	@Override
	public int getSpriteXIndex()
	{
		if(Math.abs(velocityX) > 0)
		{
			return (int)(animationTimer / animationFPS) % 4;
		}
		else
		{
			return 0;
		}
	}
	
	@Override
	public int getSpriteYIndex()
	{
		if(direction == LEFT)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	/**
	 * Sets the player moving either to the left or right. This also shows in the animation.
	 * @param movementDirection 
	 */
	public void doWalk(int movementDirection)
	{
		direction = movementDirection;
		velocityX = movementDirection * speed;
	}
	
	/** 
	 * Sets the velocity in the x axis to 0 while leaving the direction untouched.
	 */
	public void doStop()
	{
		velocityX = 0;
	}
}
