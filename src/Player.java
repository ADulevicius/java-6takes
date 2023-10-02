import java.util.ArrayList;

public class Player {
	int playerNr;
	int points;
	ArrayList<Integer> cardsHand = new ArrayList<>();
	ArrayList<Integer> cardsBeaten = new ArrayList<>();
	
	
	public Player(int playerNr, int points) {
		super();
		this.playerNr = playerNr;
		this.points = points;
	}
	
	public Player() {};

	public int getPlayerNr() {
		return playerNr;
	}

	public void setPlayerNr(int playerNr) {
		this.playerNr = playerNr;
	}

	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}

	public void setCardsHand(ArrayList<Integer> cards) {
		this.cardsHand = cards;
	}
	
	public ArrayList<Integer> getCardsHand() {
		return cardsHand;
	}
	
	public void addCards(int card) {	
		this.cardsHand.add(card);
	}
	
	public void addCardsBeaten(ArrayList<Integer> cardsBeaten) {
		this.cardsBeaten.addAll(cardsBeaten);
	}
	
	public void addCardsBeaten(int card) {
		this.cardsBeaten.add(card);
	}
	
	public ArrayList<Integer> getCardsBeaten() {
		return cardsBeaten;
	}


	


	

	
}


