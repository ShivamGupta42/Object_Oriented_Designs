package designs.snakeAndLadders;

import java.util.logging.Logger;

public class Snake {

	Logger logger = Logger.getLogger(Snake.class.getName());
	int head;
	int tail;

	public Snake(int head, int tail) throws BoardCreationException {
		if (head <= tail) {
			String errorMsg = "Snake creation failed!";
			throw new BoardCreationException(logger, errorMsg);
		}
		this.head = head;
		this.tail = tail;
	}

}
