package cavewars;

import org.newdawn.slick.*;

public class EntityPlayer extends Entity
{
	public static final String RED_TEAM;
	public static final String YELLOW_TEAM;
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	public EntityPlayer(int id, float x, float y, SpriteSheet spriteSheet)
	{
		super(id, x, y, 1.0F, spriteSheet);
		animationFPS = 350;
	}
	
	public void updateAnimation(long delta)
	{
		animationTimer += delta;
	}
	
	public int getSpriteX()
	{
		return 
	}
}
