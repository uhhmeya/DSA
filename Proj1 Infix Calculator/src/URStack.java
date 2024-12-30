//Code created by Ameya Mandhare
//Referenced: https://www.geeksforgeeks.org/stack-class-in-java/
//code referenced GeeksForGeeks, programmariz, and chatgpt
class URStack<T> {
    private node<T> x;
    private int a;

    private static class node<T> {
        private T data;
        private node<T> next;

        node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public int size() {
        return a;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T data = x.data;
        x = x.next;
        a--;
        return data;
    }

    public void push(T data) {
        node<T> newNode = new node<>(data);
        newNode.next = x;
        x = newNode;
        a++;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return x.data;
    }

    public boolean isEmpty() {
        return x == null;
    }
}
