package player;

import java.util.Random;

import card.Card;
import card.PileOfCards;
import card.Trump;

public class ComputerPlayer extends Player
{

	public ComputerPlayer(String name)
	{
		super(name);
	}

	@Override
	public int getBid(int currentBid)
	{
		Random rand = new Random();
		int randomChoice = rand.nextInt(101);
		if (randomChoice < 61)
		{
			return 0;
		}
		else if (randomChoice < 62)
		{
			return 6;
		}
		else if (randomChoice < 67)
		{
			return 5;
		}
		else if (randomChoice < 77)
		{
			return 4;
		}
		else
		{
			return 3;
		}
	}

	@Override
	public Trump getTrump()
	{
		Random rand = new Random();
		return Trump.values()[rand.nextInt(6)];
	}

	@Override
	public Card getCardPlay(PileOfCards pot, Trump trump)
	{
		Random rand = new Random();
		int choice;
		Card cardToPlay;
		if (pot.getCards().isEmpty() || !hand.hasLead(pot.getCardInformation(0).getSuit(), trump))
		{
			choice = rand.nextInt(hand.getCards().size());
			cardToPlay = hand.getCard(choice);
		}
		else
		{
			PileOfCards availablePlays = new PileOfCards();
			for (Card card : hand.getCards())
			{
				if (card.isOfSuit(pot.getCardInformation(0).getSuit(), trump))
				{
					availablePlays.addCard(card);
				}
			}
			rand = new Random();
			choice = rand.nextInt(availablePlays.getCards().size());
			cardToPlay = hand.getCard(availablePlays.getCard(choice));
		}
		return cardToPlay;
	}
}
