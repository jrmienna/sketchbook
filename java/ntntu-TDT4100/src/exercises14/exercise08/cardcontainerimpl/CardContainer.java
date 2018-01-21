package exercises14.exercise08.cardcontainerimpl;

import java.util.Iterator;

public interface CardContainer {

	public Iterator<Card> iterator();
	
	public int getCardCount();
	
	public Card getCard(int n);
}
