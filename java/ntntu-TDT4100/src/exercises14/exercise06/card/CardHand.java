package exercises14.exercise06.card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardHand implements CardContainer {

	private List<Card> cardHand;
	
	public CardHand() {
		cardHand = new ArrayList<Card>();
	}
	
	public Iterator<Card> iterator() {
		return cardHand.iterator();
	}
	
	public int getCardCount() {
		return cardHand.size();
	}

	public Card getCard(int n) {
		if (n < 0 || n > getCardCount()) {
			throw new IllegalArgumentException("out of reach");
		}
		return cardHand.get(n);
	}

	public void addCard(Card card) {
		cardHand.add(card);
	}

	public Card play(int n) {
		if(n < 0 || n > getCardCount()) {
			throw new IllegalStateException("not enough cards in hand");
		}
		return cardHand.remove(n);
	}

	public String toString() {
		return cardHand.toString();
	}
}
