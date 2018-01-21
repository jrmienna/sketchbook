package exercises14.exercise06.named;

public class Person2 implements Named {

	private String fullName;

	public Person2(String fullName) {
		this.fullName = fullName;
	}

	public void setGivenName(String givenName) {
		fullName = fullName.substring(fullName.indexOf(" "), fullName.length());
		this.fullName = givenName + fullName;
	}

	public String getGivenName() {
		return fullName.substring(0, fullName.indexOf(" "));
	}

	public void setFamilyName(String familyName) {
		fullName = fullName.substring(0, fullName.indexOf(" "));
		this.fullName = fullName + familyName;
	}

	public String getFamilyName() {
		return fullName.substring(fullName.indexOf(" ")+1, fullName.length());
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return this.fullName;
	}
}
