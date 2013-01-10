package cavewars.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class VersionTracker
{
	private static String MD5sum;
	
	public static void generateMD5()
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			InputStream is = new FileInputStream("CaveWars.jar");
			try 
			{
				is = new DigestInputStream(is, md);
				// read stream to EOF as normal...
			}
			finally 
			{
				is.close();
			}
			byte[] digest = md.digest();

			BigInteger versionMD5 = new BigInteger(digest);

			MD5sum = versionMD5.toString();
		}
		catch(Exception e)
		{
			Shutdown.shutDown(e.toString());
		}
	}
	
	public static String getMD5Sum()
	{
		return MD5sum;
	}
	
	public static boolean isSameVersion(String otherMD5)
	{
		return MD5sum.equals(otherMD5);
	}
}
