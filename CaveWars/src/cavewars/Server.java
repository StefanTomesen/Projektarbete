package cavewars;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.*;

public class Server implements Runnable
{
	public ClientListener clientListener;
	public ArrayList<PacketCentral> connections = new ArrayList();
	
	public boolean running = true;
	
	public Server(int port)
	{
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
		while(running)
		{
			
		}
		CaveWars.server = null;
		clientListener.stop();
		while(clientListener != null)
		{
			try
			{
				Thread.sleep(500);
			} catch (InterruptedException ex) {}
		}
		System.out.println("Shutdown");
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
	}
}
