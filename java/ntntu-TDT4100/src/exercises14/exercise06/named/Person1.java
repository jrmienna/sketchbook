package exercises14.exercise06.named;

public class Person1 implements Named {

	private String givenName;
	private String familyName;

	public Person1(String givenName, String familyName) {
		this.givenName = givenName;
		this.familyName = familyName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getGivenName() {
		return this.givenName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFamilyName() {
		return this.familyName;
	}

	public void setFullName(String fullName) {
		this.givenName = fullName.substring(0, fullName.indexOf(" "));
		this.familyName = fullName.substring(fullName.indexOf(" ")+1, fullName.length());
	}

	public String getFullName() {
		return givenName + " " + familyName;
	}
	public static void main(String[] args) {
		Person1 person1 = new Person1 ("Ada", "Kristiansen");
		person1.setFullName("Lol Lolsen");
		System.out.println(person1.getGivenName());
		System.out.println(person1.getFamilyName());
		System.out.println(person1.getFullName());
	
	}
}
