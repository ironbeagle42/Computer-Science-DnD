import java.util.ArrayList;
import java.util.Objects;

public class PugSaver {

	// Moves every dog whose breed is "Pug" in the list to the back of the list
	public static void rescuePugs(ArrayList<Dog> list) {
		int dogsSaved = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBreed().contains("Golden")) {
				for (int j = list.size() - 1 - dogsSaved; j >= 0; j--) {
					if (!list.get(j).getBreed().contains("Golden")) {
						if (j < i) {
							i = list.size();
							break;
						}
						Dog temp = list.get(j);
						list.set(j, list.get(i));
						list.set(i, temp);
						dogsSaved = list.size() - j;
						j = -1;
					}
				}
			}
		}

	}
}
