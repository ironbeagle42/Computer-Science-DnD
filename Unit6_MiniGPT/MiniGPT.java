import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class MiniGPT {

	private HashMap<String, ArrayList<String>> map = new HashMap<>();
	private String starterString = null;

	public MiniGPT(String fileName, int chainOrder) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			int charNum = 0;
			int charAsInt;
			String chainOrderChars = "";
			// Read until the end of the stream (-1 is returned)
			while ((charAsInt = reader.read()) != -1) {
				// Cast the integer value to a character
				if (chainOrderChars.length() == chainOrder) {
				}
				char character = (char) charAsInt;
				if (!map.containsKey(chainOrderChars)) {
					map.put(chainOrderChars, new ArrayList<String>());
				}
				ArrayList<String> temp = map.get(chainOrderChars);
				temp.add("" + character);
				map.put(chainOrderChars, temp);
				chainOrderChars += character;
				charNum++;
				if (starterString != null) {
					chainOrderChars = chainOrderChars.substring(1, chainOrderChars.length());
				}
				if (charNum == chainOrder) {
					starterString = chainOrderChars;
				}
			}
		} catch (IOException e) {
			System.err.println("An I/O error occurred: " + e.getMessage());
		}
	}



	public void generateText(String outputFileName, int numChars) {
		String ret = starterString;
		int markovNum = ret.length();
		while (ret.length() <= numChars) {
			ArrayList<String> nextStates =
					map.get(ret.substring(ret.length() - markovNum, ret.length()));
			ret += nextStates.get((int) (Math.random() * nextStates.size()));
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
			writer.write(ret);
		} catch (IOException e) {
			System.err.println("An I/O error occurred: " + e.getMessage());
		}
	}
}
