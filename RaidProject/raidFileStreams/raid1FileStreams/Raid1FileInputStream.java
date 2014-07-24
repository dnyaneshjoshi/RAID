package raidFileStreams.raid1FileStreams;

import raidFileStreams.*;
//Author-Dnyanesh
public class Raid1FileInputStream extends RedundantFileInputStream
{
	public Raid1FileInputStream(String fPath) throws Exception
	{
		if(Config.getRaidDriveType(fPath)!=1)
			throw new InvalidRaidDriveException("1");
		
		try
		{
			this.stream1=new SingleFileInputStream(Config.getLowerLevelPath(fPath, 1));
		}
		catch(Exception e)
		{	
			try
			{
				this.stream2=new SingleFileInputStream(Config.getLowerLevelPath(fPath, 2));
			}
			catch(Exception ee)
			{
				throw new CannotOpenFileInRaidDriveException(fPath, "1");
			}
		}
	}
}
