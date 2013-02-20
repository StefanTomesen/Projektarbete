package cavewars;

public class Camera
{
	public float x;
	public float y;
	/** The number of tiles visible vertically across the screen. */
	public float scale;
	
	public Camera(float x, float y, float scale)
	{
		this.x = x;
		this.y = y;
		this.scale = scale;
	}
	
	/**
	 * Updates the camera position based on where the player is.
	 */
	public void updatePositon(World world, EntityPlayer player)
	{
		float scale = world.camera.scale / 3;
		
		if(player.xPosition - scale > x)
		{
			x = player.xPosition - scale;
		}
		if(player.xPosition + scale < x)
		{
			x = player.xPosition + scale;
		}
		
		if(player.yPosition - scale > y)
		{
			y = player.yPosition - scale;
		}
		if(player.yPosition + scale < y)
		{
			y = player.yPosition + scale;
		}
	}
	
	public void zoom(float multiplier)
	{
		scale *= multiplier;
	}
}
