package exercises14.exercise05.card;

public class Card {

	char suit;
	int face;
	
	public Card(char cardSuit, int cardFace) {
		if (cardFace < 1 || cardFace > 13) {
			throw new IllegalArgumentException("illegal face");
		}
		if (cardSuit != 'S' && cardSuit != 'H' && cardSuit != 'D' && cardSuit != 'C') {
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
}
