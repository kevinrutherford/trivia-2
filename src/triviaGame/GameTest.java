package triviaGame;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import org.junit.Test;

public class GameTest {

	public static void runAThousandGames(PrintStream out) {
		Random rand = new Random(7919);
		for (int i = 0; i < 1000; i++)
			GameRunner.run(rand, out);
	}

	public void generateMaster() throws IOException {
		runAThousandGames(new PrintStream(new GoldenMaster().asFile()));
	}

	@Test
	public void characterizationTest() throws IOException {
		String master = new GoldenMaster().currentValue();
		ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
		runAThousandGames(new PrintStream(outputBuffer));
		assertEquals(master, outputBuffer.toString());
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
