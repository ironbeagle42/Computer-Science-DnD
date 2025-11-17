public class RecursionTester {
    public static void main(String[] args) {
        SinglyLinkedList<String> test = new SinglyLinkedList<String>();
        test.add("Y");
        test.add("A");
        test.add("R");
        test.add("I");
        Recursion.printListInReverse(test.getHead());
        System.out.println(Recursion.countNonConsecutiveSubsets(10));
        Recursion.printSubsets("abcd");
        Recursion.solveHanoi(3);
        Recursion.printPermutations("abc");
        int[] times = {3, 7, 8, 13, 14};
        int[] points = {100, 15, 15, 80, 10};
        System.out.println(Recursion.scavHunt(times, points));
        int[] mergeHelp = {5, 6, 9, 15, 100};
        int[] merge = {20, 3, 5, 7, 39, 2, 15};
        Recursion.quickSort(merge);
        for (int i = 0; i < merge.length; i++) {
            System.out.print(merge[i] + ", ");
        }
        // merge = Recursion.merge(times, mergeHelp);
        //         for (int i = 0; i < merge.length; i++) {
        //     System.out.print(merge[i] + ", ");
        // }
    }
}
