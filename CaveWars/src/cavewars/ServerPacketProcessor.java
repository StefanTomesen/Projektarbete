package cavewars;

import org.newdawn.slick.SlickException;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class ServerPacketProcessor
{	
	Server server;
	World world;
	
	public ServerPacketProcessor(Server server, World world)
	{
		this.world = world;
		this.server = server;
	}
	
	public void process(PacketCentral packetCentral, Packet packet) throws SlickException
	{
		switch(packet.getID())
		{
			case Packet0Login.packetID:	
			{
				processPacket0Login(packetCentral, (Packet0Login)packet);
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
		
		System.out.println("Server - Packet Not Processed: " + packet.getID());
	}
	
	public void processPacket0Login(PacketCentral packetCentral, Packet0Login packet0Login) throws SlickException
	{	
		// Create an empty grid and fill it with tiles
		packetCentral.sendPacket(new Packet1InitLocalWorld(world.tileGrid));
		for(int x = 0; x < world.tileGrid.xSize; x++)
		{
			for(int y = 0; y < world.tileGrid.xSize; y++)
			{
				Tile tile = world.tileGrid.get(x, y);
				System.out.println("Tile: x:" + x + ", y:" + y);
				if(tile != null)
				{
					packetCentral.sendPacket(new Packet4AddTile());
				}
			}
		}
		// Send all the other players
		for(EntityPlayer player : world.playerList)
		{
			packetCentral.sendPacket(new Packet2SpawnPlayer(player));
		}
		// TODO Send all entities 

		// Crete a new player
		int id = server.getNextEntityID();
		EntityPlayer player = world.entityFactory.createPlayer(id, packet0Login.team);

		for(PacketCentral pc : server.connections)
		{
			pc.sendPacket(new Packet2SpawnPlayer(player));
		}
		packetCentral.sendPacket(new Packet3SetLocalPlayer(id));
		
		// Set up the player in the packageCentral, so it can be removed when the player logs out.
		packetCentral.player = player;
	}

	private void processPacket4AddTile(PacketCentral packetCentral, Packet4AddTile packet4AddTile)
	{
		world.tileGrid.add(packet4AddTile.x, packet4AddTile.y, new Tile(packet4AddTile.id, packet4AddTile.x, packet4AddTile.y));
		for(PacketCentral pc : server.connections)
		{
			pc.sendPacket(packet4AddTile);
		}
	}

	private void processPacket5RemoveTile(PacketCentral packetCentral, Packet5RemoveTile packet5RemoveTile)
	{
		world.tileGrid.remove(packet5RemoveTile.x, packet5RemoveTile.y);
		for(PacketCentral pc : server.connections)
		{
			pc.sendPacket(packet5RemoveTile);
		}
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
