package exercises14.exercise08.cardcontainerimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CardComparator implements Comparator<Card> {

	private boolean isAceHighest;
	char triumfSuit;
	

	public CardComparator(boolean isAceHighest, char triumfSuit) {
		this.isAceHighest = isAceHighest;
		this.triumfSuit = triumfSuit;
	}

	private int getSuitRank(Card card, char triumfSuit) {
		List<Card> cards = new ArrayList<Card>();
		
		Collections.sort(cards);
		
		
		List<Character> suitOrder = new ArrayList<Character>();

		suitOrder.add('C');
		suitOrder.add('D');
		suitOrder.add('H');
		suitOrder.add('S');
		
		if(triumfSuit == 'H') {
			suitOrder.remove(suitOrder.indexOf('H'));
			suitOrder.add(suitOrder.size(), 'H');
		}
		if(triumfSuit == 'D') {
			suitOrder.remove(suitOrder.indexOf('D'));
			suitOrder.add(suitOrder.size(), 'D');
		}
		if(triumfSuit == 'C') {
			suitOrder.remove(suitOrder.indexOf('C'));
			suitOrder.add(suitOrder.size(), 'C');
		}
		
		int rank = 0;
		for (char suit : suitOrder) {
			if (card.getSuit() == suit) {
				break;
			}
			rank++;
		}
		return rank;
	}
	
	public int compare(Card card1, Card card2) {
		int firstCardSuit = getSuitRank(card1, this.triumfSuit);
		int secondCardSuit = getSuitRank(card2, this.triumfSuit);
	
		int firstCardFace = card1.getFace();
		int secondCardFace = card2.getFace();
		
		// makes card face 14 if ace is the highest face
		if(isAceHighest) {
			if(firstCardFace == 1) {
				firstCardFace = 14;
			}
			if(secondCardFace == 1) {
				secondCardFace = 14;
			}
		}
		
		// compare suits first
		if (firstCardSuit < secondCardSuit) {
			return -1;
		}
		if (firstCardSuit > secondCardSuit) {
			return 1;
		}			
		// compare faces second
		if (firstCardFace < secondCardFace) {
			return -1;
		}
		if (firstCardFace > secondCardFace) {
			return 1;
		}
		return 0;
	}

	public static void main(String[] args) {
		List<Character> suitOrder = new ArrayList<Character>();

		suitOrder.add('C');
		suitOrder.add('D');
		suitOrder.add('H');
		suitOrder.add('S');
		
		System.out.println(suitOrder.size());
	}
}
