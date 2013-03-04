package cavewars;

import java.io.*;

/**
 * @author Stefan Tomesen, 3B Portalens Gymnasium
 */
public class Packet0Login extends Packet
{
	public static final int packetID = 0;
	
	public int team;
	
	public Packet0Login(int team)
	{
		this.team = team;
	}
	
	public Packet0Login()
	{
		
	}
	
	@Override
	public void read(DataInputStream instream) throws IOException
	{
		team = instream.readInt();
	}

	@Override
	public void write(DataOutputStream outstream) throws IOException
	{
		outstream.writeInt(team);
	}

	@Override
	public int getID()
	{
		return packetID;
	}
	
}
