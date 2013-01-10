package cavewars.util;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class BuildID
{
	private String versionNumber;
	private int buildNumber;
	private String versionMD5;
	
	public BuildID(String versionNumber, int buildNumber, String versionMD5)
	{
		this.versionNumber = versionNumber;
		this.buildNumber = buildNumber;
		this.versionMD5 = versionMD5;
	}
	
	public BuildID(String[] strs)
	{
		versionNumber = strs[0];
		buildNumber = Integer.parseInt(strs[1]);
		versionMD5 = strs[2];
	}
	
	
	public String getPrintable()
	{
		return versionNumber + "." + buildNumber + "." + versionMD5 + "." + getDate();
	}

	public static String getDate() 
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(cal.getTime());
	}
	
	public int getBuildNumber()
	{
		return buildNumber;
	}
	
	public String getMD5()
	{
		return versionMD5;
	}

	public boolean hasMatchingMD5(String otherMD5)
	{
		if(versionMD5.equals(otherMD5))
		{
			return true;
		}
		return false;
	}
}
