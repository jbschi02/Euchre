package euchre;

public class Card 
{
	private int cardNumber;
	private char suit;
	private int power;
	private char oppB;
	
	Card (int c, char s)
	{
		setCardNumber(c);
		setSuit(s);
		power = -1;
	}

	public int getCardNumber() 
	{
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public char getSuit() {
		return suit;
	}

	public void setSuit(char suit) {
		this.suit = suit;
		if (suit == 'h')
		{
			this.setOppB('d');
		}
		else if (suit == 'd')
		{
			this.setOppB('h');
		}
		else if (suit == 'c')
		{
			this.setOppB('s');
		}
		else
		{
			this.setOppB('c');
		}
	}
	public String getSuitName(Card c)
	{
		switch (c.suit)
		{
		case 'h':
			return "hearts";
		case 's':
			return "spades";
		case 'd':
			return "diamonds";
		case 'c':
			return "clubs";
		}
		return "ERROR: SUIT NOT FOUND";
	}
	
	public String getCardName(Card c)
	{
		if (c.cardNumber < 11)
		{
			return Integer.toString(c.getCardNumber());
		}
		else if (c.cardNumber == 11)
		{
			return "Jack";
		}
		else if (c.cardNumber == 12)
		{
			return "Queen";
		}
		else if (c.cardNumber == 13)
		{
			return "King";
		}
		else if(c.cardNumber == 14)
		{
			return "Ace";
		}
		else return "ERROR: COULD NOT FIND CARD NUMBER";
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public char getOppB() {
		return oppB;
	}

	public void setOppB(char oppB) {
		this.oppB = oppB;
	}
}
