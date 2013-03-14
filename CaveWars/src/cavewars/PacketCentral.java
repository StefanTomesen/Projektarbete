package cavewars;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.logging.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class PacketCentral implements Runnable
{
	public boolean done = false;
	
	public Socket socket;
	public DataInputStream instream;
	public DataOutputStream outstream;
	
	public BlockingQueue<Packet> inList = new LinkedBlockingQueue();
	public EntityPlayer player;
	
	/* Whether the client has recieved enough information from the server to recieve normal updates. */
	public boolean ready = false;

	public PacketCentral(Socket socket) throws IOException
	{
		this.socket = socket;
		instream = new DataInputStream(socket.getInputStream());
		outstream = new DataOutputStream(socket.getOutputStream());
	}
	
	public void startListening()
	{
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run()
	{
		try
		{
			try
			{
				while(!done)
				{
					recievePacket();
				}
			}
			finally
			{
				instream.close();
				outstream.close();
				socket.close();
			}
		}
		catch( EOFException oef )
		{
			System.out.println("Connection closed.");
		}
		catch( IOException ioe )
		{
			ioe.printStackTrace();
		}
	}
		
	public void recievePacket() throws IOException
	{
		String action = instream.readUTF();
		if(action.equals("disconnect"))
		{
			done = true;
		}
		if(action.equals("packet"))
		{
			int packetID = instream.readInt();
			if(packetID == Packet5RemoveTile.packetID || packetID == Packet4AddTile.packetID)
			{	//System.out.println("Recieved packet: " + packetID);
			}
			Packet packet = Packet.createEmptyPacket(packetID);
			packet.read(instream);
			/* System.out.println("Finished reading packet");*/

			try
			{
				inList.put(packet);
			}
			catch(Exception e){}
		}
	}
		
	public void sendPacket(Packet packet)
	{
		/* System.out.println("Sending packet: " + packet.getID());*/
		try
		{
			outstream.writeUTF("packet");
			outstream.writeInt(packet.getID());
			packet.write(outstream);
		}
		catch(IOException ioe)
		{
			System.out.println("Failed to send packet: " + packet.getID());
			ioe.printStackTrace();
			try
			{
				closeConnection();
			}
			catch(IOException ioe2)
			{
				ioe2.printStackTrace();
			}
		}
	}
	
	public void disconnect() throws IOException
	{
		outstream.writeUTF("disconnect");
		closeConnection();
	}
	
	public void closeConnection() throws IOException
	{
		done = true;
		instream.close();
		outstream.close();
		socket.close();
	}
}
