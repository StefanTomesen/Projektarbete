package cavewars;

public class Tile extends Renderable
{
	public int id;
	public int xPos;
	public int yPos;
	
	public Tile(int id, int xPos, int yPos)
	{
		super(xPos, yPos, 1.0F, "Tiles/Brevid.png", 4, 1);
		
		this.id = id;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	@Override
	public int getSpriteXIndex()
	{
		return 0;
	}

	@Override
	public int getSpriteYIndex()
	{
		return 0;
	}

	@Override
	public void updateAnimation(long delta){}
	
}
