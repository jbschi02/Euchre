package card;

public class Card
{
	private int rank;
	private Suit suit;

	public Card(int rank, Suit suit)
	{
		this.rank = rank;
		this.suit = suit;
	}

	public void printCard()
	{
		switch (rank)
		{
		case 14:
			System.out.println("Ace of " + suit.toString());
			break;
		case 13:
			System.out.println("King of " + suit.toString());
			break;
		case 12:
			System.out.println("Queen of " + suit.toString());
			break;
		case 11:
			System.out.println("Jack of " + suit.toString());
			break;
		case 10:
		case 9:
		default:
			System.out.println(rank + " of " + suit.toString());
			break;
		}
	}

	@Override
	public String toString()
	{
		switch (rank)
		{
		case 14:
			return "Ace of " + suit.toString();
		case 13:
			return "King of " + suit.toString();
		case 12:
			return "Queen of " + suit.toString();
		case 11:
			return "Jack of " + suit.toString();
		case 10:
		case 9:
		default:
			return rank + " of " + suit.toString();
		}
	}

	public boolean isOfSuit(Suit suitToCompare, Trump trump)
	{
		if (trump != Trump.Low && trump != Trump.High && isLeftBower(trump) && suitToCompare == getOppositeSuit())
		{
			return true;
		}
		return suitToCompare.toString().equals(suit.toString());
	}

	public Suit getOppositeSuit()
	{
		if (suit == Suit.Hearts)
		{
			return Suit.Diamonds;
		}
		if (suit == Suit.Diamonds)
		{
			return Suit.Hearts;
		}
		if (suit == Suit.Clubs)
		{
			return Suit.Spades;
		}
		if (suit == Suit.Spades)
		{
			return Suit.Clubs;
		}
		return null;
	}

	private Suit getTrumpAsSuit(Trump trump)
	{
		if (trump.toString().equals(Suit.Clubs.toString()))
		{
			return Suit.Clubs;
		}
		if (trump.toString().equals(Suit.Spades.toString()))
		{
			return Suit.Spades;
		}
		if (trump.toString().equals(Suit.Hearts.toString()))
		{
			return Suit.Hearts;
		}
		if (trump.toString().equals(Suit.Diamonds.toString()))
		{
			return Suit.Diamonds;
		}
		return null;
	}

	public Trump getSuitAsTrump()
	{
		if (suit.toString().equals(Trump.Clubs.toString()))
		{
			return Trump.Clubs;
		}
		if (suit.toString().equals(Trump.Spades.toString()))
		{
			return Trump.Spades;
		}
		if (suit.toString().equals(Trump.Hearts.toString()))
		{
			return Trump.Hearts;
		}
		if (suit.toString().equals(Trump.Diamonds.toString()))
		{
			return Trump.Diamonds;
		}
		return null;
	}

	public int getRank()
	{
		return rank;
	}

	public void setRank(int rank)
	{
		this.rank = rank;
	}

	public Suit getSuit()
	{
		return suit;
	}

	public void setSuit(Suit suit)
	{
		this.suit = suit;
	}

	public boolean isLeftBower(Trump trump)
	{
		if (rank == 11)
		{
			if (trump == Trump.Clubs && suit == Suit.Spades)
			{
				return true;
			}
			if (trump == Trump.Spades && suit == Suit.Clubs)
			{
				return true;
			}
			if (trump == Trump.Hearts && suit == Suit.Diamonds)
			{
				return true;
			}
			if (trump == Trump.Diamonds && suit == Suit.Hearts)
			{
				return true;
			}
		}
		return false;
	}
}
