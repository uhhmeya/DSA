//Code authored by Ameya Mandhare while referencing https://chatgpt.com/ and https://www.geeksforgeeks.org/java-program-to-construct-a-binary-search-tree/
import java.util.Queue;
import java.util.LinkedList;
public class UR_BST<K extends Comparable<K>,V>{
    private UR_Node r;
    private class UR_Node{
        private K k;
        private V v;
        private UR_Node left,right;
        private int x;
        public UR_Node(K k,V v,int x){
            this.k=k;
            this.v=v;
            this.x=x;
        }
    }
    public boolean isEmpty(){
        return size()==0;
    }
    public int size(){
        return size(r);
    }
    private int size(UR_Node z){
        if(z==null)return 0;
        return z.x;
    }
    public boolean contains(K k){
        return get(k)!=null;
    }
    public V get(K k){
        return get(r,k);
    }
    private V get(UR_Node z,K k){
        if(z==null)return null;
        int cmp=k.compareTo(z.k);
        if(cmp<0)return get(z.left,k);
        else if(cmp>0)return get(z.right,k);
        else return z.v;
    }
    public void put(K k,V v){
        if(v==null){delete(k);return;}
        r=put(r,k,v);
    }
    private UR_Node put(UR_Node z,K k,V v){
        if(z==null)return new UR_Node(k,v,1);
        int cmp=k.compareTo(z.k);
        if(cmp<0)z.left=put(z.left,k,v);
        else if(cmp>0)z.right=put(z.right,k,v);
        else z.v=v;
        z.x=1+size(z.left)+size(z.right);
        return z;
    }
    public void deleteMin(){
        r=deleteMin(r);
    }
    private UR_Node deleteMin(UR_Node z){
        if(z.left==null)return z.right;
        z.left=deleteMin(z.left);
        z.x=1+size(z.left)+size(z.right);
        return z;
    }
    public void deleteMax(){
        r=deleteMax(r);
    }
    private UR_Node deleteMax(UR_Node z){
        if(z.right==null)return z.left;
        z.right=deleteMax(z.right);
        z.x=1+size(z.left)+size(z.right);
        return z;
    }
    public void delete(K k){
        r=delete(r,k);
    }
    private UR_Node delete(UR_Node z,K k){
        if(z==null)return null;
        int cmp=k.compareTo(z.k);
        if(cmp<0)z.left=delete(z.left,k);
        else if(cmp>0)z.right=delete(z.right,k);
        else{
            if(z.right==null)return z.left;
            if(z.left==null)return z.right;
            UR_Node temp=z;
            z=min(temp.right);
            z.right=deleteMin(temp.right);
            z.left=temp.left;
        }
        z.x=size(z.left)+size(z.right)+1;
        return z;
    }
    private UR_Node min(UR_Node z){
        if(z.left==null)return z;
        return min(z.left);
    }
    public Iterable<K> keys(){
        Queue<K> queue=new LinkedList<>();
        inorder(r,queue);
        return queue;
    }
    private void inorder(UR_Node z,Queue<K> queue){
        if(z==null)return;
        inorder(z.left,queue);
        queue.add(z.k);
        inorder(z.right,queue);
    }
    public int height(){
        return height(r);
    }
    private int height(UR_Node z){
        if(z==null)return -1;
        return 1+Math.max(height(z.left),height(z.right));
    }
    public Iterable<K> levelOrder(){
        Queue<K> keys=new LinkedList<>();
        Queue<UR_Node> queue=new LinkedList<>();
        if(r!=null)queue.add(r);
        while(!queue.isEmpty()){
            UR_Node z=queue.poll();
            keys.add(z.k);
            if(z.left!=null)queue.add(z.left);
            if(z.right!=null)queue.add(z.right);
        }
        return keys;
    }
}
