//This code was authored by Ameya Mandhare.
//This code references https://chatgpt.com/ and https://www.geeksforgeeks.org/binary-heap/

public class UR_HeapImplementation<T extends Comparable<T>> implements UR_Heap<T> {
    private T[] heap;
    private int x;
    private int y;

    public UR_HeapImplementation() {
        this.y = 10;
        this.heap = (T[]) new Comparable[y];
        this.x = 0;
    }

    public UR_HeapImplementation(int capacity) {
        this.y = capacity;
        this.heap = (T[]) new Comparable[y];
        this.x = 0;
    }

    public UR_HeapImplementation(T[] array) {
        //assumes array is not null
        this.y = array.length;
        this.x = array.length;
        this.heap = (T[]) new Comparable[y];
        for (int i = 0; i < x; i++) {
            heap[i] = array[i];
        }
        heapify();
    }

    public void insert(T item) {
        //Assumes item is not null
        if (x == y) {
            expand();
        }
        heap[x] = item;
        bubbleUp(x);
        x++;
    }

    public boolean isEmpty() {
        return x == 0;
    }

    public int size() {
        return x;
    }

    public T deleteMin() {
        if (isEmpty()) {
            return null;
        }
        T z = heap[0];
        heap[0] = heap[x - 1];
        x--;
        bubbleDown(0);
        return z;
    }

    private void bubbleUp(int i) {
        while (i > 0) {
            int pi = (i - 1) / 2;
            if (heap[i].compareTo(heap[pi]) >= 0) {
                break;
            }
            swap(i, pi);
            i = pi;
        }
    }

    private void bubbleDown(int i) {
        while (i < x / 2) {
            int lc = 2 * i + 1;
            int rc = 2 * i + 2;
            int sc = lc;

            if (rc < x && heap[rc].compareTo(heap[lc]) < 0) {
                sc = rc;
            }

            if (heap[i].compareTo(heap[sc]) <= 0) {
                break;
            }

            swap(i, sc);
            i = sc;
        }
    }

    private void expand() {
        y = y * 2;
        T[] newHeap = (T[]) new Comparable[y];
        for (int i = 0; i < x; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    private void swap(int i, int j) {
        T t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    private void heapify() {
        for (int i = (x / 2) - 1; i >= 0; i--) {
            bubbleDown(i);
        }
    }

    public void printHeap() {
        for (int i = 0; i < x; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
