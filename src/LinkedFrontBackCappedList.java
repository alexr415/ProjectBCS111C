public class LinkedFrontBackCappedList<T extends Comparable<? super T>> implements FrontBackCappedList<T>, Comparable<LinkedFrontBackCappedList<T>> {

    private Node head, tail;
    private int size, capacity;

    public LinkedFrontBackCappedList(int capacity) {
        this.capacity = capacity;
        head = tail = null;
        size = 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]\t" + "size=" + size + "\tcapacity=" + capacity;
        }

        String contents = "";
        Node temp = head;

        for (int i = 0; i < size; i++) {
            contents += temp.data;
            if (i != size - 1) {
                contents += ", ";
            }
            temp = temp.next;
        }
        return "[" + contents + "]\t" + "size=" + size + "\tcapacity=" + capacity + " head=" + head.data + " tail=" + tail.data;
    }

    public int compareTo(LinkedFrontBackCappedList<T> other) {
        if (isEmpty() && other.isEmpty()) return 0;
        if (isEmpty()) return -1;
        if (other.isEmpty()) return 1;

        int longer = 1;
        if (other.size == size) longer = 0;
        if (other.size > size) longer = -1;

        Node temp = head;
        Node otherTemp = other.head;

        while (temp != null && otherTemp != null) {
            if (temp.data.compareTo(otherTemp.data) != 0) return temp.data.compareTo(otherTemp.data);
            if (temp.next == null || otherTemp.next == null) break;
            temp = temp.next;
            otherTemp = otherTemp.next;
        }
        return longer;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean isFull() {
        return size == capacity;
    }

    private boolean isSingleton() {
        return head == tail;
    }

    public int size() {
        return size;
    }

    public T getEntry(int position) {
        if (position < 0 || position >= size) return null;

        Node returnEntry = head;

        for (int i = 0; i < position; i++) returnEntry = returnEntry.next;

        return returnEntry.data;
    }

    @Override
    public int indexOf(T anEntry) {
        if (isEmpty()) return -1;

        Node temp = head;

        for (int i = 0; i < size; i++) {
            if (temp.data.equals(anEntry)) return i;
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T anEntry) {
        if (isEmpty()) return -1;

        Node temp = head;
        int returnIndex = -1;

        for (int i = 0; i < size; i++) {
            if (temp.data.equals(anEntry)) returnIndex = i;
            temp = temp.next;
        }
        return returnIndex;
    }

    @Override
    public boolean contains(T anEntry) {
        if (isEmpty()) return false;

        Node temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.data.equals(anEntry)) return true;
            temp = temp.next;
        }
        return false;
    }

    public void clear() {
        if (isEmpty()) return;

        head = null;
        tail = null;
        size = 0;
    }

    private boolean addToEmpty(T element) {
        head = new Node(element);
        tail = head;
        size++;
        return true;
    }

    public boolean addFront(T element) {
        if (isFull()) return false;
        if (isEmpty()) return addToEmpty(element);

        head = new Node(element, head);
        size++;
        return true;
    }

    @Override
    public boolean addBack(T newEntry) {
        if (isFull()) return false;

        if (isEmpty()) return addToEmpty(newEntry);

        tail.next = new Node(newEntry);
        tail = tail.next;
        size++;
        return true;

    }

    private void removeSingleton() {
        head = null;
        size--;
    }

    @Override
    public T removeFront() {
        if (isEmpty()) return null;

        T returnT = head.data;

        if (isSingleton()) {
            removeSingleton();
            return returnT;
        }
        head = head.next;
        size--;
        return returnT;

    }

    @Override
    public T removeBack() {
        if (isEmpty()) return null;

        T returnT = tail.data;

        if (isSingleton()) {
            removeSingleton();
            return returnT;
        }

        Node temp = head;
        for (int i = 0; i < size - 2; i++) temp = temp.next;
        tail = temp;
        size--;
        return returnT;
    }


    public class Node {
        public T data;
        public Node next;

        private Node(T dataValue) {
            data = dataValue;
            next = null;
        }

        private Node(T dataValue, Node nextNode) {
            data = dataValue;
            next = nextNode;
        }

        private T getData() {
            return data;
        }

        private void setData(T newData) {
            data = newData;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }
    }
}
