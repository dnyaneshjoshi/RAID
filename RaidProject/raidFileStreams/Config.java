package raidFileStreams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import raidFileStreams.raid1FileStreams.Raid1FileRecovery;
//Class author-Dnyanesh
//restoreDrive method written by Amrutha and Chaitanya
final public class Config
{
	Config()
	{
	}
	
	public static String configFilePath="config.csv";
	public static String logFilePath="logs/log";
	
	public static void log(String fPath, String action) throws Exception
	{
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			FileOutputStream fos=new FileOutputStream(Config.logFilePath+"_Drive_"+fPath.split("/")[1], true);
			OutputStreamWriter osw=new OutputStreamWriter(fos);
			osw.append(dateFormat.format(cal.getTime())+"\t\t"+action+" '"+fPath+"'"+(char)10);
			osw.close();
			fos.close();
		}
		catch(Exception e)
		{
			throw new Exception("Log file error");
		}
	}
	
	public static String getLowerLevelPath(String fPath, int n) throws Exception
	{
		String temp, lowerLevelPath;
		String[] fPathComponents=fPath.split("/");
		BufferedReader br=new BufferedReader(new FileReader(Config.configFilePath));
		
		while((temp=br.readLine())!=null)
		{
			if(fPathComponents[1].compareToIgnoreCase(temp.split(",")[1])==0)
				break;
		}
		
		br.close();
		
		if(n==1)
		{
			lowerLevelPath="/"+temp.split(",")[2];
			for(int i=2;i<fPathComponents.length;i++)
				lowerLevelPath+="/"+fPathComponents[i];
		}
		else
		{
			lowerLevelPath="/"+temp.split(",")[3];
			for(int i=2;i<fPathComponents.length;i++)
				lowerLevelPath+="/"+fPathComponents[i];
		}
		
		return lowerLevelPath;
	}
	
	//Return values: 0-RAID0, 1-RAID1, 2-RAID1+0, 3-RAID0+1
	public static int getRaidDriveType(String fPath) throws Exception
	{
		String temp;
		String[] fPathComponents=fPath.split("/");
		
		BufferedReader br=new BufferedReader(new FileReader(Config.configFilePath));
		
		while((temp=br.readLine())!=null)
		{
			if(fPathComponents[1].compareToIgnoreCase(temp.split(",")[1])==0)
				break;
		}
		
		br.close();
		
		if(temp!=null)
			return Integer.parseInt(temp.split(",")[0]);
		else
			return -1;
	}
	
	public static void deleteFileOnRaidDrive(String fPath)
	{
		try
		{
			switch(Config.getRaidDriveType(fPath))
			{
			case 0:
				new File(Config.getLowerLevelPath(fPath, 1)).delete();
				new File(Config.getLowerLevelPath(fPath, 2)).delete();
				break;
				
			case 1:
				new File(Config.getLowerLevelPath(fPath, 1)).delete();
				new File(Config.getLowerLevelPath(fPath, 2)).delete();
				break;
				
			case 2:
				new File(Config.getLowerLevelPath(Config.getLowerLevelPath(fPath, 1), 1)).delete();
				new File(Config.getLowerLevelPath(Config.getLowerLevelPath(fPath, 1), 2)).delete();
				new File(Config.getLowerLevelPath(Config.getLowerLevelPath(fPath, 2), 1)).delete();
				new File(Config.getLowerLevelPath(Config.getLowerLevelPath(fPath, 2), 2)).delete();
				break;
				
			case 3:
				new File(Config.getLowerLevelPath(Config.getLowerLevelPath(fPath, 1), 1)).delete();
				new File(Config.getLowerLevelPath(Config.getLowerLevelPath(fPath, 1), 2)).delete();
				new File(Config.getLowerLevelPath(Config.getLowerLevelPath(fPath, 2), 1)).delete();
				new File(Config.getLowerLevelPath(Config.getLowerLevelPath(fPath, 2), 2)).delete();
				break;
			}
		}
		catch(Exception e)
		{
		}
	}
	
	public static long getFileSize(String fPath) throws Exception
	{
		File f1, f2, f3, f4;
		try
		{
			switch(getRaidDriveType(fPath))
			{
			case 0:
				f1=new File(getLowerLevelPath(fPath, 1));
				f2=new File(getLowerLevelPath(fPath, 2));
				return f1.length()+f2.length();
			case 1:
				f1=new File(getLowerLevelPath(fPath, 1));
				f2=new File(getLowerLevelPath(fPath, 2));
				if(f1.length()==f2.length())
					return f1.length();
				else
					throw new Exception("Corrupt file.");
			case 2:
				f1=new File(getLowerLevelPath(getLowerLevelPath(fPath, 1), 1));
				f2=new File(getLowerLevelPath(getLowerLevelPath(fPath, 1), 2));
				f3=new File(getLowerLevelPath(getLowerLevelPath(fPath, 2), 1));
				f4=new File(getLowerLevelPath(getLowerLevelPath(fPath, 2), 2));
				if(f1.length()==f2.length() && f3.length()==f4.length())
					return f1.length()+f3.length();
				else
					throw new Exception("Corrupt file.");
			case 3:
				f1=new File(getLowerLevelPath(getLowerLevelPath(fPath, 1), 1));
				f2=new File(getLowerLevelPath(getLowerLevelPath(fPath, 1), 2));
				f3=new File(getLowerLevelPath(getLowerLevelPath(fPath, 2), 1));
				f4=new File(getLowerLevelPath(getLowerLevelPath(fPath, 2), 2));
				if(f1.length()+f2.length()==f3.length()+f4.length())
					return f1.length()+f2.length();
				else
					throw new Exception("Corrupt file.");
			default:
				return -1;
			}
		}
		catch(Exception e)
		{
			throw new Exception("Corrupt file.");
		}
	}
	
	//Author-Amrutha and Chaitanya
	public static int restoreDrive(String driveName) throws Exception
	{
		try
    	{
	    	driveName = "/" + driveName;
	    	int RaidType = Config.getRaidDriveType(driveName);
	    	switch(RaidType)
	    	{
	    	case 1:
	    	       {
	    		    Raid1FileRecovery r1 = new Raid1FileRecovery(driveName);
	    	    	r1.Recover();
	    	    	Config.log(driveName, "Restored drive");
	    	    	return 0;
	    	       }
	    	case 2:
	    	      {
	    		   String drive1 = Config.getLowerLevelPath(driveName, 1);
	      		   Raid1FileRecovery r1 = new Raid1FileRecovery(drive1);
	    	       r1.Recover();    		   
	    		   String drive2 = Config.getLowerLevelPath(driveName, 2);
	      		   Raid1FileRecovery r2 = new Raid1FileRecovery(drive2);
	    	       r2.Recover();
	    	       Config.log(driveName, "Restored drive");
	    	       return 0;
	    	      }
	    	case 3:
	    	      {
	    	      String drive1 = Config.getLowerLevelPath(driveName, 1);
	    	      String drive2 = Config.getLowerLevelPath(driveName, 2);
	    	      String p1 = Config.getLowerLevelPath(drive1, 1);
	    	      String p2 = Config.getLowerLevelPath(drive2, 1);
	    		  Raid1FileRecovery r1 = new Raid1FileRecovery(p1, p2);
	    		  r1.Recover();  
	    		  String p3 = Config.getLowerLevelPath(drive1, 2);
	    		  String p4 = Config.getLowerLevelPath(drive2, 2);
	    		  Raid1FileRecovery r2 = new Raid1FileRecovery(p3, p4);
	    		  r2.Recover();
	    		  Config.log(driveName, "Restored drive");
	    		  return 0;
	    	      }
	    	default:
	    		return -1;
	    	}
    	}
    	catch(Exception e)
    	{
    		Config.log(driveName, "Restoration failed of drive");
    		throw new Exception("Drive restoration failed");
    	}
	}
}
