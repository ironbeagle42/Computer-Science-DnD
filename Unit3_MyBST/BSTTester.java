public class BSTTester {
    public static void main(String[] args) {
        MyBST<Integer> bst = new MyBST<Integer>();
        BinaryNode<Integer> temp = new BinaryNode<Integer>(20);
        System.out.println(bst.add(20));
        System.out.println(bst.add(15));
        System.out.println(bst.toString());
        System.out.println(bst.add(10));
        System.out.println(bst.add(30));
        System.out.println(bst.toString());
        System.out.println(bst.add(1));
        System.out.println(bst.toString());
        System.out.println(bst.add(2));
        System.out.println(bst.remove(10));
        System.out.println(bst.toString());

    }
}
