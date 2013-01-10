package cavewars.util;

import java.io.*;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;

/**
 * The version tracker keeps track of and updates the versionID as the game is
 * being developed. The main reason for doing this is to make sure that 
 * incompatible versions of the game never play against each other over a network,
 * but it also serves a documentational purpose.
 * 
 * Every time a new build is compiled, it will be assigned a new build ID 
 * which is then stored in the build history file. Whenever the same build then 
 * loads, it will generate the same MD5 and simply grab the same ID as before.
 * 
 * Whenever 
 */
public class VersionTracker
{
	/** 
	 *  The current version number.
	 */
	public static final String versionNumber = "0.0";
	
	/**
	 * The version ID is a unique version identifier that is used to tell different
	 * versions of the game appart.
	 */
	private static BuildID currentBuildID;
	
	/**
	 * This switch tells the version tracer that the current build is going to be
	 * published. Published builds don't have access to the version record and
	 * must therefore have their ID hardcoded.
	 */
	private static final boolean publishedBuild = false;
	/**
	 * This version ID is hard coded into every published version of the game to
	 * allow it to circumvent the version updating mechanism which would otherwise
	 * be responsibe for getting the version ID. To ensure that the current build 
	 * in fact was meant to be published and has a correct hard coded version 
	 * number, it first has to verify the MD5 of the release to make sure that 
	 * it is correct.
	 */
	private static final BuildID publishedBuildID = null;

	
	/**
	 * Get the name of the current version as it is displayed in-game. 
	 */
	public String getVersionName()
	{
		return "Version " + versionNumber + " Build " + getBuildID().getBuildNumber();
	}
	
	public static BuildID getBuildID()
	{
		if(publishedBuild)
		{
			return publishedBuildID;
		}
		else
		{
			return currentBuildID;
		}
	}
	
	/**
	 * Look through the record of previous builds to see if this build has already
	 * been assigned a build number. If it hasn't, the build number will be reset.
	 */
	public static void updateVersionNumber()
	{
		try
		{
			String md5 = generateMD5();
			
			BuildID[] previousBuilds = getPreviousBuilds();
			int highestBuildNumber = 0;
			
			for(BuildID buildID : previousBuilds)
			{
				if(highestBuildNumber < buildID.getBuildNumber())
				{
					highestBuildNumber = buildID.getBuildNumber();
				}
				
				if(buildID.hasMatchingMD5(md5))
				{
					currentBuildID = buildID;
					return;
				}
			}
			
			if(currentBuildID == null) //Always
			{
				currentBuildID = new BuildID(versionNumber, highestBuildNumber + 1, md5);
				saveBuildID(currentBuildID);
			}
		}
		catch(Exception e)
		{
			Shutdown.shutDown(e.getMessage());
		}
	}
	
	
	private static String generateMD5() throws Exception
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
		
		return versionMD5.toString();
	}
			
	public static File getBuildHistoryFile() throws IOException
	{
		File file = new File("BuildHistory.txt");
		if(!file.exists())
		{
			file.createNewFile();
		}
		
		return file;
	}
	
	
	
	/**
	 * 
	 */
	public static BuildID[] getPreviousBuilds() throws IOException
	{
		File buildHistory = getBuildHistoryFile();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(buildHistory));
		
		ArrayList<BuildID> builds = new ArrayList<BuildID>();
		
		for(String BuildID = ""; (BuildID = bufferedReader.readLine()) != null;)
		{	
			builds.add(new BuildID(BuildID.split(".")));
		}
		
		return builds.toArray(new BuildID[builds.size()]);
	}
	
	public static void saveBuildID(BuildID buildID) throws IOException
	{
		PrintWriter pw = new PrintWriter(new FileWriter(getBuildHistoryFile(), true));
		try
		{
			pw.println(buildID.getPrintable());
		}
		finally
		{
			pw.close();
		}
	}
	
	
	
}