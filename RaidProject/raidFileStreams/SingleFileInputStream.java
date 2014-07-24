package raidFileStreams;

import java.io.FileInputStream;
import java.io.IOException;
//Author-Dnyanesh
public class SingleFileInputStream extends FileInputStream implements IInputStream
{
	public SingleFileInputStream(String name) throws Exception
	{
		super(name);
	}
	
	public byte readByte() throws IOException
	{
		int b=this.read();
		
		if(b!=-1)
			return (byte)b;
		else
			throw new IOException("Read operation failed.");
	}
}
