package raidFileStreams;
//Author-Dnyanesh
public class CannotOpenFileInRaidDriveException extends Exception
{
	public String fPath;
	public String raidDriveType;
	public CannotOpenFileInRaidDriveException(String fPath, String raidDriveType)
	{
		super("File "+fPath+" on RAID"+raidDriveType+" drive cannot be opened.");
		this.fPath=fPath;
	}
}
