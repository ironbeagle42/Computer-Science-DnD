import java.util.ArrayList;
import java.util.Objects;

public class GoldenSaver {

	//Moves every dog whose breed is "Pug" in the list to the back of the list
	public static void rescueGoldens(ArrayList<Dog> list) {
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).getBreed().contains("Golden")) {
				Dog temp = list.get(i);
				list.remove(i);
				list.add(0, temp);
			}
		}

	}
}
