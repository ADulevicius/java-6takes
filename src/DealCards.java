import java.util.ArrayList;

public class DealCards extends Deck{

	//Kortu dalinimas zaidimo pradzioje.
	public void doDeal(ArrayList<Player> players, ArrayList<Integer> Deck) {
		int step = 10;
		for(Player e : players) {
			for(int i = e.getPlayerNr()*step;
					i < (10+e.getPlayerNr()*step); 
					i++) {
				e.addCards( Deck.get(i) );
			}
		}
	}

	//Pradiniu stalo Line'u uzpildymas pirma korta
	public void doInitListCards(ArrayList<Player> players, ArrayList<Integer> Deck,
								ArrayList<ArrayList<Integer>> lines) {
		int numPlayers = players.size();
		int temp = 0;
		for(ArrayList<Integer> e : lines) {
			e.add(Deck.get(numPlayers*10+temp));
			temp++;
		}	
	}
	
}
