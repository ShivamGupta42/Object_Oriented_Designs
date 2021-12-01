package designs.cricinfoDesign;

public enum BowlTypes {
	NO_BALL(1, 0), WIDE(1, 0), DEAD(0, 0), SINGLE(1, 1), DOUBLE(2, 1), TRIPLE(3, 1), FOUR_SINGLES(4, 1),
	FIVE_SINGLES(5, 1), FOUR(4, 1), SIX(6, 1), PENALTY(-5, 0), WICKET(0, 1);
	int runsGiven;
	int bowlsConsumed = 0;

	private BowlTypes(int runsGiven, int bowlsConsumed) {
		this.runsGiven = runsGiven;
		this.bowlsConsumed = bowlsConsumed;
	}

	public int getRunsGiven() {
		return runsGiven;
	}

	public void setRunsGiven(int runsGiven) {
		this.runsGiven = runsGiven;
	}

	public int getBowlsConsumed() {
		return bowlsConsumed;
	}

	public void setBowlsConsumed(int bowlsConsumed) {
		this.bowlsConsumed = bowlsConsumed;
	}

}
