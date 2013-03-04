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
	
	public void process(PacketCentral packetCentral, Packet packet) throws SlickException
	{
		System.out.println("Process packet: " + packet.getID());
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
		}
		
		System.out.println("Client - Packet Not Processed: " + packet.getID());
	}
	
	private void processPacket1InitLocalWorld(PacketCentral packetCentral, Packet1InitLocalWorld packet1InitLocalWorld) throws SlickException
	{
		world.clientInit(packet1InitLocalWorld.tileWidth, packet1InitLocalWorld.tileHeight);
	}

	private void processPacket2SpawnPlayer(PacketCentral packetCentral, Packet2SpawnPlayer packet2SpawnPlayer) throws SlickException
	{
		EntityPlayer player = new EntityPlayer(packet2SpawnPlayer.entityID, packet2SpawnPlayer.x, packet2SpawnPlayer.y, packet2SpawnPlayer.team);
		world.playerList.add(player);
	}
	
	private void processPacket3SetLocalPlayer(PacketCentral packetCentral, Packet3SetLocalPlayer packet3SetLocalPlayer) throws SlickException
	{
		world.localPlayer = world.getPlayer(packet3SetLocalPlayer.entityID);
	}
	
	private void processPacket4AddTile(PacketCentral packetCentral, Packet4AddTile packet4AddTile)
	{
		world.tileGrid.add(packet4AddTile.x, packet4AddTile.y, new Tile(packet4AddTile.id, packet4AddTile.x, packet4AddTile.y));
	}
	
	private void processPacket5RemoveTile(PacketCentral packetCentral, Packet5RemoveTile packet5RemoveTile)
	{
		world.tileGrid.remove(packet5RemoveTile.x, packet5RemoveTile.y);
	}
	
	private void processPacket7UpdatePlayerData(PacketCentral packetCentral, Packet7UpdatePlayerData packet7UpdatePlayerData)
	{
		EntityPlayer player = world.getPlayer(packet7UpdatePlayerData.entityID);
		player.direction = packet7UpdatePlayerData.direction;
		player.xPosition = packet7UpdatePlayerData.xPosition;
		player.yPosition = packet7UpdatePlayerData.yPosition;
		player.velocityX = packet7UpdatePlayerData.xVelocity;
		player.velocityY = packet7UpdatePlayerData.yVelocity;
	}
}
