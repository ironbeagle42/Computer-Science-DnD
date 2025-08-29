import java.util.ArrayList;
public class PugSaverTester {
    public static void main(String[] args) {
        Dog dog1 = new Dog("name", "Golden Retriever");
        Dog dog2 = new Dog("name", "Pug");
        MyArrayList<Dog> list = new MyArrayList<Dog>();
        for (int a = 0; a < 10; a+=2) {
            list.set(a, dog1);
            list.set(a + 1, dog2);
        }
        for (int j = 0; j < 10; j++) {
            System.out.println(list.get(j).getBreed());
        }
        PugSaver.rescuePugs(list);
        for (int b = 0; b < 10; b++) {
            System.out.println(list.get(b).getBreed());
        }

    }
}
