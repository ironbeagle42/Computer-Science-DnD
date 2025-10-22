public class RecursionTester {
    public static void main(String[] args) {
        SinglyLinkedList<String> test = new SinglyLinkedList<String>();
        test.add("Y");
        test.add("A");
        test.add("R");
        test.add("I");
        Recursion.printListInReverse(test.getHead());
        System.out.println(Recursion.countNonConsecutiveSubsets(4));

    }
}
