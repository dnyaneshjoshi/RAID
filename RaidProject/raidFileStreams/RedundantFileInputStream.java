package raidFileStreams;

import java.io.IOException;
//Author-Dnyanesh
public abstract class RedundantFileInputStream implements IInputStream
{
	protected IInputStream stream1, stream2;
	
	public byte readByte() throws IOException
	{
		if(this.stream1!=null)
			return this.stream1.readByte();
		else
			return this.stream2.readByte();
	}
	
	public byte[] readByteArray(int len) throws IOException
	{
		byte[] b=new byte[len];
		
		if(this.stream1!=null)
		{
			for(int i=0;i<=len-1;i++)
				b[i]=this.stream1.readByte();
		}
		else
		{
			for(int i=0;i<=len-1;i++)
				b[i]=this.stream2.readByte();
		}
		
		return b;
	}
	
	public void close() throws IOException
	{
		if(this.stream1!=null)
			this.stream1.close();
		else
			this.stream2.close();
	}
}
