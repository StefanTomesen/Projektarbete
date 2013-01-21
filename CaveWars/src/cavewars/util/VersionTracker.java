package cavewars.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
			MessageDigest digest = MessageDigest.getInstance("MD5");
		
			File f = new File("CaveWars.jar");
			InputStream is = new FileInputStream(f);
			
			byte[] buffer = new byte[8192];
			int read = 0;		
			try 
			{
				while( (read = is.read(buffer)) > 0) 
				{
					digest.update(buffer, 0, read);
				}		
				byte[] md5sum = digest.digest();
				
				BigInteger bigInt = new BigInteger(1, md5sum);
				String output = bigInt.toString(16);
				
				MD5sum = output;
			}
			catch(IOException e) 
			{
				throw new RuntimeException("Unable to process file for MD5", e);
			}
			finally 
			{
				try 
				{
					is.close();
				}
				catch(IOException e) 
				{
					throw new RuntimeException("Unable to close input stream for MD5 calculation", e);
				}
			}	
			
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
