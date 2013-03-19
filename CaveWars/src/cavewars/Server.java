package cavewars;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.*;
import org.newdawn.slick.SlickException;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class Server implements Runnable
{	
	public boolean running = true;
	
	public ClientListener clientListener;
	public CopyOnWriteArrayList<PacketCentral> connections = new CopyOnWriteArrayList();
	public ServerPacketProcessor serverPacketProcessor;
	
	public World world;
	
	public int nextEntityID = 0;
	
	public Server(int port) throws SlickException
	{
		world = new World(true);
		world.serverInit();
		
		serverPacketProcessor = new ServerPacketProcessor(this, world);
		
		try
		{
			clientListener = new ClientListener(this, port);
			clientListener.start();
		} 
		catch (IOException ex)
		{
			System.out.println("FAIL");
			ex.printStackTrace();
			running = false;
		}
	}

	@Override
	public void run()
	{
		long lastLoopTime = System.currentTimeMillis();
		
		while(running)
		{
			// Recieve packets
			for(PacketCentral connection : connections)
			{
				Packet packet;
				while((packet = connection.inList.poll()) != null)
				{
					try
					{
						serverPacketProcessor.process(connection, packet);
					} 
					catch (SlickException ex)
					{
						System.out.println("Packet process failure!");
						ex.printStackTrace();
					}
				}
			}
				
			// Update the world - run physics etc.
			int delta = (int)(System.currentTimeMillis() - lastLoopTime);
			lastLoopTime = System.currentTimeMillis();
			
			world.update(delta);
			
			// Check connection statuses
			for(PacketCentral packetCentral : connections)
			{
				if(packetCentral.done)
				{
					connections.remove(packetCentral);
					for(PacketCentral pc : connections)
					{
						pc.sendPacket(new Packet8PlayerDisconnected(packetCentral.player.entityID));
					}
					world.removePlayer(packetCentral.player);
					continue;
				}
				if(!packetCentral.ready)
				{
					continue;
				}
			}
			
			// Send game updates
			for(EntityPlayer player : world.playerList)
			{
				for(PacketCentral packetCentral : connections)
				{
					if(!packetCentral.ready) continue;
					if(player != packetCentral.player)
					{
						packetCentral.sendPacket(new Packet7UpdatePlayerData(player));
					}
				}
			}
			for(Entity entity : world.entityList)
			{
				if(entity.outsideWorld)
				{
					for(PacketCentral packetCentral : connections)
					{
						if(!packetCentral.ready) continue;
						packetCentral.sendPacket(new Packet10RemoveEntity(entity.entityID));
					}
					world.removeEntity(entity);
				}
				if(entity instanceof EntityTile)
				{
					EntityTile entityTile = (EntityTile)entity;
					if(entityTile.hasLanded)
					{
						Tile tile = new Tile(entityTile);
						for(PacketCentral packetCentral : connections)
						{
							if(!packetCentral.ready) continue;
							packetCentral.sendPacket(new Packet10RemoveEntity(entityTile.entityID));
							packetCentral.sendPacket(new Packet4AddTile(tile));
							world.removeEntity(entityTile);
							world.tileGrid.add(tile);
						}
					}
				}
			}
			
			// Wait a little so the server doesn't use up all the CPU
			try
			{
				Thread.sleep(5);
			} 
			catch (InterruptedException ex){}
		}
		clientListener.stop();
		while(clientListener != null)
		{
			try
			{
				Thread.sleep(500);
			} catch (InterruptedException ex){}
		}
		for(PacketCentral pc : connections)
		{
			try
			{
				pc.disconnect();
			} 
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
		CaveWars.server = null;
	}
	
	public void start()
	{
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void stop()
	{
		running = false;
		for(PacketCentral packetCentral : connections)
		{
			try
			{
				packetCentral.disconnect();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	public void newConnection(Socket socket) throws IOException
	{
		PacketCentral packetCentral = new PacketCentral(socket);
		packetCentral.startListening();
		
		connections.add(packetCentral);
	}
	
	/**
	 * Retrieves the current nextEntityID and incrementing the counter.
	 */
	public int getNextEntityID()
	{
		int currentID = nextEntityID;
		nextEntityID++;
		
		return currentID;
	}
	
	/**
	 * Whenever a tile is removed, if the tile above it is of a type that starts falling, it will.
	 */
	public void updateBlocksFalling(int x, int y)
	{
		Tile topTile = world.tileGrid.get(x, y);
		Tile bottomTile = world.tileGrid.get(x, y + 1);
		if(bottomTile == null && topTile != null && topTile.doesFall())
		{
			EntityTile tileEntity = new EntityTile(world, getNextEntityID(), x, y, topTile.id);
			world.tileGrid.remove(x, y);
			world.entityList.add(tileEntity);
			for(PacketCentral pc : connections)
			{
				pc.sendPacket(new Packet5RemoveTile(x, y));
				pc.sendPacket(new Packet9SpawnTileEntity(tileEntity));
			}
			
			updateBlocksFalling(x, y - 1); // Continue with the next block if this fell.
		}
	}
}
