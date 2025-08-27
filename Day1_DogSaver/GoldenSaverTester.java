import java.util.ArrayList;
public class GoldenSaverTester {
    public static void main(String[] args) {
        Dog dog1 = new Dog("name", "Golden Retriever");
        Dog dog2 = new Dog("name", "Pug");
        ArrayList<Dog> list = new ArrayList<Dog>();
        for (int i = 0; i < 100000000; i++) {
            list.add(dog1);
            list.add(dog2);
        }
        GoldenSaver.rescueGoldens(list);

    }
}
