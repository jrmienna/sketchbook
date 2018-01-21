package exercises14.exercise05.card;

import java.util.ArrayList;
import java.util.List;

public class CardHand {

	List<Card> cardHand = new ArrayList<Card>();

	
	public CardHand() {
		cardHand = new ArrayList<Card>();
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
