package cavewars;

import java.io.*;

public class Packet8PlayerDisconnected extends Packet
{
	public static final int packetID = 8;
	
	public int entityID;
	
	public Packet8PlayerDisconnected(int entityID)
	{
		this.entityID = entityID;
	}
	
	public Packet8PlayerDisconnected()
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