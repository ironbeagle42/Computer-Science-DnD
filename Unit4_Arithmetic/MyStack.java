public class MyStack<Object> {

    private ListNode head;

    
    public boolean pop() {
        if (empty()) {
            return false;
        }
        head = head.getNext();
        return true;
    }

    public boolean push(ListNode node) {
        if (empty()) {
            head = node;
            return true;
        }
        node.setNext(head);
        head = node;
        return true;
    }

    public Object peek() {
        if (empty()) {
            return null;
        }
        return (Object) head.getValue();
    }

    public boolean empty() {
        return head == null;
    }
}
