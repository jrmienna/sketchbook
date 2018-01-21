package exercises14.exercise04.sokoban;

public class Level {

	public final static String 
			SAMPLE_LEVEL1 = 
			"#####\n" +
			"#@$.#\n" +
			"#####",
			
			SAMPLE_LEVEL2 = 
			"###  \n" +
			"#.###\n" +
			"#*$ #\n" +
			"# @ #\n" +
			"#####",
			
			SAMPLE_LEVEL3 = 
			"#######\n" +
			"#.@ # #\n" +
			"#$*   #\n" +
			"#   $ #\n" +
			"#  .  #\n" +
			"#  *  #\n" +
			"#######",
			
			SAMPLE_LEVEL4 = 
			"*###########*\n" +
			"#           #\n" +
			"#  ... ...  #\n" +
			"#  *$$ $.$  #\n" +
			"# $*+$ $*$$ #\n" +
			"#  *$$ $.$  #\n" +
			"#  ...  .   #\n" +
			"#           #\n" +
			"*###########*",
			
			SAMPLE_LEVEL5 = 
			"###################\n" +
			"####  #   #########\n" +
			"####   $  #########\n" +
			"#### $## ##########\n" +
			"##### #. ##  ######\n" +
			"##### #.   $ ######\n" +
			"##### #. ##  ######\n" +
			"##### #.### #######\n" +
			"#####@   ##  ######\n" +
			"#####  $     ######\n" +
			"###########  ######\n" +
			"###################\n" +
			"###################;";

	private static final int FINAL_LEVEL = 5;

	private String currentLevel;
	private int currentLevelNumber;

	public String getLevel() {
		return currentLevel;
	}

	public int getLevelNumber() {
		return currentLevelNumber;
	}

	public int getFinalLevelNumber() {
		return FINAL_LEVEL;
	}

	public void setLevel(int level) {
		if (level == 1) {
			currentLevelNumber = 1;
			currentLevel = SAMPLE_LEVEL1;
		}
		if (level == 2) {
			currentLevelNumber = 2;
			currentLevel = SAMPLE_LEVEL2;
		}
		if (level == 3) {
			currentLevelNumber = 3;
			currentLevel = SAMPLE_LEVEL3;
		}
		if (level == 4) {
			currentLevelNumber = 4;
			currentLevel = SAMPLE_LEVEL4;
		}
		if (level == 5) {
			currentLevelNumber = 5;
			currentLevel = SAMPLE_LEVEL5;
		}
	}
}
