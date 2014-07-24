package raidFileStreams;

import java.io.IOException;
//Author-Dnyanesh
public interface IInputStream
{
	public byte readByte() throws IOException;
	public void close() throws IOException;
}
