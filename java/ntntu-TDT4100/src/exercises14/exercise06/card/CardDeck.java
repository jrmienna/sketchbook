package exercises14.exercise06.card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardDeck implements CardContainer {

	private List<Card> cardDeck;

	public CardDeck(int n) {
		cardDeck = new ArrayList<Card>();
		char[] suits = { 'S', 'H', 'D', 'C' };
		for (char suit: suits) {
			for (int face = 1; face <= n; face++) {
				cardDeck.add(new Card(suit, face));
			}
		}
	}
	
	public Iterator<Card> iterator() {
		return cardDeck.iterator();
	}
	
	public int getCardCount() {
		return cardDeck.size();
	}

	public Card getCard(int n) {
		if (getCardCount() < n || n < 0) {
			throw new IllegalArgumentException("card out of range");
		}
		return cardDeck.get(n);
	}

	public void deal(CardHand hand, int n) {
		for (int i = 0; i < n; i++) {
			int top = getCardCount() - 1;
			hand.addCard(cardDeck.remove(top));
		}
	}

	public void shufflePerfectly() {
		List<Card> deck1 = new ArrayList<Card>();
		List<Card> deck2 = new ArrayList<Card>();
		
		int n = getCardCount()/2;
		for (int i = 0; i < n; i++) {
			int half = getCardCount()/2;
			deck2.add(cardDeck.remove(half));
			deck1.add(cardDeck.remove(0));
		}
		for(int j = 0; j < n; j++) {
			cardDeck.add(deck1.get(j));
			cardDeck.add(deck2.get(j));
		}
	}

	public String toString() {
		return cardDeck.toString();
	}
	public static void main(String[] args) {
		CardDeck deck = new CardDeck(13);
		System.out.println(deck.toString());
	}
}
