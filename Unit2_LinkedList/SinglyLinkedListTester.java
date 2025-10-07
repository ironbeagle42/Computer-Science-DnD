public class SinglyLinkedListTester {
    public static void main(String[] args) {
       
        SinglyLinkedList<String> test = new SinglyLinkedList<>(strs);
        test.add(2, null);
        System.out.println(test.toString());
        System.out.println(test.remove("R"));
        System.out.println(test.toString());
        test.set(1, "A");
        test.set(0, "A");
        System.out.println(test.toString());
        test.remove("Y");
        test.remove(1);
        test.remove(0);
        test.remove(null);
        test.remove(null);
        test.remove("I");
        System.out.println(test.toString());

	


    }
}
