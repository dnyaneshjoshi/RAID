package raidFileStreams.raid01FileStreams;

import java.io.File;

import raidFileStreams.*;
import raidFileStreams.raid0FileStreams.*;
//Author-Amrutha
public class Raid01FileOutputStream extends RedundantFileOutputStream
{
	public Raid01FileOutputStream(String fPath) throws Exception
	{
		if(Config.getRaidDriveType(fPath)!=3)
			throw new InvalidRaidDriveException("0+1");
		else
		{
			String[] paths = new String[2];
			paths[0] = Config.getLowerLevelPath(fPath, 1);
			paths[1] = Config.getLowerLevelPath(fPath, 2);
			
			try
			{
				if(!new File(Config.getLowerLevelPath(paths[0], 1)).exists()
						|| !new File(Config.getLowerLevelPath(paths[0], 2)).exists()
						|| !new File(Config.getLowerLevelPath(paths[1], 1)).exists()
						|| !new File(Config.getLowerLevelPath(paths[1], 2)).exists()
					)
					Config.log(fPath, "Created file");
				else
					Config.log(fPath, "Modified file");
				
				this.stream1 = new Raid0FileOutputStream(paths[0], true);
				this.stream2 = new Raid0FileOutputStream(paths[1], true);		
			}
			catch(Exception e)
			{
				Config.deleteFileOnRaidDrive(fPath);
				throw new CannotCreateFileInRaidDriveException(fPath, "0+1");
			}
		}		
		
	}
	
}
