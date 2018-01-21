package exercises14.exercise08.cardcontainerimpl;

import java.util.ArrayList;
import java.util.List;

public class CardDeck extends CardContainerImpl {
	
	public CardDeck(int numberOfCards) {
		buildDeck(numberOfCards);
	}

	public void buildDeck(int n) {
		if(n <= super.getMaxCardCount()) {
			char[] suits = { 'S', 'H', 'D', 'C' };
			for (char suit: suits) {
				for (int face = 1; face <= n; face++) {
					cardContainer.add(new Card(suit, face));
				}
			}
		}
	}
	
	public void deal(CardHand hand, int n) {
		for (int i = 0; i < n; i++) {
			int top = getCardCount() - 1;
			hand.addCard(cardContainer.remove(top));
		}
	}

	public void shufflePerfectly() {
		List<Card> deck1 = new ArrayList<Card>();
		List<Card> deck2 = new ArrayList<Card>();
		
		int n = getCardCount()/2;
		for (int i = 0; i < n; i++) {
			int half = getCardCount()/2;
			deck2.add(cardContainer.remove(half));
			deck1.add(cardContainer.remove(0));
		}
		for(int j = 0; j < n; j++) {
			cardContainer.add(deck1.get(j));
			cardContainer.add(deck2.get(j));
		}
	}
}
