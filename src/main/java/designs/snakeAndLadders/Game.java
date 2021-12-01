package designs.snakeAndLadders;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Game {
	long gameId;
	Board board;
	Deque<Player> players;
	Dice dice;
	GameStatus status = GameStatus.NOT_STARTED;

	public Game(Board board, Dice dice, List<Player> players) {
		this.board = board;
		this.dice = dice;
		this.players = new LinkedList<Player>(players);
	}

	public Game(GameMemento memento) {
		Game.this.board = memento.board;
		Game.this.players = memento.players;
		Game.this.dice = memento.dice;
		Game.this.status = memento.status;
	}

	void pauseGame() {
		status = GameStatus.PAUSED;
	}

	void restartGame() {
		status = GameStatus.ONGOING;
		startGame();
	}

	void startGame() {
		status = GameStatus.ONGOING;
		Thread t = new Thread(() -> {
			while (status == GameStatus.ONGOING) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				nextTurn();
			}

		});
		t.start();

	}

	void nextTurn() {
		Player curPlayer = players.poll();
		System.out.println("\n\n" + curPlayer.name + " turn:");
		int dice_roll = dice.roll();
		System.out.println("\n" + "dice returned :" + dice_roll);
		movePlayer(curPlayer, dice_roll);
		if (hasWonGame(curPlayer)) {
			status = GameStatus.FINISHED;
			status.setResult(curPlayer.name + " has Won");
			System.out.println("\n" + curPlayer.name + " has Won");
			System.exit(0);
		}

		players.add(curPlayer);
	}

	private boolean hasWonGame(Player curPlayer) {
		return curPlayer.position == board.size;
	}

	private void movePlayer(Player curPlayer, int dice_roll) {

		int posPosition = curPlayer.position + dice_roll;
		if (posPosition > board.size) {
			return;
		}
		System.out.println("Moving " + curPlayer.name + " to " + posPosition);

		Optional<Snake> posSnake = board.getPossibleSnake(posPosition);
		if (posSnake.isPresent()) {
			System.out.println("Bitten by a snake at " + posPosition);
			posPosition = posSnake.get().tail;
			System.out.println("Moving to " + posPosition);
		}

		Optional<Ladder> posLadder = board.getPossibleLadder(posPosition);
		if (posLadder.isPresent()) {
			System.out.println("Going up a ladder at " + posPosition);
			posPosition = posLadder.get().top;
			System.out.println("Moving to " + posPosition);
		}

		curPlayer.position = posPosition;
	}

	class GameMemento {
		Board board;
		Deque<Player> players;
		Dice dice;
		String result;
		GameStatus status = GameStatus.NOT_STARTED;

		GameMemento() {
			this.board = Game.this.board;
			this.players = Game.this.players;
			this.dice = Game.this.dice;
			this.status = Game.this.status;
		}
	}

	GameMemento saveGame() {
		return new GameMemento();
	}

	void reloadGame(GameMemento memento) {
		Game.this.board = memento.board;
		Game.this.players = memento.players;
		Game.this.dice = memento.dice;
		Game.this.status = memento.status;
	}
}
