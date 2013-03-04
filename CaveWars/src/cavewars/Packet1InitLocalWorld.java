package cavewars;

import java.io.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class Packet1InitLocalWorld extends Packet
{
	public static final int packetID = 1;
	
	public int tileWidth;
	public int tileHeight;
	
	public Packet1InitLocalWorld(TileGrid tileGrid)
	{
		tileWidth = tileGrid.xSize;
		tileHeight = tileGrid.ySize;
	}
	
	public Packet1InitLocalWorld()
	{
		
	}
	
	
	@Override
	public void read(DataInputStream instream) throws IOException
	{
		tileWidth = instream.readInt();
		tileHeight = instream.readInt();
	}

	@Override
	public void write(DataOutputStream outstream) throws IOException
	{
		outstream.writeInt(tileWidth);
		outstream.writeInt(tileHeight);
	}

	@Override
	public int getID()
	{
		return packetID;
	}
	
}
