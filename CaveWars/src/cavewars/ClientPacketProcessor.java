package cavewars;

import org.newdawn.slick.SlickException;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class ClientPacketProcessor
{
	World world;
	
	public ClientPacketProcessor(World world)
	{
		this.world = world;
	}
	
	public void process(PacketCentral packetCentral, Packet packet)
	{
		//System.out.println("Process packet: " + packet.getID());
		switch(packet.getID())
		{
			case Packet1InitLocalWorld.packetID:	
			{
				processPacket1InitLocalWorld(packetCentral, (Packet1InitLocalWorld)packet);
				return;
			}
			case Packet2SpawnPlayer.packetID:
			{
				processPacket2SpawnPlayer(packetCentral, (Packet2SpawnPlayer)packet);
				return;
			}
			case Packet3SetLocalPlayer.packetID:
			{
				processPacket3SetLocalPlayer(packetCentral, (Packet3SetLocalPlayer)packet);
				return;
			}
			case Packet4AddTile.packetID:
			{
				processPacket4AddTile(packetCentral, (Packet4AddTile)packet);
				return;
			}
			case Packet5RemoveTile.packetID:
			{
				processPacket5RemoveTile(packetCentral, (Packet5RemoveTile)packet);
				return;
			}
			case Packet7UpdatePlayerData.packetID:
			{
				processPacket7UpdatePlayerData(packetCentral, (Packet7UpdatePlayerData)packet);
				return;
			}
			case Packet8PlayerDisconnected.packetID:
			{
				processPacketPacket8PlayerDisconnected(packetCentral, (Packet8PlayerDisconnected)packet);
				return;
			}
		}
		
		System.out.println("Client - Packet Not Processed: " + packet.getID());
	}
	
	private void processPacket1InitLocalWorld(PacketCentral packetCentral, Packet1InitLocalWorld packet1InitLocalWorld)
	{
		world.clientInit(packet1InitLocalWorld.tileWidth, packet1InitLocalWorld.tileHeight);
	}

	private void processPacket2SpawnPlayer(PacketCentral packetCentral, Packet2SpawnPlayer packet2SpawnPlayer)
	{
		EntityPlayer player = new EntityPlayer(packet2SpawnPlayer.entityID, packet2SpawnPlayer.x, packet2SpawnPlayer.y, packet2SpawnPlayer.team);
		world.playerList.add(player);
	}
	
	private void processPacket3SetLocalPlayer(PacketCentral packetCentral, Packet3SetLocalPlayer packet3SetLocalPlayer)
	{
		world.localPlayer = world.getPlayer(packet3SetLocalPlayer.entityID);
	}
	
	private void processPacket4AddTile(PacketCentral packetCentral, Packet4AddTile packet4AddTile)
	{
		Tile tile = new Tile(packet4AddTile.id, packet4AddTile.x, packet4AddTile.y);
		world.tileGrid.add(packet4AddTile.x, packet4AddTile.y, tile);
	}
	
	private void processPacket5RemoveTile(PacketCentral packetCentral, Packet5RemoveTile packet5RemoveTile)
	{
		world.tileGrid.remove(packet5RemoveTile.x, packet5RemoveTile.y);
	}
	
	private void processPacket7UpdatePlayerData(PacketCentral packetCentral, Packet7UpdatePlayerData packet7UpdatePlayerData)
	{
		System.out.println("packet7UpdatePlayerData:" + ((packet7UpdatePlayerData == null) ? "null" : packet7UpdatePlayerData));
		System.out.println("direction:" + packet7UpdatePlayerData.direction);
		EntityPlayer player = world.getPlayer(packet7UpdatePlayerData.entityID);
		player.direction = packet7UpdatePlayerData.direction;
		player.xPosition = packet7UpdatePlayerData.xPosition;
		player.yPosition = packet7UpdatePlayerData.yPosition;
		player.velocityX = packet7UpdatePlayerData.xVelocity;
		player.velocityY = packet7UpdatePlayerData.yVelocity;
	}

	private void processPacketPacket8PlayerDisconnected(PacketCentral packetCentral, Packet8PlayerDisconnected packet8PlayerDisconnected)
	{
		world.removePlayer(packet8PlayerDisconnected.entityID);
	}
}
