//Code created by Ameya Mandhare
//Referenced: https://www.programiz.com/dsa/queue
//code referenced GeeksForGeeks, programmariz, and chatgpt
class URQueue<T> {
    private node<T> x;
    private node<T> y;
    private int a;

    private static class node<T> {
        private T data;
        private node<T> next;

        node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public URQueue() {
        x = null;
        y = null;
        a = 0;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return x.data;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T data = x.data;
        x = x.next;
        if (x == null) {
            y = null;
        }
        a--;
        return data;
    }

    public void enqueue(T data) {
        node<T> newNode = new node<>(data);
        if (y == null) {
            x = newNode;
            y = newNode;
        } else {
            y.next = newNode;
            y = newNode;
        }
        a++;
    }

    public boolean isEmpty() {
        return x == null;
    }

    public int size() {
        return a;
    }
}
