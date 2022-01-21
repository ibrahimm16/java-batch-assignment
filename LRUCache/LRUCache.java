import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {

    LinkedList<Character> keyList;
    Map<Character, Integer> keyMap;
    final int maxCharacters = 10;

    LRUCache() {
        keyList = new LinkedList<>();
        keyMap = new HashMap<>();
    }

    public Integer get(Character key) {
        return keyMap.getOrDefault(key, 0);
    }

    public void set(Character key, Integer value) {
        keyList.remove(key);
        keyList.add(key);
        keyMap.put(key, value+1);

        if (keyList.size() > maxCharacters) {
            Character toRemove = keyList.get(0);
            keyList.remove(toRemove);
            keyMap.remove(toRemove);
        }
    }
}