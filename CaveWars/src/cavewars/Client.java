package cavewars;

import java.io.IOException;
import java.net.*;

public class Client
{
	public static Socket connect(String ipAdress, int port) throws UnknownHostException, IOException
	{
		Socket socket = new Socket(ipAdress, port);
		
		return socket;
	}
}
