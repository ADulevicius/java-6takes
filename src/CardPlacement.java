import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class CardPlacement {
	
	//Surandama eilute i kuria bus dedama korta
	public int cardPlacementFindLineMinDif(ArrayList<ArrayList<Integer>> lines, ArrayList<Integer> chosenCards) {
		int minLine = 666;
		int minDif = 0;
		double Dif;
		for (int i=0; i<4; i++) {
			Dif =chosenCards.get(0) - lines.get(i).get(lines.get(i).size()-1);
			if( (minDif == 0) && (Dif > 0) ) {
				minDif = (int)Dif;
				minLine = i;
			}else if( (Dif > 0) && (Dif < minDif) ) {
				minDif = (int)Dif;
				minLine = i;
			}		
		}
		return minLine;
	}

	//Pasirinktu kortu HashList'as konvertuojamas i list'a ir surikiuojamas didejimo tvarka
	public ArrayList<Integer> sortPlacementCards_hash(HashMap<Integer,Integer> chosenCards) {
	       ArrayList<Integer> arrayList = new ArrayList<>();
	       for (Integer e : chosenCards.keySet()) {
	           arrayList.add(e);
	       }
	       Collections.sort(arrayList);
	       return arrayList;
	}

	//Pagrintinis kortu talpinimo i eilutes modulis
	public void cardPlacementDecision_hash(ArrayList<ArrayList<Integer>> lines,
											ArrayList<Integer> arrayChosenCards,											
											HashMap<Integer,Integer> hmapChosenCards,
											ArrayList<Player> players) {

		int cardToPlace = arrayChosenCards.get(0);
		int cardToPlaceOwner = hmapChosenCards.get(cardToPlace);
		Player cardOwnerObj = players.get(cardToPlaceOwner);
		int tempIndex = cardPlacementFindLineMinDif(lines,arrayChosenCards);
		if (tempIndex == 666) {
			System.out.println("Card can't be placed to any line.");
			cardDoesNotFitToAnyLine(lines, arrayChosenCards, hmapChosenCards, players);
			return;
		}
		int tempSize = lines.get(tempIndex).size();
		if (tempSize == 5) {
			System.out.println("Line " + Integer.toString(tempIndex+1) + " is full. Player takes all cards from the line and then the new card is placed there.");
			cardMovementFromLineToBeaten(lines.get(tempIndex),cardOwnerObj);
			cardLineFreshCard(lines.get(tempIndex),cardToPlace);		
		}else {
			lines.get(tempIndex).add(arrayChosenCards.remove(0));		
		}	
	}
	
	//Modulis tvarkymui kai korto netelpa i jokia eilute
	public void cardDoesNotFitToAnyLine(ArrayList<ArrayList<Integer>> lines,
										ArrayList<Integer> arrayChosenCards,
										HashMap<Integer,Integer> hmapChosenCards,
										ArrayList<Player> players) {
		Scanner sc = new Scanner(System.in);
		int cardThatDoesNotFit = arrayChosenCards.get(0); // korta kuria reikia ideti i pasirinkta eitule
		int playerNr = hmapChosenCards.get(cardThatDoesNotFit);	//zaidejo numeris
		
		System.out.println("Player number "+ playerNr + ", your chosen card doesn't fit to any line. Choose which line you will take:");
		//Liniju atspausdinimas informavimui//
		for(int i=0; i<lines.size(); i++) {
			System.out.println("Line " + Integer.toString(i+1) + ": " + lines.get(i));
		}
		//////////////////////////////////////
		int chosenLine = sc.nextInt();
		cardMovementFromLineToBeaten(lines.get(chosenLine-1), players.get(playerNr));
		cardLineFreshCard(lines.get(chosenLine-1), cardThatDoesNotFit);
		arrayChosenCards.remove(0);
	}
	
	//Iraso viena eilute i kirstas kortas
	public void cardMovementFromLineToBeaten(ArrayList<Integer> line, Player player) {
		player.addCardsBeaten(line);
	}
	
	//Isvalo eilute ir iraso nauja korta
	public void cardLineFreshCard(ArrayList<Integer> line, int card) {
		line.clear();
		line.add(card);
	}

}
