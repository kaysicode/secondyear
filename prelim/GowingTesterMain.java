package prelim;

public class GowingTesterMain {
    public static void main(String[] args) {
        MyGrowingArrayList<Assignments> growingArrayList = new MyGrowingArrayList<>();

        try {
            growingArrayList.insert(new Assignments("Coding", 8, 12));
            growingArrayList.insert(new Assignments("Programming", 8, 12));
            growingArrayList.insert(new Assignments("Reading", 9, 12));
            growingArrayList.insert(new Assignments("Review", 6, 12));
            growingArrayList.insert(new Assignments("PT", 4, 12));
            growingArrayList.insert(new Assignments("Activity", 1, 12));
            growingArrayList.insert(new Assignments("Watching Documentary", 5, 12));
            growingArrayList.insert(new Assignments("Coding", 7, 12));


            System.out.println(growingArrayList.getSize());
            System.out.println(growingArrayList);
            System.out.println(growingArrayList);

        } catch (ListOverFlowException e) {
            e.printStackTrace();
        }

    }
}
