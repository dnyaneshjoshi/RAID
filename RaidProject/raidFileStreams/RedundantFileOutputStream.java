package raidFileStreams;

import java.io.IOException;
//Author-Amrutha
public class RedundantFileOutputStream implements IOutputStream
{
	protected IOutputStream stream1, stream2;
	protected byte[] content;
		
	public void writeByte(char character) throws IOException
	{
		byte charByte = (byte)character;
		try
		{
			writeByte(charByte);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void writeByteArray(String str) throws IOException
	{
		byte[] bytes = str.getBytes();
		int len = bytes.length;
		try
		{
			writeByteArray(bytes, len);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void writeByte(byte b) throws IOException
	{
			this.stream1.writeByte(b);
			this.stream2.writeByte(b);
	}
	
	public void writeByteArray(byte[] b, int len) throws IOException
	{
		WriterThread wt1, wt2;
		wt1 = new WriterThread(b, this.stream1, 0, len, 1);
		wt2 = new WriterThread(b, this.stream2, 0, len, 1);
		
		wt1.start();
		wt2.start();
		
		try
		{
			wt1.join();
			wt2.join();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(wt1.e != null || wt2.e != null)
			throw new IOException("Write operation failed.");				
	}
	
	public void close() throws IOException
	{
		this.stream1.close();
		this.stream2.close();
	}
}
