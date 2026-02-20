public class HeapTester {
    public static void main(String[] args) {
        HeapPQ<Integer> heap = new HeapPQ<>();
        heap.add(24);
        heap.add(10);
        heap.add(1);
        heap.add(2);
        heap.removeMin();
        System.out.println(heap);
    }
}
