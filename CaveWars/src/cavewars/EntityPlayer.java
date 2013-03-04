package cavewars;

import org.newdawn.slick.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class EntityPlayer extends Entity
{
	public static final int RED_TEAM = 0;
	public static final int YELLOW_TEAM = 1;
	
	private static final String RED_TEAM_IMAGE = "spritesheet_player_red.png";
	private static final String YELLOW_TEAM_IMAGE = "spritesheet_player_yellow.png";
	
	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	
	public static final int animationFPS = 100; //Milliseconds
	public static final float speed = 5.0F; // m/s
	
	public int team;
	
	/** Current animated direction */
	public int direction = RIGHT;
	
	/** Whether the player is standing on the ground. This determines if it is allowed to jump. */
	public boolean onGround = false;
	/** Whether the player is touching a ladder. */
	public boolean onLadder = false;
	
	/** While climbing, the gravity is disabled. */
	public boolean isClimbing = false;
	
	public EntityPlayer(int id, float x, float y, int team)
	{
		super(id, x, y, 1.5F, ((team == RED_TEAM) ? RED_TEAM_IMAGE : YELLOW_TEAM_IMAGE), 4, 2);
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
	 * Alters the current vertical velocity so that the player jumps.
	 */
	public void jump()
	{
		if(onGround)
		{
			velocityY = -5; // Negative values point up.
		}
	}
	
	public void climb()
	{
		if(onLadder)
		{
			isClimbing = true;
			velocityY = -3.0F;
		}
	}
	
	public void pauseClimbing()
	{
		if(isClimbing)
		{
			velocityY = 0;
		}
	}
	
	public void dropFromLadder()
	{
		if(isClimbing)
		{
			isClimbing = false;
			velocityY = 0;
		}
	}

	@Override
	public float getVerticalAcceleration()
	{
		updateClimbingState();
		if(isClimbing)
		{
			return 0F;
		}
		return gravity;
	}
	
	public void updateClimbingState()
	{
		if(!onLadder)
		{
			isClimbing = false;
		}
	}
	
	@Override
	public void hasCollided(int id, boolean onGround)
	{
		if(id == Tile.LADDER_ID)
		{
			onLadder = true;
		}
		if(id == Tile.WATER_ID1 || id == Tile.WATER_ID2)
		{
			//CaveWars.caveWars.enterState(CaveWars.MAIN_MENU_STATE);
		}
		this.onGround = onGround;
	}

	@Override
	public void resetCollisionStatus()
	{
		onGround = false;
		onLadder = false;
	}

	public void updateMovement(boolean leftPressed, boolean rightPressed)
	{
		velocityX = 0;
		if(leftPressed && !rightPressed)
		{
			velocityX = -speed;
			direction = LEFT;
		}
		if(rightPressed && !leftPressed)
		{
			velocityX = speed;
			direction = RIGHT;
		}
	}
}
