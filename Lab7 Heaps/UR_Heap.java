//This code was authored by Ameya Mandhare.
//This code references https://chatgpt.com/

public interface UR_Heap<T extends Comparable<T>> {
    public void insert(T item);
    public boolean isEmpty();
    public int size();
    public T deleteMin();
}

