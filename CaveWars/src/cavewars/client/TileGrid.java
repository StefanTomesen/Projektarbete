package cavewars.client;

public class TileGrid
{
	/** The number of horizontal tiles in the grid. */
	public final int xSize;
	/** The number of vertical tiles in the grid. */
	public final int ySize;
	
	/** The grid holds all the tiles in the map. This array can be manipulated to
	 *  add or remove tiles. Coordinates are given by [x][y].*/
	private Tile[][] grid;
	
	/**
	 * Initializes the tile grid with a fixed size and 
	 * @param x The number of tiles horizontally across the grid.
	 * @param y The number of tiles vertically across the grid.
	 */
	public TileGrid(int x, int y)
	{
		xSize = x;
		ySize = y;
		
		grid = new Tile[x][y];
	}
	
	/**
	 * Adds a tile at the specified slot in the tile grid. Will later also
	 * call an event that tells the server what has happened.
	 * 
	 * @param x The position of the slot along the x axis.
	 * @param y The position of the slot along the y axis.
	 * @param tile The tile that is being added.
	 */
	public void add(int x, int y, Tile tile)
	{
		grid[x][y] = tile;
	}
	
	/**
	 * Clears the specified slot in the grid.
	 * 
	 * @param x The position of the slot along the x axis.
	 * @param y The position of the slot along the y axis.
	 */
	public void remove(int x, int y)
	{
		grid[x][y] = null;
	}
	
	/**
	 * Gets a tile from the specified slot in the grid.
	 * 
	 * @param x The position of the slot along the x axis.
	 * @param y The position of the slot along the y axis.
	 * @return The tile located in the specified slot or null if none exists.
	 */
	public Tile get(int x, int y)
	{
		return grid[x][y];
	}
	
	/**
	 * Takes out the tile at the specified slot from the list. While being removed
	 * from the grid, it is also being returned for use elsewhere. Practically,
	 * this is used when converting tiles to entities.
	 * 
	 * @param x The position of the slot along the x axis.
	 * @param y The position of the slot along the y axis.
	 * @return The tile that until now was in the slot.
	 */
	public Tile pop(int x, int y)
	{
		Tile tile = get(x,y);
		remove(x,y);
		
		return tile;
	}
	
	/**
	 * Checks whether the specified slot contains a tile.
	 * 
	 * @param x The position of the slot along the x axis.
	 * @param y The position of the slot along the y axis.
	 * @return Whether the slot is occupied or not.
	 */
	public boolean isOccupied(int x, int y)
	{
		return grid[x][y] == null;
	}
}
