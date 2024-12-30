import java.util.ArrayList;
public class HashTable<K, V> extends UR_HashTable<K, V> {
    public HashTable() {
        this(INIT_CAPACITY);
    }

    public HashTable(int capacity) {
        n = 0;
        m = capacity;
        keys = (K[]) new Object[m];
        vals = (V[]) new Object[m];
        inserts = 0;
        collisions = 0;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int capacity) {
        HashTable<K, V> temp = new HashTable<>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }
    @Override
    public void put(K key, V value) { //Assuming key is not null
        if (value == null) {
            delete(key);
            return;
        }
        inserts++;
        if (n >= m/2) {
            resize(2*m);
        }

        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                vals[i] = value;
                return;
            }
            collisions++;
            i = (i + 1) % m;
            }
        keys[i] = key;
        vals[i] = value;
        n++;
    }

    @Override
    public V get(K key) { //Assuming key is not null
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
            i = (i + 1) % m;
        }
        return null;
    }

    @Override
    public void delete(K key) { //Assuming key is not null
        if (!contains(key)) {
            return;
        }

        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }

        keys[i] = null;
        vals[i] = null;
        n--;

        i = (i + 1) % m;
        while (keys[i] != null) {
            K a = keys[i];
            V b = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(a, b);
            i = (i + 1) % m;
        }

        if (n > 0 && n <= m/8) {
            resize(m/2);
        }
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public boolean contains(K key) { //Assuming key is not null
        return get(key) != null;
    }

    @Override
    public Iterable<K> keys() {
        ArrayList<K> keyList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                keyList.add(keys[i]);
            }
        }
        return keyList;
    }
}
