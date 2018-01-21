package exercises14.exercise03.register.registration;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonValidation {

	private static int F1 = 3;
	private static int F2 = 7;
	private static int F3 = 6;
	private static int F4 = 1;
	private static int F5 = 8;
	private static int F6 = 9;
	private static int F7 = 4;
	private static int F8 = 5;
	private static int F9 = 2;

	private static int G1 = 5;
	private static int G2 = 4;
	private static int G3 = 3;
	private static int G4 = 2;
	private static int G5 = 7;
	private static int G6 = 6;
	private static int G7 = 5;
	private static int G8 = 4;
	private static int G9 = 3;
	private static int G10 = 2;

	// Name validation
	public boolean isValidName(String name, boolean throwsException) {
		name.trim();
		if (name.indexOf(" ") == -1) {
			if (throwsException) {
				throw new IllegalArgumentException(
						"Name must contain first name and last name");
			}
			return false;
		}
		String firstName = name.substring(0, name.indexOf(" ")).toLowerCase();
		String lastName = name.substring(name.indexOf(" ") + 1, name.length())
				.toLowerCase();
		if (lastName.indexOf(" ") != -1) {
			if (throwsException) {
				throw new IllegalArgumentException(
						"Program does not support middle name");
			}
			return false;
		}
		if (firstName.length() < 2 || lastName.length() < 2) {
			if (throwsException) {
				throw new IllegalArgumentException(
						"Both names must contain more than one letter");
			}
			return false;
		}

		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (!(Character.isLetter(c) || c == ' ' || c == '-')) {
				if (throwsException) {
					throw new IllegalArgumentException("The character " + c
							+ "is not allowed");
				}
				return false;
			}
		}
		return true;
	}

	// Email validation
	public boolean isValidEmail(String email, String name,
			boolean throwsException) {
		String emailRegEx = "^[_A-Za-z0-9-\\+]{2,}(\\.[_A-Za-z0-9-]{2,})@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern emailPattern = Pattern.compile(emailRegEx);
		Matcher emailMatcher = emailPattern.matcher(email);
		if (!emailMatcher.matches()) {
			if (throwsException) {
				throw new IllegalArgumentException("E-mail not valid");
			}
			return false;
		}
		if (name == null) {
			if (throwsException) {
				throw new IllegalStateException("Name has not been spesified");
			}
			return false;
		}
		String firstNameMail = email.substring(0, email.indexOf("."));
		String lastNameMail = email.substring(email.indexOf(".") + 1,
				email.indexOf("@"));

		String firstName = name.substring(0, name.indexOf(" ")).toLowerCase();
		String lastName = name.substring(name.indexOf(" ") + 1, name.length())
				.toLowerCase();

		if (!firstName.equals(firstNameMail) || !lastName.equals(lastNameMail)) {
			if (throwsException) {
				throw new IllegalArgumentException(
						"Email must have format: firstname.lastname@....");
			}
			return false;
		}

		return true;
	}

	// Gender validation
	public boolean isValidGender(char gender, boolean throwsException) {
		if (!(gender == 'M' || gender == 'F' || gender == '\0')) {
			if (throwsException) {
				throw new IllegalArgumentException("Gender not valid");
			}
			return false;
		}
		return true;
	}

	private boolean isLeapYear(int year) {
		if (year % 400 == 0) {
			return true;
		} else if (year % 100 == 0) {
			return false;
		} else if (year % 4 == 0) {
			return true;
		} else {
			return false;
		}
	}

	// Date validation
	@SuppressWarnings("deprecation")
	public boolean isValidDate(Date date, boolean throwsException) {
		int day = date.getDate();
		int month = date.getMonth() + 1;
		int year = date.getYear() + 1900;

		if (day < 1 || day > 31) {
			if (throwsException) {
				throw new IllegalArgumentException(day + "is not a valid day");
			}
			return false;
		}
		if (month < 1 || month > 12) {
			if (throwsException) {
				throw new IllegalArgumentException(month
						+ "is not a valid month");
			}
			return false;
		}
		if (year < 0 || year > 2014) {
			if (throwsException) {
				throw new IllegalArgumentException(year
						+ " is not a valid year");
			}
			return false;
		}

		switch (month) {
		case 2 - 1: {
			if (isLeapYear(year)) {
				if (day > 29) {
					if (throwsException) {
						throw new IllegalStateException(day + "is not a day in"
								+ month);
					}
					return false;
				}
			} else {
				if (day > 28) {
					if (throwsException) {
						throw new IllegalStateException(day + "is not a day in"
								+ month + ". Year " + year
								+ "is not leap year!");
					}
					return false;
				}
			}
		}
		case 4 - 1: {
			if (day > 30) {
				if (throwsException) {
					throw new IllegalStateException(day + "is not a day in"
							+ month);
				}
				return false;
			}
		}
		case 6 - 1: {
			if (day > 30) {
				if (throwsException) {
					throw new IllegalStateException(day + "is not a day in"
							+ month);
				}
				return false;
			}
		}
		case 9 - 1: {
			if (day > 30) {
				if (throwsException) {
					throw new IllegalStateException(day + "is not a day in"
							+ month);
				}
				return false;
			}
		}
		case 11 - 1: {
			if (day > 30) {
				if (throwsException) {
					throw new IllegalStateException(day + "is not a day in"
							+ month);
				}
				return false;
			}
		}
		}
		return true;
	}

	// Social Security Number Validation
	public boolean isValidSSN(String sSN, Date birthday, char gender,
			boolean throwsException) {
		if (sSN.length() != 11) {
			if (throwsException) {
				throw new IllegalArgumentException(
						"Social security number must be 11 digits long");
			}
			return false;
		}
		if (gender == '\u0000' || birthday == null) {
			if (throwsException) {
				throw new IllegalStateException(
						"Gender and/or birthday has not been spesified");
			}
			return false;
		}

		int D = Integer.parseInt(sSN.substring(0, 2));
		int M = Integer.parseInt(sSN.substring(2, 4)) - 1;
		int Y = Integer.parseInt(sSN.substring(4, 6));

		@SuppressWarnings("deprecation")
		Date testBirthday = new Date(Y, M, D);

		int D1 = Integer.parseInt(sSN.substring(0, 1));
		int D2 = Integer.parseInt(sSN.substring(1, 2));
		int M1 = Integer.parseInt(sSN.substring(2, 3));
		int M2 = Integer.parseInt(sSN.substring(3, 4));
		int Y1 = Integer.parseInt(sSN.substring(4, 5));
		int Y2 = Integer.parseInt(sSN.substring(5, 6));

		int N1 = Integer.parseInt(sSN.substring(6, 7));
		int N2 = Integer.parseInt(sSN.substring(7, 8));
		int N3 = Integer.parseInt(sSN.substring(8, 9));

		int K1 = Integer.parseInt(sSN.substring(9, 10));
		int K2 = Integer.parseInt(sSN.substring(10, 11));

		// Algorithm for controll digits
		int VS_K1 = D1 * F1 + D2 * F2 + M1 * F3 + M2 * F4 + Y1 * F5 + Y2 * F6
				+ N1 * F7 + N2 * F8 + N3 * F9;
		int VS_K2 = D1 * G1 + D2 * G2 + M1 * G3 + M2 * G4 + Y1 * G5 + Y2 * G6
				+ N1 * G7 + N2 * G8 + N3 * G9 + K1 * G10;

		int K1_CHECK = 11 - (VS_K1 % 11);
		int K2_CHECK = 11 - (VS_K2 % 11);

		if (VS_K1 % 11 == 0) {
			K1_CHECK = 0;
		}
		if (VS_K2 % 11 == 0) {
			K2_CHECK = 0;
		}
		if (!birthday.equals(testBirthday)) {
			if (throwsException) {
				throw new IllegalArgumentException(
						"Social security number does not match with give birthday");
			}
			return false;
		}

		if (N3 % 2 == 0 && gender != 'F') {
			if (throwsException) {
				throw new IllegalArgumentException(
						"Social security number is not valid: 200");
			}
			return false;
		}
		if (N3 % 2 != 0 && gender != 'M') {
			if (throwsException) {
				throw new IllegalArgumentException(
						"Social security number is not valid: 201");
			}
			return false;
		}
		if (K1 != K1_CHECK) {
			if (throwsException) {
				throw new IllegalArgumentException(
						"Social security number is not valid: 300");
			}
			return false;
		}
		if (K2 != K2_CHECK) {
			if (throwsException) {
				throw new IllegalArgumentException(
						"Social security number is not valid: 301");
			}
			return false;
		}
		return true;
	}
}
