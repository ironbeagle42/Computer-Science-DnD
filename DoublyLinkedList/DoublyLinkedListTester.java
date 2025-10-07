public class DoublyLinkedListTester {
    public static void main(String[] args) {
        Nucleotide[] strs =
                new Nucleotide[] {Nucleotide.A, Nucleotide.G, Nucleotide.T, Nucleotide.C};
        DoublyLinkedList test = new DoublyLinkedList(strs);
        System.out.println(test.toString());
        test.add(Nucleotide.A);
        System.out.println(test.toString());
        test.remove(2);
        System.out.println(test.toString());
        test.remove(Nucleotide.C);
        System.out.println(test.toString());
        test.remove(0);
        test.remove(0);
        test.remove(0);
        System.out.println(test.toString());
        test.add(Nucleotide.G);
        test.add(Nucleotide.C);
        test.add(Nucleotide.T);
        test.add(Nucleotide.C);
        test.add(Nucleotide.A);
        test.add(Nucleotide.T);
        System.out.println(test.toString());
        test.remove(3);
        System.out.println(test.toString());
    }
}
