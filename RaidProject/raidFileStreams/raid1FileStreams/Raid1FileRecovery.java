package raidFileStreams.raid1FileStreams;

import java.util.Arrays;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import raidFileStreams.Config;
import raidFileStreams.InvalidRaidDriveException;
//Authors-Amrutha and Chaitanya
public class Raid1FileRecovery {
	
	String path1, path2;
	
	public Raid1FileRecovery(String driveName) throws Exception{
		if(Config.getRaidDriveType(driveName)!=1)
			throw new InvalidRaidDriveException("1");
		path1 = Config.getLowerLevelPath(driveName, 1);
		path2 = Config.getLowerLevelPath(driveName, 2);		
	}
	public Raid1FileRecovery(String p1,String p2)
	{
		path1 = p1;
		path2 = p2;
	}
	
	public void Recover() throws Exception
	{
		int len1, len2, i = 0, j = 0;
		String fileName1, fileName2;
		File fileList1[];  
	    File fileList2[];  
	    
	    File filePath1 = new File(path1);
	    File filePath2 = new File(path2);
	    
	    fileList1 = filePath1.listFiles();  
	    fileList2 = filePath2.listFiles();  
	    
	    Arrays.sort (fileList1);  
	    Arrays.sort (fileList2);  
	    
	    len1 = fileList1.length;
	    len2 = fileList2.length;
	    
	    while(i < len1 && j < len2)
	    {
	    	int o = 0;
	    	
	    	fileName1 = fileList1[i].getName();
	    	fileName2 = fileList2[j].getName();
	    	
	    	o  = fileName1.compareTo(fileName2);
	    	
	    	if(o == 0)
	    	{
	    		i++;
	    		j++;
	    	}
	    	
	    	else if(o < 0)
	    	{
	    		CopyFile(fileList1[i], path2);
	    		i++;
	    	}
	    	
	    	else
	    	{
	    		CopyFile(fileList2[j], path1);
	    		j++;
	    	}	    	
	    }
	    
	    if(j < len2)
	    {
	    	while(j < len2)
	    	{
	    		CopyFile(fileList2[j], path1);
		    	j++;
	    	}
	    }
	    
	    if(i < len1)
	    {
	    	while(i < len1)
	    	{
	    		CopyFile(fileList1[i], path2);
	    		i++;
	    	}
	    }
	    
	}
	
	
	public void CopyFile(File srcFile, String destPath) throws Exception
	{
		String srcFileName = srcFile.getName();
		File destFile = new File(destPath + "/" + srcFileName);
		Path p1 = destFile.toPath();
		Path p2 = srcFile.toPath();
		try
		{
		Files.copy(p2, p1, LinkOption.NOFOLLOW_LINKS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
