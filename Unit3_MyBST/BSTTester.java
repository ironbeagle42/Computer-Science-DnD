public class BSTTester {
    public static void main(String[] args) {
        MyBST<Double> bst = new MyBST<Double>();

        System.out.println(bst.add(1.0));
        // System.out.println(bst.add(5));
        System.out.println(bst.add(3.0));
        // System.out.println(bst.add(4));
        // System.out.println(bst.add(3));
        System.out.println(bst.add(2.0));
        System.out.println(bst.add(2.5));
        System.out.println(bst.toString());
        System.out.println(bst.remove(1.0));
        System.out.println(bst.toString());


    }
}
