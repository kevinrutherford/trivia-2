package triviaGame;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import org.junit.Test;

public class GameTest {

	private static final String MASTER_FILENAME = "./master.txt";

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

	public void thousandTimes(PrintStream out) {
		Random rand = new Random(7919);
		for (int i = 0; i < 1000; i++)
			GameRunner.run(rand, out);
	}

	public void generateMaster() throws IOException {
		thousandTimes(new PrintStream(MASTER_FILENAME));
	}

	@Test
	public void characterizationTest() throws IOException {
		String master = readFileAsString(MASTER_FILENAME);
		ByteArrayOutputStream outputString = new ByteArrayOutputStream();
		thousandTimes(new PrintStream(outputString));
		assertEquals(master, outputString.toString());
	}

	@Test
	public void offByOneError() throws Exception {
		Game game = new Game();
		game.add("player1");
		game.add("player2");
		game.add("player3");
		game.add("player4");
		game.add("player5");
		game.add("player6");
		assertTrue(game.isPlayable());
	}
}
