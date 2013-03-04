package cavewars;

import java.io.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public abstract class Packet
{	
	public abstract void read(DataInputStream instream) throws IOException;
	
	public abstract void write(DataOutputStream outstream) throws IOException;
	
	public abstract int getID();
	
	public static Packet createEmptyPacket(int id)
	{
		switch(id)
		{
			case Packet0Login.packetID: 
				return new Packet0Login();
			case Packet1InitLocalWorld.packetID: 
				return new Packet1InitLocalWorld();
			case Packet2SpawnPlayer.packetID: 
				return new Packet2SpawnPlayer();
			case Packet3SetLocalPlayer.packetID: 
				return new Packet3SetLocalPlayer();
			case Packet4AddTile.packetID: 
				return new Packet4AddTile();
			case Packet5RemoveTile.packetID: 
				return new Packet5RemoveTile();
			case Packet7UpdatePlayerData.packetID: 
				return new Packet7UpdatePlayerData();
		}
		
		throw new RuntimeException("Unknown packet");
	}
}
