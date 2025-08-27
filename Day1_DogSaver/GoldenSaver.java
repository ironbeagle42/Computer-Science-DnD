import java.util.ArrayList;
import java.util.Objects;

public class GoldenSaver {

	//Moves every dog whose breed is "Pug" in the list to the back of the list
	public static void rescueGoldens(ArrayList<Dog> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBreed().contains("Golden")) {
				for (int j = list.size() - 1; j >= 0; j--) {
					if (!list.get(j).getBreed().contains("Golden")) {
						if (j < i) {
							i=list.size();
							break;
						}
						Dog temp = list.get(j);
						list.set(j, list.get(i));
						list.set(i, temp);
						j = -1;
					}
				}
			}
		}

	}
}
