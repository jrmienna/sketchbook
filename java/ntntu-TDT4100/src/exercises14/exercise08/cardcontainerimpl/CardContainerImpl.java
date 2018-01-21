package exercises14.exercise08.cardcontainerimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardContainerImpl implements CardContainer {

	protected List<Card> cardContainer;
	private int maxCardCount;
	
	public CardContainerImpl() {
		cardContainer = new ArrayList<Card>();
		maxCardCount = 52;
	}
	
	public void addCard(Card card) {
		if(getCardCount() < maxCardCount) {
			cardContainer.add(card);
		}
		else {
			throw new IllegalStateException();
		}
	}
	
	public int getMaxCardCount() {
		return maxCardCount;
	}

	public void setMaxCardCount(int maxCardCount) {
		this.maxCardCount = maxCardCount;
	}

	@Override
	public Iterator<Card> iterator() {
		return cardContainer.iterator();
	}

	@Override
	public int getCardCount() {
		return cardContainer.size();
	}

	@Override
	public Card getCard(int n) {
		if (n < 0 || n > getCardCount()) {
			throw new IllegalArgumentException("card out of reach");
		}
		return cardContainer.get(n);
	}
	
	@Override
	public String toString() {
		return cardContainer.toString();
	}

}
