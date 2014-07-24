package raidFileStreams.raid1FileStreams;

import raidFileStreams.*;

import java.io.File;

//Author-Amrutha
public class Raid1FileOutputStream extends RedundantFileOutputStream
{
	private boolean isInnerDrive=false;
	
	private void init(String fPath) throws Exception
	{
		if(Config.getRaidDriveType(fPath)!=1)
			throw new InvalidRaidDriveException("1");
		else
		{
			String[] paths = new String[2];
			paths[0] = Config.getLowerLevelPath(fPath, 1);
			paths[1] = Config.getLowerLevelPath(fPath, 2);
			File file1 = new File(paths[0]);
			File file2 = new File(paths[1]);
			
			try
			{
				if(!file1.exists())
				{
					file1.createNewFile();
					file2.createNewFile();
					if(!this.isInnerDrive) Config.log(fPath, "Created file");
				}
				else
					if(!this.isInnerDrive) Config.log(fPath, "Modified file");
			
				this.stream1 = new SingleFileOutputStream(paths[0]);
				this.stream2 = new SingleFileOutputStream(paths[1]);
			}
			catch(Exception e)
			{
				Config.deleteFileOnRaidDrive(fPath);
				throw new CannotCreateFileInRaidDriveException(fPath, "1");
			}			
		}
	}
	
	public Raid1FileOutputStream(String fPath) throws Exception
	{
		this.init(fPath);
	}
	
	public Raid1FileOutputStream(String fPath, boolean isInnerDrive) throws Exception
	{
		this.isInnerDrive=isInnerDrive;
		this.init(fPath);
	}
}
