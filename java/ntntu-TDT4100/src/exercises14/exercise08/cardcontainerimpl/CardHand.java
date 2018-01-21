package exercises14.exercise08.cardcontainerimpl;

public class CardHand extends CardContainerImpl {

	public CardHand(int maxCardCount) {
		super.setMaxCardCount(maxCardCount);
	}

	public Card play(int n) {
		if (n < 0 || n > getCardCount()) {
			throw new IllegalStateException("card out of reach");
		}
		return cardContainer.remove(n);
	}
}
