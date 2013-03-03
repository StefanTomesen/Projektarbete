package cavewars;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class PacketCentral implements Runnable
{
	InputStream instream;
	OutputStream outstream;
	
	ArrayList<Packet> outList;
	ArrayList<Packet> inList;

	public PacketCentral(Socket socket) throws IOException
	{
		instream = socket.getInputStream();
		outstream = socket.getOutputStream();
	}
	
	@Override
	public void run()
	{
		// Wait for response
	}
	
	public void startListening()
	{
		Thread thread = new Thread(this);
		thread.start();
	}
}
