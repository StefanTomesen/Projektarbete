package cavewars;

import java.io.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class Packet7UpdatePlayerData extends Packet
{
	public static final int packetID = 7;
	
	public int entityID;
	public int direction;
	
	public float xPosition;
	public float yPosition;
	public float xVelocity;
	public float yVelocity;
	
	public Packet7UpdatePlayerData(EntityPlayer player)
	{
		this.entityID = player.entityID;
		this.direction = player.direction;
		this.xPosition = player.xPosition;
		this.yPosition = player.yPosition;
		this.xVelocity = player.velocityX;
		this.yVelocity = player.velocityY;
	}
	
	public Packet7UpdatePlayerData()
	{
		
	}

	@Override
	public void read(DataInputStream instream) throws IOException
	{
		entityID = instream.readInt();
		direction = instream.readInt();
		xPosition = instream.readFloat();
		yPosition = instream.readFloat();
		xVelocity = instream.readFloat();
		yVelocity = instream.readFloat();
	}

	@Override
	public void write(DataOutputStream outstream) throws IOException
	{
		outstream.writeInt(entityID);
		outstream.writeInt(direction);
		outstream.writeFloat(xPosition);
		outstream.writeFloat(yPosition);
		outstream.writeFloat(xVelocity);
		outstream.writeFloat(yVelocity);
	}

	@Override
	public int getID()
	{
		return packetID;
	}
	
}
