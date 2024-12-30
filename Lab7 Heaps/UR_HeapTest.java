//This code was authored by Ameya Mandhare.
//This code references https://chatgpt.com/

public class UR_HeapTest {
    public static void main(String[] args) {
        UR_HeapImplementation<Integer> heap = new UR_HeapImplementation<>(5);
        System.out.println(heap.isEmpty() ? 1 : 0);
        System.out.println(heap.size());
        heap.insert(42);
        heap.insert(17);
        heap.insert(93);
        heap.insert(8);
        heap.insert(31);
        heap.insert(56);
        heap.insert(23);
        heap.insert(4);
        heap.insert(67);
        heap.insert(29);
        heap.printHeap();
        System.out.println(heap.deleteMin());
        heap.printHeap();
        while (!heap.isEmpty()) System.out.println(heap.deleteMin());
        System.out.println(heap.isEmpty() + " " + heap.size());
        System.out.println();
    }
}
