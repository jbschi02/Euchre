package card;

import java.util.ArrayList;
import java.util.Collections;

public class PileOfCards
{
	private ArrayList<Card> cards;

	public PileOfCards()
	{
		cards = new ArrayList<Card>();
	}

	public void createNewEuchreDeck()
	{
		for (Suit suit : Suit.values())
		{
			for (int i = 9; i < 15; i++)
			{
				cards.add(new Card(i, suit));
			}
		}
	}

	public void printCards()
	{
		for (Card card : cards)
		{
			card.printCard();
		}
	}

	public void shuffle()
	{
		Collections.shuffle(cards);
	}

	public ArrayList<Card> getCards()
	{
		return cards;
	}

	public void addCard(Card card)
	{
		cards.add(card);
	}

	public Card getCard(int index)
	{
		Card card = cards.get(index);
		cards.remove(index);
		return card;
	}

	public Card getCardInformation(int index)
	{
		return cards.get(index);
	}

	/**
	 * Gets the top card on the deck.
	 *
	 * @return The top {@link Card} on the deck.
	 */
	public Card draw()
	{
		return getCard(0);
	}

	public boolean hasLead(Suit lead, Trump trump)
	{
		for (Card card : cards)
		{
			if (card.getSuit() == lead)
			{
				return true;
			}
			else if (card.isLeftBower(trump) && lead == card.getOppositeSuit())
			{
				return true;
			}
		}
		return false;
	}

	public void setCards(ArrayList<Card> cards)
	{
		this.cards = cards;
	}

	public Card getCard(Card card)
	{
		int index = 0;
		int cardToGet = 0;
		for (Card cardToSearch : cards)
		{
			if (cardToSearch == card)
			{
				cardToGet = index;
				break;
			}
			index++;
		}
		return getCard(cardToGet);
	}
}
