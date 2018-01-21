package exercises14.exercise06.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Card implements Comparable<Card> {

	private char suit;
	private int face;

	public Card(char cardSuit, int cardFace) {
		if (cardFace < 1 || cardFace > 13) {
			throw new IllegalArgumentException("illegal face");
		}
		if (cardSuit != 'S' && cardSuit != 'H' && cardSuit != 'D'
				&& cardSuit != 'C') {
			throw new IllegalArgumentException("Illegal suit");
		}

		suit = cardSuit;
		face = cardFace;
	}

	public char getSuit() {
		return suit;
	}

	public int getFace() {
		return face;
	}

	public String toString() {
		return "" + getSuit() + getFace();
	}
	
	public int compareTo(Card card) {
		List<Card> cards = new ArrayList<Card>();
		cards.add(this);
		cards.add(card);
		
		Comparator<Card> comparator = new CardComparator(false, ' ');
		Collections.sort(cards, comparator);
		
		System.out.println(cards);
		
		if(comparator.compare(this, card) == 0) {
			return 0;
		}
		if(cards.indexOf(this) < cards.indexOf(card)) {
			return -1;
		}
		if(cards.indexOf(this) > cards.indexOf(card)) {
			return 1;
		}
		return 0;
	}
	

	public static void main(String[] args) {
		Card card1 = new Card('H', 10);
		Card card2 = new Card('H', 10);

		System.out.println(card1.compareTo(card2));
	}
}
