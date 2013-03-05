package cavewars;

import java.io.IOException;
import java.net.*;
import java.util.logging.*;
import org.newdawn.slick.SlickException;


public class Client
{
	public World world;
	
	PacketCentral packetCentral;
	ClientPacketProcessor clientPacketProcessor;
	
	public Client(Socket socket) throws Exception
	{
		packetCentral = new PacketCentral(socket);
		packetCentral.startListening();
		world = new World();
		clientPacketProcessor = new ClientPacketProcessor(world);
		
		packetCentral.sendPacket(new Packet0Login(ColMenu.chosenTeam));
	}
	
	public void update()
	{
		if(world.localPlayer != null)
		{
			packetCentral.sendPacket(new Packet7UpdatePlayerData(world.localPlayer));
		}
		Packet packet;
		while((packet = packetCentral.inList.poll()) != null)
		{
			clientPacketProcessor.process(packetCentral, packet);
		}
	}
	
	public void stop()
	{
		try
		{
			packetCentral.disconnect();
		} 
		catch (IOException ex) {}
		
	}
}
