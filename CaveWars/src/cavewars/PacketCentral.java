package cavewars;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.logging.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class PacketCentral implements Runnable
{
	public boolean running = true;
	
	public DataInputStream instream;
	public DataOutputStream outstream;
	
	public BlockingQueue<Packet> inList =  new LinkedBlockingQueue();
	
	public EntityPlayer player;

	public PacketCentral(Socket socket) throws IOException
	{
		instream = new DataInputStream(socket.getInputStream());
		outstream = new DataOutputStream(socket.getOutputStream());
	}
	
	public void startListening()
	{
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void stop()
	{
		running = false;
		try
		{
			instream.close();
			outstream.close();
		} catch (IOException ex)
		{
			System.out.println("Failed to close PacketCentral in/out stream");
			ex.printStackTrace();
		}
		
	}
	
		@Override
	public void run()
	{
		while(running)
		{
			try
			{
				recievePacket();
			}
			catch(InterruptedException ie)
			{
				ie.printStackTrace();
			}
		}
		System.out.println("PacketCentral Shutdown");
	}
		
	public void recievePacket() throws InterruptedException
	{
		try
		{
			int packetID = instream.readInt();
			//System.out.println("Recieved packet: " + packetID);
			Packet packet = Packet.createEmptyPacket(packetID);
			
			packet.read(instream);
			inList.put(packet);
			//System.out.println("Finished reading packet");
		} 
		catch (IOException ex)
		{ 
			System.out.println("Packet Error");
			ex.printStackTrace();
		}
	}
		
	public void sendPacket(Packet packet)
	{
		//System.out.println("Sending packet: " + packet.getID());
		try
		{
			outstream.writeInt(packet.getID());
			packet.write(outstream);
		}
		catch(IOException ioe)
		{
			System.out.println("Failed to send packet with id: " + packet.getID());
		}
	}
}
