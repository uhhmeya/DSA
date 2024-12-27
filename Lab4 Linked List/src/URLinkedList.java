import java.util.Collection;
import java.util.Iterator;

public class URLinkedList<E> implements URList<E> {
    private URNode<E> head;
    private URNode<E> tail;
    private int size;

    public URLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public void add(int index, E element) { //assuming index is in range
        if (index==0) addFirst(element);
        else if (index==size) addLast(element);
        else {
            URNode<E> current = head;
            for(int i=0; i<index; i++) current = current.next();
            URNode<E> D = new URNode<E>(element, current.prev(), current);
            current.prev().setNext(D);
            current.setPrev(D);
            size++;
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) { //assuming C is not empty
        for(E element : c) add(element);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) { //assuming C is not empty
        for(E element : c) add(index++, element);
        return true;
    }

    // Remove methods
    @Override
    public E remove(int index) { //assuming index is in range
        if(index==0) return pollFirst();
        if (index==size-1) return pollLast();
        URNode<E> current = head;
        for(int i=0; i<index; i++) current = current.next();
        current.prev().setNext(current.next());
        current.next().setPrev(current.prev());
        size--;
        return current.element();
    }

    @Override
    public boolean remove(Object o) {
        URNode<E> current = head;
        while (current != null) {
            if ((o == null && current.element() == null) || (o != null && o.equals(current.element()))) {
                if (current == head) pollFirst();
                else if (current == tail) pollLast();
                 else {
                    current.prev().setNext(current.next());
                    current.next().setPrev(current.prev());
                    size--; }
                return true; }
            current = current.next();}
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean x = false;
        for (Object o: c) while (remove(o)) x = true;
        return x;}

    // Access methods
    @Override
    public E get(int index) { //assuming index is in range
        URNode<E> current = head;
        for(int i=0; i<index; i++) current=current.next();
        return current.element();}

    @Override
    public E set(int index, E element) { //assuming index is in range
        URNode<E> current = head;
        for (int i = 0; i < index; i++) current = current.next();
        E temp = current.element();
        current.setElement(element);
        return temp; }

    @Override
    public int indexOf(Object o) {
        int index=0;
        URNode<E> current = head;
        while (current != null) {
            if ((o == null && current.element() == null) || (o != null && o.equals(current.element()))) return index;
            current = current.next();
            index++;}
        return -1;
    }

    @Override
    public void clear() {
        head=null;
        tail=null;
        size=0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1; }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o : c) if (!contains(o)) return false;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public URList<E> subList(int fromIndex, int toIndex) { //assuming from and to index are in range
        URLinkedList<E> subList = new URLinkedList<>();
        URNode<E> current = head;
        for (int i = 0; i < fromIndex; i++) current = current.next();


        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(current.element());
            current = current.next(); }
        return subList;
    }

    @Override
    public Object[] toArray() {
        Object[] x = new Object[size];
        URNode<E> current = head;
        for (int i = 0; i < size; i++) {
            x[i] = current.element();
            current = current.next();}
        return x;
    }

    // Iterator
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private URNode<E> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }
            @Override
            public E next() {
                E element = current.element();
                current = current.next();
                return element;
            }
        };
    }

    //The following method references claude.ai
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof URList)) {
            return false;
        }

        URList<?> other = (URList<?>) o;
        if (size != other.size()) {
            return false;
        }

        Iterator<?> it1 = iterator();
        Iterator<?> it2 = other.iterator();

        while (it1.hasNext()) {
            Object o1 = it1.next();
            Object o2 = it2.next();
            if (!(o1 == null ? o2 == null : o1.equals(o2))) {
                return false;
            }
        }
        return true;
    }

    public void addFirst(E e) {
        URNode<E> D = new URNode<>(e, null, head);
        if (head!=null) head.setPrev(D);
        else {
            tail = D;
        }
        head=D;
        size++;
    }

    public void addLast(E e) {
        URNode<E> D = new URNode<>(e, tail, null);
        if (tail != null) tail.setNext(D);
        else head=D;
        tail = D;
        size++;
    }

    public E peekFirst() {
        return isEmpty() ? null : head.element();
    }

    public E peekLast() {
        return isEmpty() ? null : tail.element();
    }

    public E pollFirst() {
        if (isEmpty())  return null;
        E element = head.element();
        head = head.next();
        if (head != null)  head.setPrev(null);
        else tail = null;
        size--;
        return element;
    }

    public E pollLast() {
        if (isEmpty()) return null;
        E element = tail.element();
        tail = tail.prev();
        if (tail != null) tail.setNext(null);
        else head=null;
        size--;
        return element;
    }
}
