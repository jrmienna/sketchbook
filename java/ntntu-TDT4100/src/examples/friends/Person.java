package examples.friends;

import java.util.ArrayList;

public class Person {
	
	private String name;
	private ArrayList<Person> friends;
	
	public Person(String name) {
		this.name = name;
		friends = new ArrayList<Person>();
	}
	
	public void addFriend(Person person) {
		if (! this.friends.contains(person)) {
			this.friends.add(person);
			person.addFriend(this);
		}
	}
	
	public void removeFriend(Person person) {
		if (this.friends.contains(person)) {
			this.friends.remove(person);
			person.removeFriend(this);
		}
	}
	
	public String toString() {
		String str = name + ": ";
		for (int i = 0; i < friends.size(); i++) {
			str += friends.get(i).name + ", ";
		}
		return str.substring(0,str.length()-2);
	}
	
	public static void main(String[] args) {
		Person per = new Person("Per");
		Person erik = new Person("Erik");
		Person ola = new Person("Ola");
		Person kari = new Person("Kari");
		
		per.addFriend(erik);
		erik.addFriend(ola);
		kari.addFriend(per);
		
		System.out.println(per);
		System.out.println(erik);
		System.out.println(ola);
		System.out.println(kari);
		
		erik.removeFriend(per);
		
		System.out.println();
		System.out.println(per);
		System.out.println(erik);
		System.out.println(ola);
		System.out.println(kari);
	}
}
