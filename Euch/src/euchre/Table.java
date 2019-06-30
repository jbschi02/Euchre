package euchre;

public class Table 
{
	Player[] players = new Player[4];
	private int currentPlayer;
	private int currentDeal;
	
	Table(Player player1, Player player2, Player player3, Player player4)
	{
		players[0] = player1;
		players[1] = player2;
		players[2] = player3;
		players[3] = player4;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public int determineTurn(Table table)
	{
		return table.getCurrentPlayer() % 4;
	}
	
	public int determineDeal(Table table)
	{
		return table.getCurrentDeal() % 4;
	}

	public int getCurrentDeal() {
		return currentDeal;
	}

	public void setCurrentDeal(int currentDeal) {
		this.currentDeal = currentDeal;
	}

	public void printDealer(Table table) {
		System.out.println(table.players[table.determineDeal(table)].getName() + " deals");
		System.out.println();
	}
}
