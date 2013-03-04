package cavewars;

import java.io.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class Packet4AddTile extends Packet
{
	public static final int packetID = 4;
	
	public int id;
	public int x;
	public int y;
	
	public Packet4AddTile(Tile tile)
	{
		id = tile.id;
		x = (int)tile.xPosition;
		y = (int)tile.yPosition;
	}
	
	public Packet4AddTile()
	{
		
	}
	
	@Override
	public void read(DataInputStream instream) throws IOException
	{
		id = instream.readInt();
		x = instream.readInt();
		y = instream.readInt();
		
	}

	@Override
	public void write(DataOutputStream outstream) throws IOException
	{
		outstream.writeInt(id);
		outstream.writeInt(x);
		outstream.writeInt(y);
	}

	@Override
	public int getID()
	{
		return packetID;
	}
	
}
