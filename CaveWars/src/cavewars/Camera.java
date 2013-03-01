package cavewars;

public class Camera
{
	/** The position along the x axis. */
	public float x;
	/** The position along the y axis. */
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
	public void updatePositon(EntityPlayer player, int levelWidth, int levelHeight)
	{
		float scale = this.scale / 2;
		
		/*float playerHeight = player.spritesheet.getHeight();
		
		if(player.xPosition - scale * 2 >= x)
			x = player.xPosition - scale * 2;
		
		if(player.xPosition + scale * 2 <= x)
			x = player.xPosition + scale * 2;
		
		if(player.yPosition - scale >= y)
			y = player.yPosition - scale;
		
		if(player.yPosition + scale < y)
			y = player.yPosition + scale;
		*/
		x = player.xPosition;
		y = player.yPosition;
	}
	
	public void zoom(float multiplier)
	{
		scale *= multiplier;
	}
}
