package id.sql.models;

public class MatchHistory {
	private String user1;
	private String user2;
	private String user3;
	private String user4;
	private int scoreLeft;
	private int scoreRight;

	public MatchHistory(String user1, String user2, String user3, String user4, int scoreLeft, int scoreRight) {
		this.user1 = user1;
		this.user2 = user2;
		this.user3 = user3;
		this.user4 = user4;
		this.scoreLeft = scoreLeft;
		this.scoreRight = scoreRight;
	}

	public String getUser1() {
		return user1;
	}

	public void setUser1(String user1) {
		this.user1 = user1;
	}

	public String getUser2() {
		return user2;
	}

	public void setUser2(String user2) {
		this.user2 = user2;
	}

	public String getUser3() {
		return user3;
	}

	public void setUser3(String user3) {
		this.user3 = user3;
	}

	public String getUser4() {
		return user4;
	}

	public void setUser4(String user4) {
		this.user4 = user4;
	}

	public int getScoreLeft() {
		return scoreLeft;
	}

	public void setScoreLeft(int scoreLeft) {
		this.scoreLeft = scoreLeft;
	}

	public int getScoreRight() {
		return scoreRight;
	}

	public void setScoreRight(int scoreRight) {
		this.scoreRight = scoreRight;
	}

}
