package exercises14.exercise05.person;

import java.util.ArrayList;
import java.util.List;

public class Person {

	private String name;
	private char gender;

	private Person mother;
	private Person father;
	private List<Person> children;

	public Person(String name, char gender) {
		children = new ArrayList<Person>();

		this.name = name;
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public char getGender() {
		return this.gender;
	}

	public Person getMother() {
		return this.mother;
	}

	public Person getFather() {
		return this.father;
	}

	public int getChildCount() {
		return this.children.size();
	}

	public Person getChild(int n) {
		if (n < 0 || n > getChildCount()) {
			throw new IllegalArgumentException("out of range");
		}
		return this.children.get(n);
	}

	/*
	 * oppretter en kobling til et barn (et annet Person-objekt). Dersom
	 * Person-objektet som metoden kalles på, er en kvinne, så skal denne bli
	 * barnets mor, og motsatt, dersom Person-objektet som metoden kalles på, er
	 * en mann, så skal denne bli barnets far.
	 */
	public void addChild(Person child) {
		children.add(child);
		if (gender == 'F' && child.getMother() != this) {
			child.setMother(this);
		}
		if (gender == 'M' && child.getFather() != this) {
			child.setFather(this);
		}
	}

	/*
	 * fjerner en kobling til et barn (et annet Person-objekt). Dersom
	 * Person-objektet som metoden kalles på, er moren til argumentet, så skal
	 * mother-koblingen fjernes, og motsatt, dersom Person-objektet som metoden
	 * kalles på, er argumentets far, så skal father-koblingen fjernes.
	 */
	public void removeChild(Person child) {
		if (children.indexOf(child) == -1) {
			throw new IllegalArgumentException(
					"Not registrered as child of this person");
		}

		this.children.remove(children.indexOf(child));

		if (this == child.getMother()) {
			child.setMother(null);
		}
		if (this == child.getFather()) {
			child.setFather(null);
		}

	}

	/*
	 * setter argumentet (en mann) som faren til Person-objektet som metoden
	 * kalles på. Argumentet får samtidig registrert Person-objektet som metoden
	 * kalles på, som sitt barn.
	 */
	public void setFather(Person father) {
		if(father == null) {
			this.father = null;
			return;
		}
		if(father.getGender() != 'M') {
			throw new IllegalArgumentException("gender must be MALE to be a father");
		}
		if(this == father) {
			throw new IllegalArgumentException("you can't be your own mother");
		}
		if(this.father != null) {
			this.father.removeChild(this);
		}
		this.father = father;
		if(father.children.indexOf(this) == -1) {
			father.addChild(this);
		}
	}
	/*
	 * setter argumentet (en kvinne) som moren til Person-objektet som metoden
	 * kalles på. Argumentet får samtidig registrert Person-objektet som metoden
	 * kalles på, som sitt barn.
	 */
	public void setMother(Person mother) {
		if(mother == null) {
			this.mother = null;
			return;
		}
		if(mother.getGender() != 'F') {
			throw new IllegalArgumentException("Gender must be FEMALE to be a mother");
		}
		if(this == mother) {
			throw new IllegalArgumentException("you can't be your own mother");
		}
		if(this.mother != null) {
			this.mother.removeChild(this);
		}
		this.mother = mother;
		if(mother.children.indexOf(this) == -1) {
			mother.addChild(this);
		}
	}


	public String toString() {
		String output = "Name: " + this.name + "\n" + "Gender: " + this.gender
				+ "\n";
		if (father != null) {
			output += "Father: " + this.father.getName() + "\n";
		}
		if (mother != null) {
			output += "Mother: " + this.mother.getName() + "\n";
		}
		if (children != null) {
			for (int i = 0; i < getChildCount(); i++) {
				output += "Child: " + this.children.get(i).getName() + "\n";
			}
		}

		return output;
	}

	public static void main(String[] args) {
		Person jens = new Person("Jens", 'M');
		Person inga = new Person("Inga", 'F');

		Person john = new Person("John", 'M');
		Person rikard = new Person("Rikard", 'M');
		Person kristoffer = new Person("Kristoffer", 'M');
		Person kristine = new Person("Kristine", 'M');

		john.addChild(rikard);
		john.addChild(kristine);
		john.addChild(kristoffer);
		john.setFather(jens);
		john.setMother(inga);
		
		//bug:
		rikard.setFather(null);

		System.out.println(jens.toString());
		System.out.println();
		System.out.println(inga.toString());
		System.out.println();
		System.out.println(john.toString());
		System.out.println();
		System.out.println(rikard.toString());
		System.out.println();
		System.out.println(kristine.toString());
		System.out.println();
		System.out.println(kristoffer.toString());

	}
}
