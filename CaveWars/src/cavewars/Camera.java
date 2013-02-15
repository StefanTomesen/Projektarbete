package cavewars;

public class Camera
{
	public float x;
	public float y;
	/** The size of a single tile compared to the window height. */
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
	public void updatePositon(EntityPlayer player)
	{
		x = player.xPosition;
		y = player.yPosition;
	}
	
	public void zoom(float multiplier)
	{
		scale *= multiplier;
	}
}
