package raidFileStreams;
//Author-Dnyanesh
public class InvalidRaidDriveException extends Exception
{
	public String expectedDriveType;
	public InvalidRaidDriveException(String expectedDriveType)
	{
		super("Not a RAID"+expectedDriveType+" drive.");
		this.expectedDriveType=expectedDriveType;
	}
}
