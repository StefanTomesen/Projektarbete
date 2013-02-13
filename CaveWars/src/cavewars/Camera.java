package cavewars;

public class Camera
{
	public float x;
	public float y;
	/** The number of tiles that can vertically fit the screen. */
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
	public void update(EntityPlayer player)
	{
		x = player.xPosition;
		y = player.yPosition;
	}
	
	
	public float getPhysicalXPosition(int windowWidth, int windowHeight, int gridWidth, int gridHeight)
	{
		return 0;
	}
}
