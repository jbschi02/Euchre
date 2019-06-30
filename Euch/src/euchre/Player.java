package euchre;

import java.util.ArrayList;

public class Player 
{
	ArrayList<Card> hand;
	private String name;
	private boolean isHuman = false;
	private boolean wonBid = false;
	
	Player(String n)
	{
		hand = new ArrayList<Card>();
		setName(n);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addToHand(int c, char s)
	{
		Card card = new Card(c, s);
		hand.add(card);
	}

	public boolean isHuman() {
		return isHuman;
	}

	public void setHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}

	public boolean isWonBid() {
		return wonBid;
	}

	public void setWonBid(boolean wonBid) {
		this.wonBid = wonBid;
	}
}
