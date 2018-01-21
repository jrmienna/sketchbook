import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Babelfish {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, String> map = new HashMap<String, String>();
		
		String line = sc.nextLine();
		String[] split;
		while (!line.isEmpty()) {
			split = line.split(" "); 
			map.put(split[1], split[0]);
			line = sc.nextLine();
		}
		String key;
		while(sc.hasNext()) {
			key = sc.nextLine();
			if (map.containsKey(key)) {
				System.out.println(map.get(key));
			} else {
				System.out.println("eh");
			}
		}
		sc.close();
	}
}
