package cavewars;

import java.io.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class Packet9SpawnTileEntity extends Packet
{
	public static final int packetID = 9;
	
	public int entityID;
	public int tileID;
	public float x;
	public float y;
	
	public Packet9SpawnTileEntity(EntityTile tile)
	{
		this.entityID = tile.entityID;
		this.tileID = tile.tileID;
		this.x = tile.xPosition;
		this.y = tile.yPosition;
	}
	
	public Packet9SpawnTileEntity()
	{
		
	}

	@Override
	public void read(DataInputStream instream) throws IOException
	{
		entityID = instream.readInt();
		tileID = instream.readInt();
		x = instream.readFloat();
		y = instream.readFloat();
	}

	@Override
	public void write(DataOutputStream outstream) throws IOException
	{
		outstream.writeInt(entityID);
		outstream.writeInt(tileID);
		outstream.writeFloat(x);
		outstream.writeFloat(y);
	}

	@Override
	public int getID()
	{
		return packetID;
	}
}
