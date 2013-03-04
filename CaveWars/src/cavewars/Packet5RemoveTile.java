package cavewars;

import java.io.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class Packet5RemoveTile extends Packet
{
	public static final int packetID = 5;
	
	public int x;
	public int y;
	
	public Packet5RemoveTile(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Packet5RemoveTile()
	{
		
	}

	@Override
	public void read(DataInputStream instream) throws IOException
	{
		x = instream.readInt();
		y = instream.readInt();
	}

	@Override
	public void write(DataOutputStream outstream) throws IOException
	{
		outstream.writeInt(x);
		outstream.writeInt(y);
	}

	@Override
	public int getID()
	{
		return packetID;
	}
}
