package designs.cricinfoDesign;

import java.util.List;

public class Innings {

	static final int BOWLS_IN_AN_OVER = 6;

	int currentInnings;
	Team currentTeam;
	MatchFormat format;
	int oversRemaining;
	int bowlsRemaining;
	boolean hasInningsEnded = false;
	Player PlayerOnStrike;
	Player PlayerRunningEnd;

	public Innings(int currentInnings, Team currentPlayingTeam, MatchFormat format) {
		this.currentInnings = currentInnings;
		this.currentTeam = currentPlayingTeam;
		this.format = format;
		oversRemaining = format.getTotalOvers();
		bowlsRemaining = BOWLS_IN_AN_OVER;
	}

	private void startNewOver() {
		if (oversRemaining != 0) {
			// Fresh over
			bowlsRemaining = BOWLS_IN_AN_OVER;
			rotateStrike();
		} else {
			hasInningsEnded = true;
		}
	}

	private void rotateStrike() {
		// rotating strike
		Player temp = PlayerOnStrike;
		PlayerOnStrike = PlayerRunningEnd;
		PlayerRunningEnd = temp;

	}

	public void starInnings() {
		List<Player> openers = currentTeam.getOpeners();
		PlayerOnStrike = openers.get(0);
		PlayerRunningEnd = openers.get(1);
		PlayerOnStrike.setStatus(PlayerInningStatus.BATTING);
		PlayerRunningEnd.setStatus(PlayerInningStatus.BATTING);

	}

	public void addBowl(BowlTypes bowl) {
		if (hasInningsEnded) {
			return; // ThrowException
		}
		if (bowlsRemaining == 0) {
			startNewOver();
		}

		UpdateTeam(bowl);

		bowlsRemaining -= bowl.getBowlsConsumed();
	}

	private void UpdateTeam(BowlTypes bowl) {
		switch (bowl) {
		case NO_BALL: {
			currentTeam.incrementScore(BowlTypes.NO_BALL.getRunsGiven());
			break;
		}

		case WIDE: {
			currentTeam.incrementScore(BowlTypes.WIDE.getRunsGiven());
			break;
		}

		case DEAD: {
			currentTeam.incrementScore(BowlTypes.DEAD.getRunsGiven());
			break;
		}

		case SINGLE: {
			currentTeam.incrementScore(BowlTypes.SINGLE.getRunsGiven());
			PlayerOnStrike.incrementRunsScored(BowlTypes.SINGLE.getRunsGiven());
			PlayerOnStrike.incrementBallsFaced();
			rotateStrike();
			break;
		}

		case DOUBLE: {
			currentTeam.incrementScore(BowlTypes.DOUBLE.getRunsGiven());
			PlayerOnStrike.incrementRunsScored(BowlTypes.DOUBLE.getRunsGiven());
			PlayerOnStrike.incrementBallsFaced();
			break;
		}

		case TRIPLE: {
			currentTeam.incrementScore(BowlTypes.TRIPLE.getRunsGiven());
			PlayerOnStrike.incrementRunsScored(BowlTypes.TRIPLE.getRunsGiven());
			PlayerOnStrike.incrementBallsFaced();
			rotateStrike();
			break;
		}

		case FOUR_SINGLES: {
			currentTeam.incrementScore(BowlTypes.FOUR_SINGLES.getRunsGiven());
			PlayerOnStrike.incrementRunsScored(BowlTypes.FOUR_SINGLES.getRunsGiven());
			PlayerOnStrike.incrementBallsFaced();
			break;
		}

		case FIVE_SINGLES: {
			currentTeam.incrementScore(BowlTypes.FIVE_SINGLES.getRunsGiven());
			PlayerOnStrike.incrementRunsScored(BowlTypes.FIVE_SINGLES.getRunsGiven());
			PlayerOnStrike.incrementBallsFaced();
			rotateStrike();
			break;
		}

		case FOUR: {
			currentTeam.incrementScore(BowlTypes.FOUR.getRunsGiven());
			PlayerOnStrike.incrementRunsScored(BowlTypes.FOUR.getRunsGiven());
			PlayerOnStrike.incrementBallsFaced();
			PlayerOnStrike.incrementFours();
			break;
		}

		case SIX: {
			currentTeam.incrementScore(BowlTypes.SIX.getRunsGiven());
			PlayerOnStrike.incrementRunsScored(BowlTypes.SIX.getRunsGiven());
			PlayerOnStrike.incrementBallsFaced();
			PlayerOnStrike.incrementSixes();
			break;
		}

		case PENALTY: {
			currentTeam.incrementScore(BowlTypes.PENALTY.getRunsGiven());
			break;
		}

		case WICKET: {
			currentTeam.incrementScore(BowlTypes.WICKET.getRunsGiven());
			currentTeam.incrementWickets();
			PlayerOnStrike.incrementBallsFaced();
			PlayerOnStrike.setStatus(PlayerInningStatus.OUT);

			PlayerOnStrike = currentTeam.getNextPlayer();

			// If innings ended
			if (PlayerOnStrike != null) {
				PlayerOnStrike.setStatus(PlayerInningStatus.BATTING);
			} else {
				hasInningsEnded = true;
			}

			break;

		}

		}
	}

	public boolean hasInningsEnded() {
		return hasInningsEnded;
	}

}
