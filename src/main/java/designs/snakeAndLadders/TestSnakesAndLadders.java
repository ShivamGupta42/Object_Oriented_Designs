package designs.snakeAndLadders;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class TestSnakesAndLadders {
	Board board;
	Game game;

	@BeforeEach
	void setup_board() throws BoardCreationException {

		List<Ladder> ladders = Arrays.asList(new Ladder(12, 71), new Ladder(25, 39), new Ladder(36, 88));
		List<Snake> snakes = Arrays.asList(new Snake(32, 2), new Snake(56, 21), new Snake(99, 12), new Snake(87, 37),
				new Snake(69, 7));
		board = new Board.BoardBuilder(100).withLadders(ladders).withSnakes(snakes).build();

		List<Player> players = Arrays.asList(new Player("Shivam", 0), new Player("Kaushik", 0));
		Game newGame = new Game(board, new Dice(), players);
	}
	
	
	

}
