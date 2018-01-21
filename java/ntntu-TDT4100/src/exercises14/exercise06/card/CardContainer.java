package exercises14.exercise06.card;

import java.util.Iterator;

public interface CardContainer {

	public Iterator<Card> iterator();
	
	public int getCardCount();
	
	public Card getCard(int n);
}
