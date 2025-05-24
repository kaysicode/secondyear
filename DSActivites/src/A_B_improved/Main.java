package A_B_improved;

public class Main {
    public static void main(String[] args) {
        MyFixedArrayList<Integer> arrayList = new MyFixedArrayList<>();
        MyGrowingArrayList<Integer> growingArrayList = new MyGrowingArrayList<>();

        try {
            //FixedArrayList
            arrayList.insert(1);
            arrayList.insert(2);
            arrayList.insert(3);
            arrayList.insert(4);
            arrayList.insert(5);
            System.out.println(arrayList);
            System.out.println("size : " + arrayList.getSize());
            System.out.println("Capacity : " + arrayList.getCapacity());
            System.out.println(arrayList.getElement(3));
            System.out.println("Deleted : " + arrayList.delete(5));
            System.out.println("Searched : " + arrayList.search(1));

            System.out.println();

            //GrowingArrayList
            growingArrayList.insert(1);
            growingArrayList.insert(2);
            growingArrayList.insert(3);
            growingArrayList.insert(4);
            growingArrayList.insert(5);
            growingArrayList.insert(6);
            growingArrayList.insert(7);
            System.out.println(growingArrayList);
            System.out.println("size : " + growingArrayList.getSize());
            System.out.println("Capcity : " + growingArrayList.getCapacity());
            System.out.println(growingArrayList.getElement(7));
            System.out.println("Deleted : " + growingArrayList.delete(7));
            System.out.println("Searched : " + growingArrayList.search(6));


        } catch (ListOverFlowException e) {
            e.printStackTrace();
        }
    }
}
