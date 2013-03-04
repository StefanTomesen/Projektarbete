package cavewars;

import java.io.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class Packet3SetLocalPlayer extends Packet
{
	public static final int packetID = 3;
	
	public int entityID;

	public Packet3SetLocalPlayer(int entityID)
	{
		this.entityID = entityID;
	}
	
	public Packet3SetLocalPlayer()
	{
		
	}
	
	@Override
	public void read(DataInputStream instream) throws IOException
	{
		entityID = instream.readInt();
	}

	@Override
	public void write(DataOutputStream outstream) throws IOException
	{
		outstream.writeInt(entityID);
	}

	@Override
	public int getID()
	{
		return packetID;
	}
}
