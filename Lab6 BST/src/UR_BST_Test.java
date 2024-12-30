// Code authored by Ameya Mandhare while referencing https://chatgpt.com/ and https://www.geeksforgeeks.org/java-program-to-construct-a-binary-search-tree/
public class UR_BST_Test {
    public static void main(String[] args) {
        UR_BST<Integer, String> bst = new UR_BST<>();
        bst.put(5, "5");
        bst.put(3, "3");
        bst.put(7, "7");
        System.out.println(bst.get(10));
        System.out.println(bst.size());
        bst.put(15, "15");
        System.out.println(bst.keys());
        System.out.println(bst.contains(5));
        bst.put(10, "10");
        bst.put(2, "2");
        bst.put(4, "4");
        System.out.println(bst.isEmpty());
        System.out.println(bst.get(8));
        bst.put(20, "20");
        System.out.println(bst.size());
        System.out.println(bst.levelOrder());
        bst.put(6, "6");
        System.out.println(bst.height());
        bst.put(8, "8");
        System.out.println(bst.get(5));
        bst.put(12, "12");
        System.out.println(bst.isEmpty());
        System.out.println(bst.get(3));
        System.out.println(bst.contains(10));
        System.out.println(bst.get(8));
        System.out.println(bst.keys());
        System.out.println(bst.height());
        bst.put(18, "18");
        System.out.println(bst.get(7));
        bst.put(25, "25");
        System.out.println(bst.contains(4));
        System.out.println(bst.keys());
        bst.delete(4);
        System.out.println(bst.size());
        System.out.println(bst.get(5));
        bst.delete(5);
        System.out.println(bst.size());
        System.out.println(bst.get(6));
        bst.delete(3);
        System.out.println(bst.keys());
        bst.delete(6);
        System.out.println(bst.keys());
        bst.delete(7);
        System.out.println(bst.contains(5));
        System.out.println(bst.size());
        System.out.println(bst.levelOrder());
        bst.deleteMin();
        System.out.println(bst.size());
        bst.deleteMax();
        System.out.println(bst.size());
        bst = new UR_BST<>();
        bst.put(30, "30");
        System.out.println(bst.isEmpty());
        System.out.println(bst.size());
        System.out.println(bst.get(30));
        System.out.println(bst.contains(30));
        System.out.println(bst.height());
        System.out.println(bst.keys());
        System.out.println(bst.isEmpty());
        System.out.println(bst.size());
        while (!bst.isEmpty()) {
            bst.deleteMin();
        }
        System.out.println(bst.isEmpty());
        System.out.println(bst.keys());
    }
}
