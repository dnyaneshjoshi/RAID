package raidFileStreams.raid0FileStreams;

import raidFileStreams.*;
//Author-Dnyanesh
public class Raid0FileInputStream extends StripedFileInputStream
{
	public Raid0FileInputStream(String fPath) throws Exception
	{
		if(Config.getRaidDriveType(fPath)!=0)
			throw new InvalidRaidDriveException("0");
		
		try
		{
			this.stream1=new SingleFileInputStream(Config.getLowerLevelPath(fPath, 1));
			this.stream2=new SingleFileInputStream(Config.getLowerLevelPath(fPath, 2));
		}
		catch(Exception e)
		{
			throw new CannotOpenFileInRaidDriveException(fPath, "0");
		}
	}
}

