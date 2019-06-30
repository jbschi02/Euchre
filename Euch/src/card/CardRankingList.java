package card;

import java.util.HashMap;

public class CardRankingList
{
	private final Trump trump;
	private final Suit lead;
	private HashMap<String, Integer> cardRank = new HashMap<String, Integer>();

	public CardRankingList(Trump trump, Card lead)
	{
		this.trump = trump;
		this.lead = lead.getSuit();
		switch (trump)
		{
		case Low:
			createRankingListLow();
			break;
		case High:
			createRankingListHigh();
			break;
		default:
			createRankingListSuit(lead.getSuitAsTrump());
		}
	}

	private void createRankingListSuit(Trump leadAsTrump)
	{
		Card leftBower = null;
		if (trump == Trump.Clubs)
		{
			leftBower = new Card(11, Suit.Spades);
		}
		else if (trump == Trump.Spades)
		{
			leftBower = new Card(11, Suit.Clubs);
		}
		else if (trump == Trump.Hearts)
		{
			leftBower = new Card(11, Suit.Diamonds);
		}
		else if (trump == Trump.Diamonds)
		{
			leftBower = new Card(11, Suit.Hearts);
		}

		PileOfCards deck = new PileOfCards();
		deck.createNewEuchreDeck();
		if (trump == leadAsTrump)
		{
			for (Card card : deck.getCards())
			{
				if (card.getRank() == 11 && card.getSuit().toString().equals(lead.toString()))
				{
					cardRank.put(card.toString(), 8);
				}
				else if (card.getRank() == 11 && card.getSuit().toString().equals(leftBower.getSuit().toString()))
				{
					cardRank.put(card.toString(), 7);
				}
				else if (card.getRank() == 14 && card.getSuit().toString().equals(lead.toString()))
				{
					cardRank.put(card.toString(), 6);
				}
				else if (card.getRank() == 13 && card.getSuit().toString().equals(lead.toString()))
				{
					cardRank.put(card.toString(), 5);
				}
				else if (card.getRank() == 12 && card.getSuit().toString().equals(lead.toString()))
				{
					cardRank.put(card.toString(), 4);
				}
				else if (card.getRank() == 10 && card.getSuit().toString().equals(lead.toString()))
				{
					cardRank.put(card.toString(), 3);
				}
				else if (card.getRank() == 9 && card.getSuit().toString().equals(lead.toString()))
				{
					cardRank.put(card.toString(), 2);
				}
				else
				{
					cardRank.put(card.toString(), 1);
				}
			}
		}
		else
		{
			for (Card card : deck.getCards())
			{
				if (card.getRank() == 11 && card.getSuitAsTrump() == trump)
				{
					cardRank.put(card.toString(), 13);
				}
				else if (card.getRank() == 11 && card.getSuit().toString().equals(leftBower.getSuit().toString()))
				{
					cardRank.put(card.toString(), 12);
				}
				else if (card.getRank() == 14 && card.getSuitAsTrump() == trump)
				{
					cardRank.put(card.toString(), 11);
				}
				else if (card.getRank() == 13 && card.getSuitAsTrump() == trump)
				{
					cardRank.put(card.toString(), 10);
				}
				else if (card.getRank() == 12 && card.getSuitAsTrump() == trump)
				{
					cardRank.put(card.toString(), 9);
				}
				else if (card.getRank() == 10 && card.getSuitAsTrump() == trump)
				{
					cardRank.put(card.toString(), 8);
				}
				else if (card.getRank() == 9 && card.getSuitAsTrump() == trump)
				{
					cardRank.put(card.toString(), 7);
				}
				else if (card.getRank() == 14 && card.getSuit().toString().equals(lead.toString()))
				{
					cardRank.put(card.toString(), 6);
				}
				else if (card.getRank() == 13 && card.getSuit().toString().equals(lead.toString()))
				{
					cardRank.put(card.toString(), 5);
				}
				else if (card.getRank() == 12 && card.getSuit().toString().equals(lead.toString()))
				{
					cardRank.put(card.toString(), 4);
				}
				else if (card.getRank() == 10 && card.getSuit().toString().equals(lead.toString()))
				{
					cardRank.put(card.toString(), 3);
				}
				else if (card.getRank() == 9 && card.getSuit().toString().equals(lead.toString()))
				{
					cardRank.put(card.toString(), 2);
				}
				else
				{
					cardRank.put(card.toString(), 1);
				}
			}
		}
	}

	private void createRankingListLow()
	{
		PileOfCards deck = new PileOfCards();
		deck.createNewEuchreDeck();
		for (Card card : deck.getCards())
		{
			if (card.getRank() == 9 && card.getSuit() == lead)
			{
				cardRank.put(card.toString(), 7);
			}
			else if (card.getRank() == 10 && card.getSuit() == lead)
			{
				cardRank.put(card.toString(), 6);
			}
			else if (card.getRank() == 11 && card.getSuit() == lead)
			{
				cardRank.put(card.toString(), 5);
			}
			else if (card.getRank() == 12 && card.getSuit() == lead)
			{
				cardRank.put(card.toString(), 4);
			}
			else if (card.getRank() == 13 && card.getSuit() == lead)
			{
				cardRank.put(card.toString(), 3);
			}
			else if (card.getRank() == 14 && card.getSuit() == lead)
			{
				cardRank.put(card.toString(), 2);
			}
			else
			{
				cardRank.put(card.toString(), 1);
			}
		}
	}

	private void createRankingListHigh()
	{
		PileOfCards deck = new PileOfCards();
		deck.createNewEuchreDeck();
		for (Card card : deck.getCards())
		{
			if (card.getRank() == 14 && card.getSuit() == lead)
			{
				cardRank.put(card.toString(), 7);
			}
			else if (card.getRank() == 13 && card.getSuit() == lead)
			{
				cardRank.put(card.toString(), 6);
			}
			else if (card.getRank() == 12 && card.getSuit() == lead)
			{
				cardRank.put(card.toString(), 5);
			}
			else if (card.getRank() == 11 && card.getSuit() == lead)
			{
				cardRank.put(card.toString(), 4);
			}
			else if (card.getRank() == 10 && card.getSuit() == lead)
			{
				cardRank.put(card.toString(), 3);
			}
			else if (card.getRank() == 9 && card.getSuit() == lead)
			{
				cardRank.put(card.toString(), 2);
			}
			else
			{
				cardRank.put(card.toString(), 1);
			}
		}
	}

	public int getRanking(Card cardInformation)
	{
		return cardRank.get(cardInformation.toString());
	}
}
