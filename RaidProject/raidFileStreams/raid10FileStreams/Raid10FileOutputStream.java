package raidFileStreams.raid10FileStreams;

import java.io.File;

import raidFileStreams.*;
import raidFileStreams.raid1FileStreams.*;
//Author-Amrutha
public class Raid10FileOutputStream extends StripedFileOutputStream
{
	public Raid10FileOutputStream(String fPath) throws Exception
	{
		if(Config.getRaidDriveType(fPath)!=2)
			throw new InvalidRaidDriveException("1+0");
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
				
				this.stream1 = new Raid1FileOutputStream(paths[0], true);
				this.stream2 = new Raid1FileOutputStream(paths[1], true);		
			}
			catch(Exception e)
			{
				Config.deleteFileOnRaidDrive(fPath);
				throw new CannotCreateFileInRaidDriveException(fPath, "1+0");
			}
		}		
		
		
	}
	

}
