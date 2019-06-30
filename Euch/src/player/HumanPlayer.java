package player;

import java.util.Scanner;

import card.Card;
import card.PileOfCards;
import card.Trump;

public class HumanPlayer extends Player
{
	public HumanPlayer(String name)
	{
		super(name);
	}

	@SuppressWarnings("resource")
	@Override
	public int getBid(int currentBid)
	{
		System.out.println("Choose your bid:");
		Scanner scan = new Scanner(System.in);
		return scan.nextInt();
	}

	@SuppressWarnings("resource")
	@Override
	public Trump getTrump()
	{
		System.out.println("Choose trump:");
		for (Trump trump : Trump.values())
		{
			System.out.println("[" + trump.ordinal() + "] " + trump.name());
		}
		Scanner scan = new Scanner(System.in);
		return Trump.values()[scan.nextInt()];
	}

	@SuppressWarnings("resource")
	@Override
	public Card getCardPlay(PileOfCards pot, Trump trump)
	{
		int index = 0;
		Card cardToPlay = null;
		if (pot.getCards().isEmpty() || !hand.hasLead(pot.getCardInformation(0).getSuit(), trump))
		{
			System.out.println("no have lead");
			for (Card card : hand.getCards())
			{
				System.out.println("[" + index + "] " + card.toString());
				index++;
			}
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
			cardToPlay = hand.getCard(choice);
		}
		else
		{
			System.out.println("have lead");
			PileOfCards availablePlays = new PileOfCards();
			for (Card card : hand.getCards())
			{
				if (card.isOfSuit(pot.getCardInformation(0).getSuit(), trump))
				{
					System.out.println("[" + index + "] " + card.toString());
					availablePlays.addCard(card);
					index++;
				}
			}
			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
			cardToPlay = hand.getCard(availablePlays.getCards().get(choice));
		}
		return cardToPlay;
	}
}
