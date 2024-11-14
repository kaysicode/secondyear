package BroCodeDSA.Structure.DynamicArray;

public class DynamicArrayMain {

    // Advantages :

    // 1. Random access of elements O(1)
    // 2. Good locality of reference and data cache utilization
    // 3. Easy to insert/delete at the end

    // Disadvantage :

    // 1. Wastes more memory
    // 2. Shifting elements is time-consuming O(n)
    // 3. Expanding/Shirking the array is time-consuming O(n)

    public static void main(String[] args) {
        // ArrayList<Integer> arrayList = new ArrayList<>();
        // Same with the dynamic array but here you will make one since
        // ArrayList is Pre Build Dynamic Array

        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.add("A");
        dynamicArray.add("B");
        dynamicArray.add("C");
        dynamicArray.add("D");
        dynamicArray.add("E");
        dynamicArray.add("F");


        System.out.println(dynamicArray);
        System.out.println(dynamicArray.size);

        System.out.println(dynamicArray);
        System.out.println("size : " + dynamicArray.size);
        System.out.println("capacity : " + dynamicArray.capacity);
        System.out.println("Empty : " + dynamicArray.isEmpty());

        dynamicArray.insert(0, "X");
        System.out.println(dynamicArray);
        System.out.println(dynamicArray.size);

        dynamicArray.delete("A");
        System.out.println(dynamicArray);
        System.out.println(dynamicArray.size);

        System.out.println("index : " + dynamicArray.search("X"));




    }
}
