import java.util.ArrayList;

public class PugSaverTester {
    public static void main(String[] args) {
        Dog dog1 = new Dog("name", "Golden Retriever");
        Dog dog2 = new Dog("name", "Pug");
        ArrayList<Dog> list = new ArrayList<Dog>(1000000);
        for (int a = 0; a < 1000000; a += 1) {
            list.add(dog1);
            list.add(dog2);
        }
        PugSaver.rescuePugs(list);
        System.out.println(list.toString());
    }
}
