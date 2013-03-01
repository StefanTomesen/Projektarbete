package cavewars;

public class Tile extends Renderable
{
	public static final int NUMBER_OF_TILES = 4;
	public static final int LADDER_ID = 2;
	
	public int id;
	
	public Tile(int id, int xPos, int yPos)
	{
		super(xPos, yPos, 1.0F, "Tiles/Brevid.png", NUMBER_OF_TILES, 1);
		
		this.id = id;
	}

	@Override
	public int getSpriteXIndex()
	{
		return id - 1;
	}

	@Override
	public int getSpriteYIndex()
	{
		return 0;
	}

	@Override
	public void updateAnimation(long delta){}
	
	public boolean isSolid()
	{
		return isSolid(id);
	}
	
	public static boolean isSolid(int id)
	{
		if(id == LADDER_ID) return false;
		
		return true;
	}
	
}
