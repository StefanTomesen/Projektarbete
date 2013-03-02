package cavewars;

public class Tile extends Renderable
{
	public static int numberOfTileColumns;
	public static int numberOfTileRows;
	
	public static final int NUMBER_OF_ACTIVE_TILES = 4;
	
	public static int LADDER_ID = 2;
	public static int WATER_ID1 = 3;
	public static int WATER_ID2 = 4;
	
	public int id;
	
	public Tile(int id, int xPos, int yPos)
	{
		super(xPos, yPos, 1.0F, "Tiles/Brevid.png", numberOfTileColumns, numberOfTileRows);
		
		this.id = id;
	}

	@Override
	public int getSpriteXIndex()
	{
		return getColumn(id);
	}

	@Override
	public int getSpriteYIndex()
	{
		return getRow(id);
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
	
	private static int getColumn(int id)
	{
		id -= 1;
		return id % numberOfTileColumns;
	}
	
	private static int getRow(int id)
	{
		id -= 1;
		return id / numberOfTileColumns;
	}
	
	public static int getNumberOfTiles()
	{
		return NUMBER_OF_ACTIVE_TILES;
	}
	
}
