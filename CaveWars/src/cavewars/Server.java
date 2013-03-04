package cavewars;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.*;
import org.newdawn.slick.SlickException;

public class Server implements Runnable
{	
	public boolean running = true;
	
	public ClientListener clientListener;
	public ArrayList<PacketCentral> connections = new ArrayList();
	public ServerPacketProcessor serverPacketProcessor;
	
	public World world;
	
	public int nextEntityID = 0;
	
	public Server(int port) throws SlickException
	{
		world = new World();
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
			
			// Send updates
			for(PacketCentral packetCentral : connections)
			{
				for(EntityPlayer player : world.playerList)
				{
					if(player != packetCentral.player)
					{
						packetCentral.sendPacket(new Packet7UpdatePlayerData(player));
					}
				}
			}
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
		System.out.println("Server Shutdown");
		CaveWars.server = null;
	}
	
	public void start()
	{
		Thread thread = new Thread(this);
		thread.start();
		System.out.println("Server Start");
	}
	
	public void stop()
	{
		running = false;
		System.out.println("Server Stop");
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
}
