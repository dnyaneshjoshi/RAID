package raidFileStreams;
//Author-Dnyanesh
public class CannotCreateFileInRaidDriveException extends Exception
{
	public String fPath;
	public String raidDriveType;
	public CannotCreateFileInRaidDriveException(String fPath, String raidDriveType)
	{
		super("File "+fPath+" cannot be created on RAID"+raidDriveType+" drive.");
		this.fPath=fPath;
	}
}
