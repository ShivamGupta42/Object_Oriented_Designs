package designs.snakeAndLadders;

import java.util.List;
import java.util.Optional;

public class Board {
	int size;
	List<Snake> snakes;
	List<Ladder> ladders;

	private Board(BoardBuilder builder) {
		size = builder.size;
		snakes = builder.snakes;
		ladders = builder.ladders;
	}

	public static class BoardBuilder {

		int size;
		List<Snake> snakes;
		List<Ladder> ladders;

		public BoardBuilder(int size) {
			this.size = size;
		}

		public BoardBuilder withSnakes(List<Snake> snakes) {
			this.snakes = snakes;
			return this;
		}

		public BoardBuilder withLadders(List<Ladder> ladders) {
			this.ladders = ladders;
			return this;
		}

		public Board build() {
			return new Board(this);
		}

	}

	public Optional<Snake> getPossibleSnake(int posPosition) {
		return snakes.parallelStream().filter(e -> e.head == posPosition).findAny();
	}

	public Optional<Ladder> getPossibleLadder(int posPosition) {
		return ladders.parallelStream().filter(e -> e.bottom == posPosition).findAny();
	}

}
