package exercises14.exercise03.register.registration;

import java.util.Date;

public class PersonData {

	private String name;
	private String email;
	private Date birthday;
	private char gender;
	private String sSN;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setEmail(String email, boolean throwsException) {
			this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setBirthday(Date birthday, boolean throwsException) {
			this.birthday = birthday;
	}
	
	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public void setGender(char gender, boolean throwsException) {
			this.gender = gender;
	}
	
	public String getSocialsec() {
		return sSN;
	}

	public void setSSN(String sSN) {
		this.sSN = sSN;
	}
	
	public void setSSN(String sSN, boolean throwsException) {
			this.sSN = sSN;
	}
	
	public String toString() {
		return "Name: " + name + "\nGender: " + gender + "\nE-mail: " + email
				+ "\nBirthday: " + birthday + "\nSSN: " + sSN;
	}
}
