package player;

import card.Card;
import card.PileOfCards;
import card.Trump;

public abstract class Player
{
	private final String name;
	protected PileOfCards hand;
	private Player nextPlayer;

	public Player(String name)
	{
		this.name = name;
		hand = new PileOfCards();
	}

	public void addCard(Card card)
	{
		hand.addCard(card);
	}

	public void printHand()
	{
		System.out.println(name + "\'s hand:");
		hand.printCards();
	}

	public abstract int getBid(int currentBid);

	public abstract Trump getTrump();

	public abstract Card getCardPlay(PileOfCards pot, Trump trump);

	public void setNextPlayer(Player player)
	{
		nextPlayer = player;
	}

	public Player getNextPlayer()
	{
		return nextPlayer;
	}

	public String getName()
	{
		return name;
	}

	public PileOfCards getHand()
	{
		return hand;
	}
}
