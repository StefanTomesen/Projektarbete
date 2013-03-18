package cavewars;

import com.sun.org.apache.xpath.internal.axes.ChildIterator;
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
			case Packet6UpdateEntityData.packetID:
			{
				processPacket6UpdateEntityData(packetCentral, (Packet6UpdateEntityData)packet);
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
			case Packet9SpawnTileEntity.packetID:
			{
				processPacket9SpawnTileEntity(packetCentral, (Packet9SpawnTileEntity)packet);
				return;
			}
			case Packet10RemoveEntity.packetID:
			{
				processPacket10RemoveEntity(packetCentral, (Packet10RemoveEntity)packet);
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
		EntityPlayer player = new EntityPlayer(world, packet2SpawnPlayer.entityID, packet2SpawnPlayer.childEntityID, packet2SpawnPlayer.x, packet2SpawnPlayer.y, packet2SpawnPlayer.team);
		world.playerList.add(player);
		world.entityList.add(player);
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
	
	private void processPacket6UpdateEntityData(PacketCentral packetCentral, Packet6UpdateEntityData packet6UpdateEntityData)
	{
		Entity player = world.getEntity(packet6UpdateEntityData.entityID);
		player.xPosition = packet6UpdateEntityData.xPosition;
		player.yPosition = packet6UpdateEntityData.yPosition;
		player.velocityX = packet6UpdateEntityData.xVelocity;
		player.velocityY = packet6UpdateEntityData.yVelocity;
	}
	
	private void processPacket7UpdatePlayerData(PacketCentral packetCentral, Packet7UpdatePlayerData packet7UpdatePlayerData)
	{
		EntityPlayer player = world.getPlayer(packet7UpdatePlayerData.entityID);
		player.direction = packet7UpdatePlayerData.direction;
		player.child.rotation = packet7UpdatePlayerData.armRotation;
		player.xPosition = packet7UpdatePlayerData.xPosition;
		player.yPosition = packet7UpdatePlayerData.yPosition;
		player.child.xPosition = packet7UpdatePlayerData.xPosition + EntityArm.offsetX;
		player.child.yPosition = packet7UpdatePlayerData.yPosition + EntityArm.offsetY;
		player.velocityX = packet7UpdatePlayerData.xVelocity;
		player.velocityY = packet7UpdatePlayerData.yVelocity;
	}

	private void processPacketPacket8PlayerDisconnected(PacketCentral packetCentral, Packet8PlayerDisconnected packet8PlayerDisconnected)
	{
		EntityPlayer player = world.getPlayer(packet8PlayerDisconnected.entityID);
		world.removePlayer(player);
	}
	
	private void processPacket9SpawnTileEntity(PacketCentral packetCentral, Packet9SpawnTileEntity packet9SpawnTileEntity)
	{
		EntityTile tileEntity = new EntityTile(world, packet9SpawnTileEntity.entityID, packet9SpawnTileEntity.x, packet9SpawnTileEntity.y, packet9SpawnTileEntity.tileID);
		world.entityList.add(tileEntity);
	}
	
	private void processPacket10RemoveEntity(PacketCentral packetCentral, Packet10RemoveEntity packet10RemoveEntity)
	{
		Entity entity = world.getEntity(packet10RemoveEntity.entityID);
		world.removeEntity(entity);
	}
}
