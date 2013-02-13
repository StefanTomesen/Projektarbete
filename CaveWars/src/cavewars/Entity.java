package cavewars;

import org.newdawn.slick.Renderable;

public abstract class Entity
{
	public int entityID;
	
	public float xPosition;
	public float yPosition;
	public float rotation;
	
	public Renderable renderable;
	
	public Entity(int entityID, float xPosition, float yPosition, Renderable renderable)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
}
