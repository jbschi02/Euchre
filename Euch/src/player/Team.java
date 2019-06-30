package player;

public class Team
{
	private Player player1;
	private Player player2;
	private int score;

	public Team(Player player1, Player player2)
	{
		this.player1 = player1;
		this.player2 = player2;
		score = 0;
	}

	public int getScore()
	{
		return score;
	}

	public boolean isTeamMember(Player player)
	{
		return player == player1 || player == player2;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public void updateScore(int turnScore)
	{
		score += turnScore;
	}
}
