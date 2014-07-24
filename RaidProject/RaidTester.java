import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Test;

import raidFileStreams.Config;
import raidFileStreams.raid0FileStreams.Raid0FileOutputStream;
import edu.iiitb.basicjunit.BasicJunit;

/**
 * Author-Testing Team
 * Tests the RAID algorithms.
 */
public class RaidTester extends BasicJunit {

	/**
	 * Tests the write operation to RAID0 . Also compares multi-threaded write
	 * operation. The first file is without multi-threading whereas the second
	 * file uses multi-threading. The comparative speedup is shown in the
	 * console.
	 */
	@Test
	public void testWriteRAID0() {
		String testCase = new String("WRAID0.");
		int i;
		long a, b, c, d;

		// RAID0 - Writing file byte by byte
		Raid0FileOutputStream r0fos1;
		try {
			r0fos1 = new Raid0FileOutputStream(getStringValue(testCase
					+ "RAID0FileOutputStream1"));

			a = System.currentTimeMillis();// begin
			StringBuilder expectedOutput = new StringBuilder();
			for (i = 0; i <= 20000; i++) {
				if (i % 2 == 0) {
					r0fos1.writeByte('A');
					expectedOutput.append('A');
				} else {
					r0fos1.writeByte('B');
					expectedOutput.append('B');
				}

			}

			b = System.currentTimeMillis();// end

			r0fos1.close();

			// RAID0 - Using threads
			String s = "";
			for (i = 1; i <= 20000; i++)
				s += "A";
			Raid0FileOutputStream r0fos2 = new Raid0FileOutputStream(
					getStringValue(testCase + "RAID0FileOutputStream2"));

			c = System.currentTimeMillis();// begin
			r0fos2.writeByteArray(s);
			d = System.currentTimeMillis();// end

			r0fos2.close();
			System.out.println("Statistics");
			System.out.println("Using writeByte: " + (b - a) + "ms");
			System.out.println("Using writeByteArray: " + (d - c) + "ms");
			System.out.println("% performance improvement: "
					+ new DecimalFormat("0.##").format((double) (b - a - d + c)
							/ (b - a) * 100.0f));

			String drive1Path = Config.getLowerLevelPath(
					getStringValue(testCase + "RAID0FileOutputStream1"), 1);
			String drive1Data = normalRead(drive1Path);
			String drive2Path = Config.getLowerLevelPath(
					getStringValue(testCase + "RAID0FileOutputStream1"), 2);
			String drive2Data = normalRead(drive2Path);
			StringBuilder combinedData = new StringBuilder();
			int drive1Count = 0, drive2Count = 0;
			for (i = 0; i <= 20000; i++) {
				if (i % 2 == 0) {
					combinedData.append(drive1Data.charAt(drive1Count));
				} else {
					combinedData.append(drive2Data.charAt(drive2Count));
				}

			}
			Assert.assertEquals(expectedOutput.toString(),
					combinedData.toString());
			// System.out.println(drive1Data + drive2Data);
			// System.out.println("Length:" + (drive1Data + drive2Data).length());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String normalRead(String pathname) {
		String fileData = null;
		File file = new File(pathname);
		if (file != null && file.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				fileData = reader.readLine();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return fileData;
	}

}
