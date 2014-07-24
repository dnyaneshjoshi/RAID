package raidFileStreams.raid01FileStreams;

import raidFileStreams.*;
import raidFileStreams.raid0FileStreams.*;
//Author-Dnyanesh
public class Raid01FileInputStream extends RedundantFileInputStream
{
	public Raid01FileInputStream(String fPath) throws Exception
	{
		if(Config.getRaidDriveType(fPath)!=3)
			throw new InvalidRaidDriveException("0+1");
		
		try
		{
			this.stream1=new Raid0FileInputStream(Config.getLowerLevelPath(fPath, 1));
		}
		catch(Exception e)
		{
			try
			{
				this.stream2=new Raid0FileInputStream(Config.getLowerLevelPath(fPath, 2));
			}
			catch(Exception ee)
			{
				throw new CannotOpenFileInRaidDriveException(fPath, "0+1");
			}
		}
	}
}
