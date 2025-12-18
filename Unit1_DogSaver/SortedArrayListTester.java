public class SortedArrayListTester {
    public static void main(String[] args) {
        SortedArrayList<Integer> test = new SortedArrayList<>();
        for (int i = 0; i < 20; i += 2) {
            test.add(i);
        }
        for (int j = 1; j < 20; j += 2) {
            test.add(j);
        }
        System.out.println(test.toString());
    }
}
