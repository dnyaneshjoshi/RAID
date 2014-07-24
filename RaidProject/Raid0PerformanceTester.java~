import java.text.DecimalFormat;

import raidFileStreams.Config;
import raidFileStreams.raid0FileStreams.Raid0FileInputStream;
import raidFileStreams.raid0FileStreams.Raid0FileOutputStream;
//Demonstration of RAID0 read/write operation performance improvement using
//writeByteArray AND readByteArray methods over writeByte AND readByte methods, respectively.
//Even though this class contains a main method, it's not the entry point of this project. The entry point is RaidSelection.main().
//It may show more than 50% improvement sometimes, but theoritically it's not possible. It's only a result of function call overhead
//	in case of writeByte and readByte methods as they are called 'fSize' times as opposed to writeByteArray and readByteArray which
//	are called only once. Block I/O may also play a part.
public class Raid0PerformanceTester
{
	public static void main(String[] args) throws Exception
	{
		int i, fSize=100000;
		long a, b, c, d;
		
		System.out.println("RAID0 - PERFORMANCE TEST OF METHODS writeByteArray AND readByteArray - WRITING AND READING "+fSize+" BYTES\nPlease wait.....\n");
		
		//***********WRITING*******************************************************
		//RAID0 - Writing file byte by byte
		Raid0FileOutputStream r0fos1=new Raid0FileOutputStream("/RAID0DRIVE/testfile1.txt");
		
		a=System.currentTimeMillis();//begin
		for(i=1; i<=fSize; i++)
			r0fos1.writeByte('A');
		b=System.currentTimeMillis();//end
		
		r0fos1.close();
		
		//RAID0 - Writing parallely
		String s="";
		for(i=1; i<=fSize; i++)
			s+="A";
		Raid0FileOutputStream r0fos2=new Raid0FileOutputStream("/RAID0DRIVE/testfile2.txt");
		
		c=System.currentTimeMillis();//begin
		r0fos2.writeByteArray(s); //uses threads
		d=System.currentTimeMillis();//end
		
		r0fos2.close();
		
		System.out.println("Execution time of writeByte method of Raid0FileOutputStream class: "+(b-a)+"ms");
		System.out.println("Execution time of writeByteArray method of Raid0FileOutputStream class: "+(d-c)+"ms");
		System.out.println("Performance improvement of writeByteArray over writeByte: "+new DecimalFormat("0.##").format((double)(b-a-d+c)/(b-a)*100.0f)+"%");
		
		//***********READING*******************************************************
		//RAID0 - Reading file byte by byte
		Raid0FileInputStream r0fis1=new Raid0FileInputStream("/RAID0DRIVE/testfile1.txt");
		
		a=System.currentTimeMillis();//begin
		for(i=1; i<=fSize; i++)
			r0fis1.readByte();
		b=System.currentTimeMillis();//end
		
		r0fis1.close();
		
		//RAID0 - Reading parallely
		Raid0FileInputStream r0fis2=new Raid0FileInputStream("/RAID0DRIVE/testfile2.txt");
		
		c=System.currentTimeMillis();//begin
		r0fis2.readByteArray(fSize); //uses threads
		d=System.currentTimeMillis();//end
		
		r0fis2.close();
		
		System.out.println();
		System.out.println("Execution time of readByte method of Raid0FileInputStream class: "+(b-a)+"ms");
		System.out.println("Execution time of readByteArray method of Raid0FileInputStream class: "+(d-c)+"ms");
		System.out.println("Performance improvement of readByteArray over readByte: "+new DecimalFormat("0.##").format((double)(b-a-d+c)/(b-a)*100.0f)+"%");

		System.out.println();
		System.out.println("It may show more than 50% improvement sometimes, but theoritically");
		System.out.println("it's not possible. It's only a result of function call overhead");
		System.out.println("in case of writeByte and readByte methods as they are called "+fSize+" times");
		System.out.println("as opposed to writeByteArray and readByteArray which are called only once.");
		System.out.println("Block I/O may also play a part.");
		System.out.println();
	}
}
