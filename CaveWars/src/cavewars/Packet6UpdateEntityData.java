package cavewars;

import java.io.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class Packet6UpdateEntityData extends Packet
{
	public static final int packetID = 6;
	
	public int entityID;
	
	public float xPosition;
	public float yPosition;
	public float xVelocity;
	public float yVelocity;
	
	public Packet6UpdateEntityData(Entity entity)
	{
		this.entityID = entity.entityID;
		this.xPosition = entity.xPosition;
		this.yPosition = entity.yPosition;
		this.xVelocity = entity.velocityX;
		this.yVelocity = entity.velocityY;
	}
	
	public Packet6UpdateEntityData()
	{
		
	}

	@Override
	public void read(DataInputStream instream) throws IOException
	{
		entityID = instream.readInt();
		xPosition = instream.readFloat();
		yPosition = instream.readFloat();
		xVelocity = instream.readFloat();
		yVelocity = instream.readFloat();
	}

	@Override
	public void write(DataOutputStream outstream) throws IOException
	{
		outstream.writeInt(entityID);
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
