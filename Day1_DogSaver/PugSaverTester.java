import java.util.ArrayList;

public class PugSaverTester {
    public static void main(String[] args) {
        Dog dog1 = new Dog("name", "Golden Retriever");
        Dog dog2 = new Dog("name", "Pug");
        MyArrayList<Dog> list = new MyArrayList<Dog>();
        for (int a = 0; a < 10; a += 2) {
            list.add(dog1);
            list.add(dog2);
        }
        System.out.println(list.toString());
        PugSaver.rescuePugs(list);
        System.out.println(list.toString());
    }
}
