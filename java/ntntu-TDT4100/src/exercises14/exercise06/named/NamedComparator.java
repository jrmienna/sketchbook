package exercises14.exercise06.named;

import java.util.Comparator;

public class NamedComparator implements Comparator<Named> {

	public int compare(Named named1, Named named2) {

		int compareFamilyName = named1.getFamilyName().compareTo(
				named2.getFamilyName());

		if (compareFamilyName != 0) {
			return compareFamilyName;
		}

		int compareFirstName = named1.getGivenName().compareTo(
				named2.getGivenName());

		if (compareFirstName != 0) {
			return compareFirstName;
		}

		return 0;
	}

	public static void main(String[] args) {
		NamedComparator comparator = new NamedComparator();
		Person1 person1 = new Person1("Ada", "Smith");
		Person2 person2 = new Person2("Oda Smith");

		System.out.println(comparator.compare(person1, person2));

	}
}
