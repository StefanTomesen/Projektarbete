package cavewars.client;

import java.awt.Image;

public abstract class Entity
{
	public String entityID;
	
	public int x;
	public int y;
	
	public Image image;
	
	public Entity(int x, int y, String entityID)
	{
		this.x = x;
		this.y = y;
	}
	
	public abstract Image getImage();
}
