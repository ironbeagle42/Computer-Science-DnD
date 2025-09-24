public class SinglyLinkedListTester {
    public static void main(String[] args) {
        String[] strs = new String[] {"Y", "A", "R", "I"};
        SinglyLinkedList<String> test = new SinglyLinkedList<>(strs);
        test.add(2, null);
        System.out.println(test.toString());
        System.out.println(test.remove("R"));
        System.out.println(test.toString());
        test.set(1, "A");
        System.out.println(test.get(3));
        System.out.println(test.toString());
        System.out.println( test.remove(3));
       ;
  



        System.out.println(test.toString());
        System.out.println(test.isEmpty());
    }
}
