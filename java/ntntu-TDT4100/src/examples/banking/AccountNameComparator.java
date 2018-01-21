package examples.banking;

import java.util.Comparator;

public class AccountNameComparator implements Comparator<Account> {
	
	// Compares the names of two accounts lexically
	// Will sort list of accounts (given by names): ["Birger", "Sebastian", "Seb", "Axel", "Berit"]
	// 										   as: ["Axel", "Berit", "Birger", "Seb", "Sebastian"]
	public int compare(Account o1, Account o2) {
		int length1 = o1.getName().length();
		int length2 = o2.getName().length();
		int iterationLength = length1 > length2 ? length2 : length1;
		for (int i = 0; i < iterationLength; i++) {
			char ch1 = o1.getName().toLowerCase().charAt(i);
			char ch2 = o2.getName().toLowerCase().charAt(i);
			int diff = ch1 - ch2;
			if (diff > 0) {
				return 1;
			} else if (diff < 0) {
				return -1;
			}
		}
		if (length1 < length2) {
			return -1;
		} else if (length1 > length2) {
			return 1;
		} else {
			return 0;
		}
	}
}