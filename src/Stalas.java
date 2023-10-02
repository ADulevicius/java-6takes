import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Stalas {
	
	public void playGame() {
		ArrayList<Player> players = new ArrayList<>();
		Deck deck = new Deck();
		DealCards dc = new DealCards();
		Lines linesClass = new Lines();  // galiu panaikinti ir daryti tiesiog new Lines().initializeLines();
		ArrayList<ArrayList<Integer>> tableLines = linesClass.initializeLines();
		ArrayList<Integer> sortedPlacementCards= new ArrayList<>();
		
		ArrayList<Integer> newDeck = deck.NewDeck();
		
		players.add(new Player(0,0));
		players.add(new Player(1,0));
		players.add(new Player(2,0));
		players.add(new Player(3,0));
		
		dc.doDeal(players, newDeck);
		dc.doInitListCards(players, newDeck, tableLines);		

		//////////////////////////////PAGRINDINIS LOOP'AS//////////////////////////////////////////
		do {
			HashMap<Integer,Integer> placedCardList_2 = playerChooseCards_hash(players);		//pakeisk placedCardList pavadinima
			sortedPlacementCards = new CardPlacement().sortPlacementCards_hash(placedCardList_2);
			updateGVarUsedCards(sortedPlacementCards);
			do {			
				new CardPlacement().cardPlacementDecision_hash(tableLines, sortedPlacementCards, placedCardList_2, players);
				System.out.println(tableLines);
				System.out.println(sortedPlacementCards);
			}while(sortedPlacementCards.size() != 0);			
			checkIfAllCardsPlayed(players);
		}while(!checkIfAllCardsPlayed(players));		
		calculatePoints(players);
		printPenaltyPoints(players);
	}
	
	public void updateGVarUsedCards(ArrayList<Integer> arrayChosenCards) {
		GVar.gCardsUsed.addAll(arrayChosenCards);
		System.out.println("Used cards: " + GVar.gCardsUsed);
	}
	
	public HashMap<Integer,Integer> playerChooseCards_hash(ArrayList<Player> players){
		
		HashMap<Integer,Integer> hmap = new HashMap<>();
		
		Scanner sc = new Scanner(System.in);
		String korta;
		
		for (Player e : players) {
			System.out.println(e.getPlayerNr() +  " player, you have these cards:" + e.getCardsHand().toString());
			System.out.println("Choose a card to place on the table:");
			korta = sc.nextLine();
			hmap.put(Integer.valueOf(korta), e.getPlayerNr());
			players.get(hmap.size()-1).cardsHand.remove(Integer.valueOf(korta));
		}
		return hmap;
	}
	
	public boolean checkIfAllCardsPlayed(ArrayList<Player> players) {
		if (GVar.gCardsUsed.size() >= players.size()*2) //*10
			return true;
		else return false;
	}
	
	public void calculatePoints(ArrayList<Player> players) {
		for (Player e : players) {
			for (int card : e.getCardsBeaten()) {
				if ( (card % 5) == 0 && (card % 10) != 0 && (card % 11) != 0) {
					e.addPoints(2);
				}else if ( (card % 10) == 0 ){
					e.addPoints(3);
				}else if ( (card % 11) == 0 ){
					e.addPoints(5);
				}else
					e.addPoints(1);
			}
		}
	}
	
	public void printPenaltyPoints(ArrayList<Player> players) {
		for (Player e : players)
			System.out.println("Player " + e.getPlayerNr() + " got " + e.getPoints() + " penalty points.");
	}
	
}
