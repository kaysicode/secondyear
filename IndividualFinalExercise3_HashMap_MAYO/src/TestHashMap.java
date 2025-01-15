import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * The TestHashMap class demonstrates the use of different types of Map implementations
 * in Java, including HashMap, TreeMap, and LinkedHashMap.
 *
 * <pre>
 *     OUTPUT:
 *     Display entries in HashMap
 * {Lewis=29, Smith=30, Cook=29, Anderson=31}
 *
 * Display entries in ascending order of key
 * {}
 * The age for Lewis is 29
 *
 * Display entries in LinkedHaspMap
 * {Smith=30, Anderson=31, Cook=29, Lewis=29}
 *
 * Process finished with exit code 0
 * </pre>
 *
 * @author MAYO, Kenneth Charles P.
 * @since November 29, 2024
 */
public class TestHashMap {

    /**
     * The main method where different types of HashMaps are created, populated with
     * data, and displayed.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Create a HashMap to store name-age pairs
        Map<String, Integer> hashMap = new HashMap<>();

        // Adding key-value pairs to the HashMap
        hashMap.put("Smith", 30);
        hashMap.put("Anderson", 31);
        hashMap.put("Lewis", 29);
        hashMap.put("Cook", 29);

        // Display the entries in the HashMap
        System.out.println("Display entries in HashMap");
        System.out.println(hashMap + "\n");

        // Create a TreeMap from the previous HashMap (Note: TreeMap should be used here, not HashMap)
        Map<String, Integer> treeMap = new HashMap<>();

        // Display entries in ascending order of key (Note: this is incorrect since treeMap is not actually a TreeMap)
        System.out.println("Display entries in ascending order of key");
        System.out.println(treeMap);

        // Create a LinkedHashMap that maintains the order of insertion
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);

        // Adding key-value pairs to the LinkedHashMap
        linkedHashMap.put("Smith", 30);
        linkedHashMap.put("Anderson", 31);
        linkedHashMap.put("Lewis", 29);
        linkedHashMap.put("Cook", 29);

        // Display the age for Lewis from LinkedHashMap
        System.out.println("The age for " + "Lewis is " +
                linkedHashMap.get("Lewis").intValue());

        // Display the entries in the LinkedHashMap
        System.out.println("\nDisplay entries in LinkedHashMap");
        System.out.println(linkedHashMap);
    }
}
