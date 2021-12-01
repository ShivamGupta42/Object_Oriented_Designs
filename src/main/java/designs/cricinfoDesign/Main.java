package designs.cricinfoDesign;

import designs.cricinfoDesign.ScoreBoard.ScoreboardBuilder;

public class Main {

	public static void main(String[] args) {
		// Making Team A
		Team A = new Team("India", "Indian team", MatchFormat.ODI);
		Player p1 = new Player("Sehwag");
		Player p2 = new Player("Sachin");
		Player p3 = new Player("Kohli");
		Player p4 = new Player("Dravid");
		Player p5 = new Player("Yuvraj");
		A.addPlayer(p1);
		A.addPlayer(p2);
		A.addPlayer(p3);
		A.addPlayer(p4);
		A.addPlayer(p5);

		// Making Team B
		Team B = new Team("Australia", "Australian team", MatchFormat.ODI);
		Player p6 = new Player("Gilchirst");
		Player p7 = new Player("Hayden");
		Player p8 = new Player("Pointing");
		Player p9 = new Player("Smith");
		Player p10 = new Player("Calrke");
		B.addPlayer(p6);
		B.addPlayer(p7);
		B.addPlayer(p8);
		B.addPlayer(p9);
		B.addPlayer(p10);

		ScoreBoard sb = new ScoreboardBuilder().firstTeam(A).secondTeam(B).matchType(MatchFormat.ODI).build();

		// Simulating First Innings
		sb.startNewInnings();
		sb.addBowl(BowlTypes.FOUR);
		sb.addBowl(BowlTypes.SINGLE);
		sb.addBowl(BowlTypes.DOUBLE);
		sb.addBowl(BowlTypes.TRIPLE);
		sb.addBowl(BowlTypes.WIDE);
		sb.addBowl(BowlTypes.NO_BALL);
		sb.addBowl(BowlTypes.WICKET);// Wicket
		sb.addBowl(BowlTypes.WICKET);// Wicket
		sb.addBowl(BowlTypes.FOUR);
		sb.addBowl(BowlTypes.SINGLE);
		sb.addBowl(BowlTypes.DOUBLE);
		sb.addBowl(BowlTypes.TRIPLE);
		sb.addBowl(BowlTypes.WIDE);
		sb.addBowl(BowlTypes.NO_BALL);
		sb.addBowl(BowlTypes.WICKET);// wicket
		// Wicket

		// System.out.println(sb.getCurrentMatchStats());

		// Simulating Second Innings
		sb.startNewInnings();
		sb.addBowl(BowlTypes.FOUR);
		sb.addBowl(BowlTypes.WICKET); // wicket

		sb.addBowl(BowlTypes.SINGLE);
		sb.addBowl(BowlTypes.DOUBLE);
		sb.addBowl(BowlTypes.TRIPLE);
		sb.addBowl(BowlTypes.WICKET); // wicket

		sb.addBowl(BowlTypes.WIDE);
		sb.addBowl(BowlTypes.NO_BALL);
		sb.addBowl(BowlTypes.WICKET); // wicket

		sb.addBowl(BowlTypes.FOUR);
		sb.addBowl(BowlTypes.SINGLE);

		System.out.println(sb.getCurrentMatchStats());
		System.out.println(sb.getMatchResult());

	}
}
