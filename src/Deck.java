import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	int cardNR;
    private static final int DECK_SIZE = 104;

    
	public Deck(int cardNR) {
		super();
		this.cardNR = cardNR;
	}
	
	public Deck() {
	};

	public int getCardNR() {
		return cardNR;
	}

	public void setCardNR(int cardNR) {
		this.cardNR = cardNR;
	}
	
	public ArrayList<Integer> NewDeck() {
		ArrayList<Integer> deck_Clean = new ArrayList<Integer>();
		for (int i = 1; i <= DECK_SIZE; ++i) {
			deck_Clean.add(i);
		}
		Collections.shuffle(deck_Clean);
		return deck_Clean;
	}
	
}
