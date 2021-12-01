package designs.snakeAndLadders;

import java.util.logging.Logger;

public class Ladder {

	Logger logger = Logger.getLogger(Ladder.class.getName());
	int top;
	int bottom;

	public Ladder(int bottom, int top) throws BoardCreationException {
		if (top <= bottom) {
			String errorMsg = "Ladder creation failed!";
			throw new BoardCreationException(logger, errorMsg);
		}
		this.top = top;
		this.bottom = bottom;
	}

}
