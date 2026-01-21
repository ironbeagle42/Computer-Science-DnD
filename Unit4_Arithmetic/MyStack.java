public class MyStack<E> {

    private ListNode<E> head;

    
    public boolean pop() {
        if (empty()) {
            return false;
        }
        head = head.getNext();
        return true;
    }

    public boolean add(ListNode<E> node) {
        if (empty()) {
            head = node;
            return true;
        }
        head.setNext(node);
        return true;
    }

    public E peek() {
        return head.getValue();
    }

    public boolean empty() {
        return head == null;
    }
}
