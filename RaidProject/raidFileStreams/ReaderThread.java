package raidFileStreams;

import java.io.IOException;
//Author-Dnyanesh
class ReaderThread extends Thread
{
	IInputStream stream;
	byte[] b;
	int start, count;
	IOException e;
	
	public ReaderThread(IInputStream stream, byte[] b, int start)
	{
		this.stream=stream;
		this.b=b;
		this.start=start;
		this.count=this.b.length/2;
		
		if(this.start==0 && this.b.length%2==1)
			this.count++;
		
		this.e=null;
	}
	
	public void run()
	{
		int i=this.start;
		
		while(this.count>0)
		{
			try
			{
				b[i]=this.stream.readByte();
			}
			catch(IOException e)
			{
				this.e=e;
				this.stop();
			}
			
			i+=2;
			this.count--;
		}
	}
}
