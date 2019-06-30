package euchregame;

import card.PileOfCards;
import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import player.Team;

public class EuchreGame
{
	private PileOfCards gameDeck;
	private Player human;
	private Player north;
	private Player east;
	private Player west;
	private Team team1;
	private Team team2;
	private Player dealer;

	public EuchreGame()
	{
		setUpGame();
		dealInitialDeck();
		play();
	}

	private void setUpGame()
	{
		gameDeck = new PileOfCards();
		gameDeck.createNewEuchreDeck();

		human = new HumanPlayer("John");
		north = new ComputerPlayer("North");
		east = new ComputerPlayer("East");
		west = new ComputerPlayer("West");
		human.setNextPlayer(west);
		west.setNextPlayer(north);
		north.setNextPlayer(east);
		east.setNextPlayer(human);

		team1 = new Team(human, north);
		team2 = new Team(west, east);

		dealer = human;
	}

	private void dealInitialDeck()
	{
		gameDeck.shuffle();

		for (int i = 0; i < 6; i++)
		{
			west.addCard(gameDeck.draw());
			north.addCard(gameDeck.draw());
			east.addCard(gameDeck.draw());
			human.addCard(gameDeck.draw());
		}
	}

	private void play()
	{
		do
		{
			human.printHand();
			System.out.println();
			Turn turn = new Turn(this);
			turn.playTurn();
			System.out.println("Team 1 score: " + team1.getScore());
			System.out.println("Team 2 score: " + team2.getScore());
			dealer = dealer.getNextPlayer();
			gameDeck.createNewEuchreDeck();
			dealInitialDeck();
		} while (team1.getScore() < 25 && team2.getScore() < 25 || team1.getScore() == team2.getScore());
	}

	public Player getDealerPosition()
	{
		return dealer;
	}

	public Team getTeam1()
	{
		return team1;
	}

	public Team getTeam2()
	{
		return team2;
	}

	public void updateTeam1Score(int turnScore)
	{
		team1.updateScore(turnScore);
	}

	public void updateTeam2Score(int turnScore)
	{
		team2.updateScore(turnScore);
	}

	public Player getLead()
	{
		return dealer.getNextPlayer();
	}
}
