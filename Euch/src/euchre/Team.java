package euchre;

public class Team 
{
	private Player player1;
	private Player player2;
	private int score;
	private boolean wonBid;
	
	Team(Player p1, Player p2)
	{
		player1 = p1;
		player2 = p2;
		score = 0;
		setWonBid(false);
	}
	
	public Player getPlayer1() 
	{
		return player1;
	}
	public void setPlayer1(Player player1) 
	{
		this.player1 = player1;
	}
	public Player getPlayer2() 
	{
		return player2;
	}
	public void setPlayer2(Player player2) 
	{
		this.player2 = player2;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isWonBid() {
		return wonBid;
	}

	public void setWonBid(boolean wonBid) {
		this.wonBid = wonBid;
	}
}
