package raidFileStreams;

import java.io.IOException;
//Author-Amrutha
public interface IOutputStream
{
	public void writeByte(byte b) throws IOException;
	public void close() throws IOException;
}