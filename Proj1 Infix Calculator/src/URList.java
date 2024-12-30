//Code created by Ameya Mandhare
//Refernced: https://www.geeksforgeeks.org/linked-list-in-java/
class URList<T> {
    private URNode<T> x;
    private int a;

    public URList() {
        x = null;
        a = 0;
    }

    public int size() {
        return a;
    }

    public T get(int index) {
        if (index < 0 || index >= a) {
            return null;
        }
        URNode<T> current = x;
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current.element();
    }

    public void add(T data) {
        URNode<T> newNode = new URNode<>(data, null, null);
        if (x == null) {
            x = newNode;
        } else {
            URNode<T> current = x;
            while (current.next() != null) {
                current = current.next();
            }
            current.setNext(newNode);
            newNode.setPrev(current);
        }
        a++;
    }

    public boolean isEmpty() {
        return x == null;
    }
}
