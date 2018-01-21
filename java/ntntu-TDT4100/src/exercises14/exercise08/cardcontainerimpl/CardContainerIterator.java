package exercises14.exercise08.cardcontainerimpl;

import java.util.Iterator;


public class CardContainerIterator implements Iterator<Card> {
	
	private CardContainer container;
	private int iterationIndex;
	
	public CardContainerIterator(CardContainer cardContainer) {
		this.container = cardContainer;
		this.iterationIndex = 0;
	}
	
	public boolean hasNext() {
		return iterationIndex < container.getCardCount();
	}
	public Card next() {
		return container.getCard(iterationIndex++);
	}
	public void remove(){
		throw new UnsupportedOperationException();
	}
	public static void main(String[] args) {
		CardDeck deck = new CardDeck(13);
		CardHand hand1 = new CardHand(5);
		CardHand hand2 = new CardHand(5);
		
		
		deck.shufflePerfectly();
		deck.shufflePerfectly();
		deck.shufflePerfectly();
		deck.shufflePerfectly();
		
		deck.deal(hand1, 5);
		deck.deal(hand2, 5);
		
		Iterator<Card> iterator1 = new CardContainerIterator(hand1);
		Iterator<Card> iterator2 = new CardContainerIterator(hand2);
		
		System.out.println("hand 1:");
		while(iterator1.hasNext()) {
			System.out.println(iterator1.next());
		}
		System.out.println();
		System.out.println("hand 2:");
		while(iterator2.hasNext()) {
			System.out.println(iterator2.next());
		}
		
	}
}
