package cavewars.client;

import java.awt.Image;

public class EntityPlayer extends Entity
{
	public EntityPlayer(int x, int y, String id)
	{
		super(x, y, id);
	}
	
	@Override
	public Image getImage() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
