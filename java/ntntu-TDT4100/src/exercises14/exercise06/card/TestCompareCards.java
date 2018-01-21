package exercises14.exercise06.card;

import java.util.Comparator;

public class TestCompareCards {
	
	Card card1;
	Card card2;
	
	private void init() {
		card1 = new Card('D',1);
		card2 = new Card('S',1);
	}

	private void run() {
		Comparator<Card> cmp = new CardComparator(true, ' ');
		int i = cmp.compare(card1, card2);
		
		if(i < 0) {
			System.out.println(card1.toString() + ": low");
			System.out.println(card2.toString() + ": high");
		}
		if(i > 0) {
			System.out.println(card1.toString() + ": high");
			System.out.println(card2.toString() + ": low");
		}
		if(i == 0) {
			System.out.println("equal");
		}
	}
	

	public static void main(String[] args) {
		TestCompareCards test = new TestCompareCards();
		test.init();
		test.run();
	}

}
