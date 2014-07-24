package raidFileStreams;

import java.io.IOException;
//Author-Dnyanesh
public abstract class StripedFileInputStream implements IInputStream
{
	protected IInputStream stream1, stream2;
	int diskSwitch=1;
	
	public byte readByte() throws IOException
	{
		if(this.diskSwitch==1)
		{
			this.diskSwitch=2;
			return this.stream1.readByte();
		}
		else
		{
			this.diskSwitch=1;
			return this.stream2.readByte();
		}
	}
	
	public byte[] readByteArray(int len) throws IOException
	{
		byte[] b=new byte[len];
		
		ReaderThread rt1=new ReaderThread(this.stream1, b, 0);
		ReaderThread rt2=new ReaderThread(this.stream2, b, 1);
		
		rt1.start();
		rt2.start();
		
		try
		{
			rt1.join();
			rt2.join();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(rt1.e==null && rt2.e==null)
			return b;
		else
			throw new IOException("Read operation failed.");
	}
	
	public void close() throws IOException
	{
		this.stream1.close();
		this.stream2.close();
	}
}
