package triviaGame;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

public class GoldenMaster {

	private static final String MASTER_FILENAME = "./master.txt";

	public static void main(String[] args) throws IOException {
		GameRunner.runAThousandGames(new PrintStream(MASTER_FILENAME));
	}

	public String currentValue() {
		try {
			return readFileAsString(MASTER_FILENAME);
		} catch (IOException e) {
			return "";
		}
	}

	private static String readFileAsString(String filePath) throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream(filePath));
		try {
			long len = new File(filePath).length();
			if (len > Integer.MAX_VALUE)
				throw new IOException("File " + filePath + " too large, was " + len + " bytes.");
			byte[] bytes = new byte[(int) len];
			dis.readFully(bytes);
			return new String(bytes, "UTF-8");
		} finally {
			dis.close();
		}
	}

}
