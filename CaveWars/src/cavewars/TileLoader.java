package cavewars;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.*;

/**
 * @author Mattias Stenqvist, 3B Portalens Gymnasium
 */
public class TileLoader
{
	public static TileGrid loadTiles() throws SlickException
	{
		// Ladda in tmxfilen med tiles.
		TiledMap karta = new TiledMap("resources/karta_2_1.tmx");
		
		// Loopa igenom alla tiles och skapa nya Tile objekt om det finns en tile på den specifika
		// platsen. Tile objektet initieras med endast ett id som representerar dess blocktyp.
		// Tilesen placeras alla in i ett TileGrid objekt som sedan returneras.
		TileGrid tileGrid = new TileGrid(karta.getWidth(), karta.getHeight());
		
		
		for(int x = 0; x < tileGrid.xSize; x++)
		{
			for(int y = 0; y < tileGrid.ySize; y++)
			{
				Tile tile = new Tile(karta.getTileId(x, y, 0), x, y);
				if(karta.getTileId(x, y, 0) != 0)
				{
					tileGrid.add(x, y, tile);	
				}
			}
		}
		return tileGrid;
	}
	
	public static void initTileSet() throws SlickException
	{
		System.out.println("init");
		// Ladda in tmxfilen med tiles.
		TiledMap karta = new TiledMap("resources/karta_2_1.tmx");
		TileSet tileset = karta.getTileSet(0);
		
		Tile.numberOfTileColumns = tileset.tilesAcross;
		Tile.numberOfTileRows = tileset.tilesDown;
	}
}