package raidFileStreams;

import java.io.IOException;
//Author-Amrutha
public class WriterThread extends Thread
{
IOutputStream stream;
int count, start, gap;
byte[] content;
IOException e;

public WriterThread(byte[] b, IOutputStream stream, int start, int len, int gap)
{
	this.stream = stream;
	this.content = b;
	this.count = len / gap;
	this.gap = gap;
	this.start = start;
	if((start == 0) && (len % gap == 1))
	{
		this.count++;
	}
	this.e = null;	
}

public void run()
{
	int i = this.start;
	
	while(this.count > 0)
	{
		try
			{		
				this.stream.writeByte(this.content[i]);
			}
			catch(IOException e)
			{
				this.e = e;
				this.stop();
			}
			
		i += gap;
		this.count--;
	}
	
}

}
