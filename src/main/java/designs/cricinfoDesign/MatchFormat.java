package designs.cricinfoDesign;

public enum MatchFormat {
	ODI(50, 2), T20(20, 2), TEST(450, 4), REDUCED(50, 2);

	int totalOvers;
	int totalInnings;

	private MatchFormat(int totalOvers, int totalInnings) {
		this.totalOvers = totalOvers;
		this.totalInnings = totalInnings;
	}

	public int getTotalOvers() {
		return totalOvers;
	}

	public void setTotalOvers(int totalOvers) {
		this.totalOvers = totalOvers;
	}

	public int getTotalInnings() {
		return totalInnings;
	}

	public void setTotalInnings(int totalInnings) {
		this.totalInnings = totalInnings;
	}

}
