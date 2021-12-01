package designs.snakeAndLadders;

public enum GameStatus {
	NOT_STARTED, ONGOING, FINISHED, PAUSED, ABANDONED;

	String result = this.name();

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
