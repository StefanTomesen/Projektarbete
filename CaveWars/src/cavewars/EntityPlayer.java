package cavewars;

import java.awt.Image;
import org.newdawn.slick.Renderable;

public class EntityPlayer extends Entity
{
	public static final int RED_TEAM = 0;
	public static final int YELLOW_TEAM = 1;
	
	public EntityPlayer(int id, float x, float y, Renderable renderable)
	{
		super(id, x, y, renderable);
	}
}
