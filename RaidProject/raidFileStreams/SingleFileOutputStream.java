package raidFileStreams;

import java.io.FileOutputStream;
import java.io.IOException;
//Author-Amrutha
public class SingleFileOutputStream extends FileOutputStream implements IOutputStream
{
	public SingleFileOutputStream(String name) throws Exception
	{
		super(name);
	}

	public void writeByte(byte b) throws IOException
	{
		this.write(b);
	}
}
