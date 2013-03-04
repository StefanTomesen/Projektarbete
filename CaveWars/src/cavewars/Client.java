package cavewars;

import java.io.IOException;
import java.net.*;
import org.newdawn.slick.SlickException;


public class Client
{
	public World world;
	
	PacketCentral serverConnection;
	ClientPacketProcessor clientPacketProcessor;
	
	public Client(String ipAdress, int port) throws Exception
	{
		Socket socket = new Socket(ipAdress, port);
		serverConnection = new PacketCentral(socket);
		serverConnection.startListening();
		
		world = new World();
		clientPacketProcessor = new ClientPacketProcessor(world);
		
		serverConnection.sendPacket(new Packet0Login(SubMenu.chosenTeam));
	}
	
	public void update()
	{
		if(world.localPlayer != null)
		{
			serverConnection.sendPacket(new Packet7UpdatePlayerData(world.localPlayer));
		}
		Packet packet;
		while((packet = serverConnection.inList.poll()) != null)
		{
			try
			{
				clientPacketProcessor.process(serverConnection, packet);
			} 
			catch (SlickException ex)
			{
				System.out.println("Packet process failure!");
				ex.printStackTrace();
			}
		}
	}
}
