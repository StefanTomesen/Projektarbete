package cavewars;

public class TileLoader
{
	String mapFilePath = "......";
	
	public TileGrid loadTiles() // Skapade grunden för TileLoadern. Du får göra färdigt den. Behöver den rätt snart, annars måste jag göra den själv...
	{
		// Ladda in tmxfilen med tiles.
		
		// Loopa igenom alla tiles och skapa nya Tile objekt om det finns en tile på den specifika
		// platsen. Tile objektet initieras med endast ett id som representerar dess blocktyp.
		// Tilesen placeras alla in i ett TileGrid objekt som sedan returneras.
		TileGrid tileGrid = new TileGrid(0/* antal tiles i x-led*/,0/* antal tiles i y-led*/);
		
		for(int x = 0; x < tileGrid.xSize; x++)
		{
			for(int y = 0; y < tileGrid.ySize; y++)
			{
				// TODO: Överför tiledatan till nya tileobjekt i tilegriden.
			}
		}
		
		return tileGrid;
	}
}
