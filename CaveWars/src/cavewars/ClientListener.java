package cavewars;

import java.io.IOException;
import java.net.*;
import java.util.logging.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class ClientListener implements Runnable
{
	public Server server;
	public ServerSocket serverSocket;
	
	public boolean running = true;
	
	public ClientListener(Server server, int port) throws IOException
	{
		this.server = server;
		
		serverSocket = new ServerSocket(port);
	}

	@Override
	public void run()
	{
		while(running)
		{
			Socket clientSocket = null;
			try
			{
				clientSocket = serverSocket.accept();
				server.newConnection(clientSocket);
			} 
			catch (IOException ioe) 
			{
				System.out.println("Connection failed"); 
				
				if(clientSocket != null)
				{
					try
					{
						clientSocket.close();
					}
					catch (IOException ioe2){}
				}
				
			}
		}
		System.out.println("ClientListener shutdown");
		server.clientListener = null;
	}
	
	public void start()
	{
		System.out.println("ClientListener start");
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void stop()
	{
		System.out.println("ClientListener stop");
		running = false;
		try
		{
			serverSocket.close();
		} 
		catch (IOException ex) {}
	}
}
