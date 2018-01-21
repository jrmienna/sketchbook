package exercises14.exercise02.stack;

import java.util.Random;

public class RandomStringGenerator {

	private StringBuilder randomString = new StringBuilder();
	private String vocals = "aaeeeiiioouy";
	private String consonants = "bdddfgghhjkllmnnprssssttttv";
	private Random random = new Random();

	
	// returner et tilfeldig String-objekt.
	
	public String getRandomString()  {
		randomString.delete(0, randomString.length());
		int n = random.nextInt(15);
		if (n == 0) {
			n += 2;
		}
		for (int i = 0; i < n; i++){
			int indexVocals = random.nextInt(vocals.length());
			int indexConsonants = random.nextInt(consonants.length());
			if (5 > random.nextInt(10)) {
				randomString.append(vocals.substring(indexVocals, indexVocals+1));
				randomString.append(consonants.substring(indexConsonants, indexConsonants+1));
			}
			else {
				randomString.append(vocals.substring(indexVocals, indexVocals));
				randomString.append(consonants.substring(indexConsonants, indexConsonants));
			}
		}
		return randomString.toString();
	}
}
