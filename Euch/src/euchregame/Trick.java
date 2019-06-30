package euchregame;

import card.Card;
import card.CardRankingList;
import card.PileOfCards;
import player.Player;

public class Trick
{
	private Turn turn;
	private Player currentPlayer;
	private Player winner;

	public Trick(Turn turn)
	{
		this.turn = turn;
		currentPlayer = turn.getCurrentPlayer();
	}

	public void playTrick()
	{
		PileOfCards pot = new PileOfCards();
		for (int i = 0; i < 4; i++)
		{
			currentPlayer.printHand();
			Card cardPlayed = currentPlayer.getCardPlay(pot, turn.getTrump());
			pot.addCard(cardPlayed);
			System.out.println(currentPlayer.getName() + " played the " + cardPlayed.toString());
			currentPlayer = currentPlayer.getNextPlayer();
		}
		determineWinner(pot);
	}

	private void determineWinner(PileOfCards pot)
	{
		Player winningPlayer = currentPlayer;
		int winningCardRank = 0;
		CardRankingList rankingList = new CardRankingList(turn.getTrump(), pot.getCardInformation(0));
		for (int i = 0; i < 4; i++)
		{
			int currentCardRank = rankingList.getRanking(pot.getCardInformation(i));
			if (currentCardRank > winningCardRank)
			{
				winningPlayer = currentPlayer;
				winningCardRank = currentCardRank;
			}
			currentPlayer = currentPlayer.getNextPlayer();
		}
		System.out.println(winningPlayer.getName() + " wins!");
		winner = winningPlayer;
	}

	public Player getWinner()
	{
		return winner;
	}
}
