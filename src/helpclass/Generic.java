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
    private K first;
    private V second;
    private Map<K, V> genericMap;

    public Generic(K first, V second) {
        this.first = first;
        this.second = second;
        this.genericMap = new HashMap<>();
    }

    public Generic() {
        this.genericMap = new HashMap<>();
    }

    public Map<K, V> getGenericMap() {
        return genericMap;
    }

    public V get(K key) {
        return genericMap.get(key);
    }

    public void put(K key, V value) {
        this.genericMap.put(key, value);
    }

    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    public void updateMap(K key, V value) {
        genericMap.put(key, value);
    }
}