package raidFileStreams.raid10FileStreams;

import raidFileStreams.*;
import raidFileStreams.raid1FileStreams.*;
//Author-Dnyanesh
public class Raid10FileInputStream extends StripedFileInputStream
{
	public Raid10FileInputStream(String fPath) throws Exception
	{
		if(Config.getRaidDriveType(fPath)!=2)
			throw new InvalidRaidDriveException("1+0");
		
		try
		{
			this.stream1=new Raid1FileInputStream(Config.getLowerLevelPath(fPath, 1));
			this.stream2=new Raid1FileInputStream(Config.getLowerLevelPath(fPath, 2));
		}
		catch(Exception e)
		{
			throw new CannotOpenFileInRaidDriveException(fPath, "1+0");
		}
	}
}
