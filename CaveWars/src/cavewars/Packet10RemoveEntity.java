package cavewars;

import java.io.*;

public class Packet10RemoveEntity extends Packet
{
	public static final int packetID = 10;
	
	public int entityID;
	
	public Packet10RemoveEntity(int entityID)
	{
		this.entityID = entityID;
	}
	
	public Packet10RemoveEntity()
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
