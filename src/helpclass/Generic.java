package helpclass;

import java.util.HashMap;
import java.util.Map;

/**
 * The use of a generic class here is only to show knowledge from the lessons,
 * of course I could have used a HashMap service
 * @param <K>
 * @param <V>
 */
public class Generic<K, V> {
    private K key;
    private V value;
    private final Map<K, V> genericMap;

    public Generic(K key, V value) {
        this.key = key;
        this.value = value;
        this.genericMap = new HashMap<>();
    }

    public Generic() {
        this.genericMap = new HashMap<>();
    }

    public void put(K key, V value) {
        genericMap.put(key, value);
    }

    public Map<K, V> getGenericMap() {
        return genericMap;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}