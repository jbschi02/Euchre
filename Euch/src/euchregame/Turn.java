package euchregame;

import card.Trump;
import player.Player;
import player.Team;

public class Turn
{
	private EuchreGame game;
	private Player currentPlayer;
	private int winningBidNumber;
	private Team highestBiddingTeam;
	private Player highestBiddingPlayer;
	private Trump trump;
	int team1TricksWon;
	int team2TricksWon;

	public Turn(EuchreGame game)
	{
		this.game = game;
		currentPlayer = game.getLead();
		winningBidNumber = 2;
	}

	public void playTurn()
	{
		getBids();
		getTrumpCall();
		for (int i = 0; i < 6; i++)
		{
			Trick trick = new Trick(this);
			trick.playTrick();
			Player winner = trick.getWinner();
			if (game.getTeam1().isTeamMember(winner))
			{
				System.out.println("Team 1 wins the trick.");
				team1TricksWon++;
			}
			else if (game.getTeam2().isTeamMember(winner))
			{
				team2TricksWon++;
				System.out.println("Team 2 wins the trick.");
			}
			currentPlayer = winner;
		}
		setPoints();
	}

	private void setPoints()
	{
		if (highestBiddingTeam.equals(game.getTeam1()))
		{
			if (team1TricksWon >= winningBidNumber)
			{
				System.out.println("Team 1 scores " + team1TricksWon);
				game.updateTeam1Score(team1TricksWon);
			}
			else
			{
				System.out.println("Team 1 loses " + winningBidNumber);
				game.updateTeam1Score(winningBidNumber * -1);
			}
			game.updateTeam2Score(team2TricksWon);
		}
		else if (highestBiddingTeam.equals(game.getTeam2()))
		{
			if (team2TricksWon >= winningBidNumber)
			{
				System.out.println("Team 2 scores " + team2TricksWon);
				game.updateTeam2Score(team2TricksWon);
			}
			else
			{
				System.out.println("Team 2 loses " + winningBidNumber);
				game.updateTeam2Score(winningBidNumber * -1);
			}
			game.updateTeam1Score(team1TricksWon);
		}
	}

	private void getBids()
	{
		int currentBidToBeat = 2;
		Player playerWinningBid = currentPlayer;
		for (int i = 0; i < 4; i++)
		{
			if (i == 3 && currentBidToBeat == 2)
			{
				System.out.println("Dealer screwed!");
				currentBidToBeat = 3;
				playerWinningBid = currentPlayer;
			}
			else
			{
				int newCurrentBid = currentPlayer.getBid(currentBidToBeat);
				if (newCurrentBid > currentBidToBeat)
				{
					System.out.println(currentPlayer.getName() + " bids " + newCurrentBid);
					currentBidToBeat = newCurrentBid;
					playerWinningBid = currentPlayer;
				}
				else
				{
					System.out.println(currentPlayer.getName() + " passes.");
				}
			}
			currentPlayer = currentPlayer.getNextPlayer();
		}

		winningBidNumber = currentBidToBeat;
		System.out.println(playerWinningBid.getName());
		if (game.getTeam1().isTeamMember(playerWinningBid))
		{
			highestBiddingTeam = game.getTeam1();
		}
		else if (game.getTeam2().isTeamMember(playerWinningBid))
		{
			highestBiddingTeam = game.getTeam2();
		}
		highestBiddingPlayer = playerWinningBid;
	}

	private void getTrumpCall()
	{
		trump = highestBiddingPlayer.getTrump();
		System.out.println(trump.name() + " is trump!");
	}

	public int getWinningBidNumber()
	{
		return winningBidNumber;
	}

	public Team getHighestBiddingTeam()
	{
		return highestBiddingTeam;
	}

	public Trump getTrump()
	{
		return trump;
	}

	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}
}
