package cavewars;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class Packet2SpawnPlayer extends Packet
{	
	public static final int packetID = 2;
	
	public int entityID;
	public int childEntityID;
	public int team;
	public float x;
	public float y;
	
	public Packet2SpawnPlayer(EntityPlayer entityPlayer)
	{
		this.entityID = entityPlayer.entityID;
		//this.childEntityID = entityPlayer.child.entityID;
		this.team = entityPlayer.team;
		this.x = entityPlayer.xPosition;
		this.y = entityPlayer.yPosition;
	}
	
	public Packet2SpawnPlayer()
	{
		
	}

	@Override
	public void read(DataInputStream instream) throws IOException
	{
		entityID = instream.readInt();
		//childEntityID = instream.readInt();
		team = instream.readInt();
		x = instream.readFloat();
		y = instream.readFloat();
	}

	@Override
	public void write(DataOutputStream outstream) throws IOException
	{
		outstream.writeInt(entityID);
		//outstream.writeInt(childEntityID);
		outstream.writeInt(team);
		outstream.writeFloat(x);
		outstream.writeFloat(y);
	}

	@Override
	public int getID()
	{
		return packetID;
	}
}
