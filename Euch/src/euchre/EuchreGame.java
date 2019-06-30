package euchre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class EuchreGame 
{
	public static void main(String[] args)
	{
		//set up teams
		Player human = new Player("Human");
		human.setHuman(true);
		Player north = new Player("North");
		Player west = new Player("West");
		Player east = new Player("East");
		Team teamHuman = new Team(human, north);
		Team teamComp = new Team(west, east);
		Table table = new Table(human, west, north, east);
		table.setCurrentPlayer(1);
		
		//set up card objects
		ArrayList<Card> deck = new ArrayList<Card>();
		ArrayList<Card> powerIndex = new ArrayList<Card>();
		
		deck = newHand(deck, teamHuman, teamComp, table);
		
		printHand(teamHuman.getPlayer1());
		
		char trump = 'x';
		char lead = 'x';
		int tricksBid = 0;
		Card[] pot = new Card[4];
		int potIndex = 0;
		int winner = -1;
		boolean scoreCheck = false;
		
		//while(teamComp.getScore() < 32 && teamHuman.getScore() < 32)
		for (int j = 0; j < 500; j++)
		{
			//bidding war
			tricksBid = makeBids(table, teamHuman, teamComp);
			if (teamHuman.isWonBid())
			{
				System.out.println("Team human won the bid with " + tricksBid +  " bids!\n");
				if(teamHuman.getPlayer1().isWonBid())
				{
					trump = setBid(teamHuman.getPlayer1());
					outputTrump(teamHuman.getPlayer1(), trump);
				}
				else
				{
					trump = setBid(teamHuman.getPlayer2());
					outputTrump(teamHuman.getPlayer2(), trump);
				}
			}
			else
			{
				System.out.println("Team computer won the bid with " + tricksBid + " bids!\n");
				if(teamComp.getPlayer1().isWonBid())
				{
					trump = setBid(teamComp.getPlayer1());
					outputTrump(teamComp.getPlayer1(), trump);
				}
				else
				{
					trump = setBid(teamComp.getPlayer2());
					outputTrump(teamComp.getPlayer2(), trump);
				}
			}
			
			System.out.println();
			table.setCurrentPlayer(table.getCurrentDeal());
			for (int i = 0; i < 24; i++)
			{
				if (i % 4 == 0)
				{
					System.out.println(table.players[table.determineTurn(table)].getName() + " to lead!");
					lead = 'x';
				}
				//printHand(table.players[table.determineTurn(table)]);
				Card chosenCard = chooseCard(table.players[table.determineTurn(table)], lead);
				if (i % 4 == 0)
				{
					lead = chosenCard.getSuit();
					powerIndex = createPowerIndex(powerIndex, trump, lead);
				}
				pot[potIndex] = chosenCard;
				potIndex++;
				if (potIndex > 3)
				{
					potIndex = 0;
					scoreCheck = true;
				}
				System.out.println(table.players[table.determineTurn(table)].getName() + " plays the " 
				+ chosenCard.getCardName(chosenCard) + " of " + chosenCard.getSuitName(chosenCard));
				table.setCurrentPlayer(table.getCurrentPlayer() + 1);
				
				if (potIndex == 0 && scoreCheck)
				{
					System.out.println("Checking winner...");
					winner = determineWinner(pot, powerIndex, table.determineTurn(table));
					switch (winner)
					{
					case 0:
						System.out.println(table.players[winner + 1].getName());
						break;
					case 1:
						System.out.println(table.players[winner + 1].getName());
						break;
					case 2:
						System.out.println(table.players[winner + 1].getName());
						break;
					case 3:
						System.out.println(table.players[0].getName());
						break;
					case -1:
						System.out.println("Error.");
						break;
					}
					scoreCheck = false;
				}
			}
			deck = newHand(deck, teamHuman, teamComp, table);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	











	private static int determineWinner(Card[] pot, ArrayList<Card> powerIndex, int turn) 
	{
		int highest = -1;
		Card winningCard = new Card(0, 'x');
		int i = 0;
		int index = 0;
		for (Card c : powerIndex)
		{
			if (c.getPower() > highest)
			{
				for (Card card : pot)
				{
					if (card.getCardNumber() == c.getCardNumber() && card.getSuit() == c.getSuit())
					{
						highest = c.getPower();
						index = i;
					}
				}
			}
			i++;
		}
		
		winningCard = powerIndex.get(index);
		System.out.println("Winning card is " + winningCard.getCardName(winningCard) + " of " + winningCard.getSuitName(winningCard));
		int j = 0;
		for (Card c : pot)
		{
			if (c.getCardNumber() == winningCard.getCardNumber() && c.getSuit() == winningCard.getSuit())
			{
				return j;
			}
			j++;
		}
		
		return -1;
	}






















	private static ArrayList<Card> createPowerIndex(ArrayList<Card> powerIndex, char trump, char lead) 
	{
		powerIndex = setDeck(powerIndex);
		if(trump == 'h' || trump == 's' || trump == 'c' || trump == 'd') //trump is a suit
		{
			if(lead == trump) // if trump is led
			{
				for (Card c : powerIndex)
				{
					if (c.getSuit() == trump || (c.getOppB() == trump && c.getCardNumber() == 11))
					{
						if (c.getCardNumber() < 11)
						{
							c.setPower(c.getCardNumber() + 9);;
						}
						else if (c.getCardNumber() > 11)
						{
							c.setPower(c.getCardNumber() + 8);
						}
						else if (c.getCardNumber() == 11)
						{
							if (c.getSuit() == trump)
							{
								c.setPower(24);
							}
							else
							{
								c.setPower(23);
							}
						}
					}
				}
			}
			else //trump is not led
			{
				for (Card c : powerIndex)
				{
					if (c.getSuit() == trump || (c.getOppB() == trump && c.getCardNumber() == 11))
					{
						if (c.getCardNumber() < 11)
						{
							c.setPower(c.getCardNumber() + 9);;
						}
						else if (c.getCardNumber() > 11)
						{
							c.setPower(c.getCardNumber() + 8);
						}
						else if (c.getCardNumber() == 11)
						{
							if (c.getSuit() == trump)
							{
								c.setPower(24);
							}
							else
							{
								c.setPower(23);
							}
						}
					}
					else
					{
						if (c.getSuit() == lead && !(c.getOppB() == trump && c.getCardNumber() == 11))
						{
							c.setPower(c.getCardNumber() + 3);
						}
					}
				}
			}
		}
		else //high or low no trump
		{
			for (Card c : powerIndex)
			{
				if (c.getSuit() == lead)
				{
					c.setPower(c.getCardNumber() + 10);
				}
			}
		}
		return powerIndex;
	}
	
	private static void outputTrump(Player player, char trump) 
	{
		System.out.print(player.getName() + " chooses " + trumpOutput(trump));
	}
	
	private static String trumpOutput(char trump) 
	{
		switch(trump)
		{
		case 'h':
			return "hearts";
		case 'd':
			return "diamonds";
		case 's':
			return "spades";
		case 'c':
			return "clubs";
		case 'i':
			return "high no trump";
		case 'l':
			return "low no trump";
		}
		return "ERROR: TRUMP NOT FOUND";
	}

	@SuppressWarnings("resource")
	private static char setBid(Player player) 
	{
		char choice;
		char[] choices = {'s', 'h', 'c', 'd', 'i', 'l'};
		if(player.isHuman())
		{
			System.out.println();
			System.out.println("What will trump be?");
			System.out.println("Spades? [s]");
			System.out.println("Hearts? [h]");
			System.out.println("Clubs? [c]");
			System.out.println("Diamonds? [d]");
			System.out.println("High no trump? [i]");
			System.out.println("Low no trump? [l]");
			Scanner scan = new Scanner(System.in);
			choice = scan.next(".").charAt(0);
		}
		else
		{
			Random rand = new Random();
			choice = choices[rand.nextInt(choices.length)];
		}
		return choice;
	}
	
	@SuppressWarnings("resource")
	private static Card chooseCard(Player player, char lead) 
	{
		int choice;
		Card tempCard = new Card(0, 'x');
		if(player.isHuman())
		{
			System.out.println("\nWhich card will you play?");
			printPlayerHand(player, lead);
			Scanner scan = new Scanner(System.in);
			choice = scan.nextInt();
			tempCard = player.hand.get(choice);
			player.hand.remove(choice);
			System.out.println();
		}
		else
		{
			boolean hasLeadSuit = false;
			hasLeadSuit = checkForLead(player, lead);
			if (hasLeadSuit)
			{
				if (lead == 'x')
				{
					Random rand = new Random();
					choice = rand.nextInt(player.hand.size());
				}
				else
				{
					int lowerBound = -1;
					int upperBound = -1;
					boolean suitFound = false;
					boolean exit = false;
					int i = 0;
					for (Card c : player.hand)
					{
						if (!exit)
						{
							if (c.getSuit() == lead)
							{
								if (!suitFound)
								{
									lowerBound = i;
									suitFound = true;
								}
							}
							if (c.getSuit() != lead)
							{
								if(suitFound)
								{
									upperBound = i - 1;
									exit = true;
								}
							}
						}

						i++;
					}
					choice = determineChoice(lowerBound, upperBound, player);
				}
			}
			else
			{
				Random rand = new Random();
				choice = rand.nextInt(player.hand.size());
			}
			tempCard = player.hand.get(choice);
			player.hand.remove(choice);
		}
		
		return tempCard;
	}
	private static int determineChoice(int lowerBound, int upperBound, Player player) 
	{
		if(lowerBound == upperBound)
		{
			return lowerBound;
		}
		else if (lowerBound != -1 && upperBound == -1)
		{
			upperBound = player.hand.size() - 1;
		}
		Random rand = new Random();
		return rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
	}

	private static boolean checkForLead(Player player, char lead) 
	{
		if (lead == 'x')
		{
			return true;
		}
		else
		{
			for (Card c : player.hand)
			{
				if(c.getSuit() == lead)
				{
					return true;
				}
			}
		}
		return false;
	}

	private static void printPlayerHand(Player player, char lead) 
	{
		int i = 0;
		int j = 0;
		boolean hasLead = false;
		for (Card c : player.hand)
		{
			if (c.getSuit() == lead || lead == 'x')
			{
				System.out.println(c.getCardName(c) + " of " + c.getSuitName(c) + " [" + i + "]");
				hasLead = true;
			}
			i++;
		}
		if(!hasLead)
		{
			for (Card c : player.hand)
			{
				System.out.println(c.getCardName(c) + " of " + c.getSuitName(c) + " [" + j + "]");
				j++;
			}
		}
	}

	private static ArrayList<Card> newHand(ArrayList<Card> deck, Team teamHuman, Team teamComp, Table table) 
	{
		deck = setDeck(deck);
		deal(deck, teamHuman, teamComp, table);
		sort(teamComp, teamHuman);
		
		teamHuman.setWonBid(false);
		teamComp.setWonBid(false);
		teamHuman.getPlayer1().setWonBid(false);
		teamHuman.getPlayer2().setWonBid(false);
		teamComp.getPlayer1().setWonBid(false);
		teamComp.getPlayer2().setWonBid(false);
		return deck;
	}

	@SuppressWarnings("resource")
	private static int makeBids(Table table, Team teamHuman, Team teamComp) 
	{
		String winner = "none";
		int currentBid = 0;
		int p = 0;
		int bid = 0;
		int index = table.determineDeal(table);
		for (int i = 0; i < 4; i++)
		{
			if (table.players[index].isHuman())
			{
				if (currentBid != 6)
				{
					System.out.println("\nHow many do you bid?");
					printHumanBidOptions(currentBid);
					Scanner scan = new Scanner(System.in);
					boolean verBid = false;
					do
					{
						boolean isInt = false;
						while(!isInt)
						{
						    if (scan.hasNextInt()) 
						    {
						        bid = scan.nextInt();
						        isInt = true;
						    }
						    else 
						    {
						    	System.out.println("Not a valid input.");
						    	scan = new Scanner(System.in);
						        isInt = false;
						    }
						}
						
						verBid = verifyBid(bid, currentBid);
						if(!verBid)
						{
							System.out.println("Please input a valid bid.");
						}
					} while (!verBid);
					
					if (bid != 0)
					{
						System.out.println("Human bids " + bid);
						currentBid = bid;
						winner = table.players[index].getName();
					}
					else
					{
						System.out.println("Human passes!");
					}
				}
				else
				{
					System.out.println("Human passes!");
				}
			}
			else
			{
				Random rand = new Random();
				p = rand.nextInt(101);
				bid = determineBid(p);
				
				if (bid > currentBid)
				{
					System.out.println(table.players[index].getName() + " bids " + bid);
					currentBid = bid;
					winner = table.players[index].getName();
				}
				else
				{
					System.out.println(table.players[index].getName() + " passes!");
				}
			}
			index++;
			if (index > 3)
			{
				index = 0;
			}
		}
		System.out.println();
		if (winner.equals("none"))
		{
			if (index == 0)
			{
				index = 4;
			}
			winner = table.players[index - 1].getName();
			currentBid = 3;
		}
		for (Player player : table.players)
		{
			if (player.getName().equals(winner))
			{
				if (player.getName().equals("West") || player.getName().equals("East"))
				{
					teamComp.setWonBid(true);
					player.setWonBid(true);
				}
				else
				{
					teamHuman.setWonBid(true);
					player.setWonBid(true);
				}
			}
		}
		return currentBid;
	}

	private static boolean verifyBid(int bid, int currentBid) 
	{
		if (bid == 0)
		{
			return true;
		}
		if (bid > 6 || bid <= currentBid || bid < 3)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	private static void printHumanBidOptions(int currentBid) 
	{
		System.out.println("Pass" + " [0]");
		for (int i = 3; i < 7; i++)
		{
			if (i > currentBid)
			{
				System.out.println("Bid " + (i) + " [" + (i) + "]");
			}
		}
	}

	private static int determineBid(int p) 
	{
		if (p < 61)
		{
			return 0;
		}
		else if (p < 62)
		{
			return 6;
		}
		else if (p < 67)
		{
			return 5;
		}
		else if (p < 81)
		{
			return 4;
		}
		else return 3;
	}

	private static void sortHand(Player player) 
	{
		Collections.sort(player.hand, new Comparator<Card>()
		  {
			public int compare(Card c1, Card c2) 
		        {
		            return c1.getSuit() - c2.getSuit();
		        }
		  });
	}
	
	private static void sort(Team human, Team computer)
	{
		sortHand(human.getPlayer1());
		sortHand(human.getPlayer2());
		sortHand(computer.getPlayer1());
		sortHand(computer.getPlayer2());
	}
	
	private static void printHand(Player player) 
	{
		System.out.println(player.getName() + "\'s hand:");
		for (Card c : player.hand)
		{
			System.out.println(c.getCardName(c) + " of " + c.getSuitName(c));
		}
		System.out.println();
	}

	private static void deal(ArrayList<Card> deck, Team human, Team computer, Table table) 
	{
		table.printDealer(table);
		dealHand(human.getPlayer1(), deck);
		dealHand(human.getPlayer2(), deck);
		dealHand(computer.getPlayer1(), deck);
		dealHand(computer.getPlayer2(), deck);
		table.setCurrentDeal(table.getCurrentDeal() + 1);
	}

	private static void dealHand(Player player, ArrayList<Card> deck) 
	{
		for (int i = 0; i < 6; i++)
		{
			Random rand = new Random();
			int index = rand.nextInt(deck.size());
			player.addToHand(deck.get(index).getCardNumber(), deck.get(index).getSuit());
			deck.remove(index);
		}
	}

	private static ArrayList<Card> setDeck(ArrayList<Card> deck) 
	{
		char[] suits = {'h', 'd', 'c', 's'};
		Card tempCard = new Card(0, 'x');
		
		for (char s : suits)
		{
			for (int i = 9; i < 15; i++)
			{
				tempCard = new Card(i, s);
				deck.add(tempCard);
			}
		}
		return deck;
	}
}