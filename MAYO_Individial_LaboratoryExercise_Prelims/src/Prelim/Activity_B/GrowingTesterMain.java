package Prelim.Activity_B;

import Prelim.Activity_A.ListOverFlowException;


public class GrowingTesterMain {
    public static void main(String[] args) {

        // Create a new instance of MyGrowingArrayList to hold Assignments objects
        MyGrowingArrayList<Assignments> growingArrayList = new MyGrowingArrayList<>();
        try {
            // Insert various Assignments objects into the growingArrayList
            growingArrayList.insert(new Assignments("Laboratory Programming 1:45", "8 AM", "12:59 PM"));
            growingArrayList.insert(new Assignments("Lecture", "8:00 AM", "12:59 PM"));
            growingArrayList.insert(new Assignments("Programming Assignment", "9:30 AM", "12:59 PM"));
            growingArrayList.insert(new Assignments("Prog 2 Project", "6:45 AM", "12:59 PM"));
            growingArrayList.insert(new Assignments("Fit Practical Assignment", "4:00 PM", "12:59 PM"));
            growingArrayList.insert(new Assignments("GENVI Assignment", "1:30 PM", "12:59 PM"));
            growingArrayList.insert(new Assignments("Watching Documentary", "5:25 PM", "12:59 PM"));
            growingArrayList.insert(new Assignments("GSTS Assignment", "7:30 AM", "12:59 PM"));
            growingArrayList.insert(new Assignments("CFE Religious Response Assignment", "7:00 AM", "12:59 PM"));

            // Print the size of the list
            System.out.println("The Size of the List : ");
            System.out.println(growingArrayList.getSize());
            System.out.println();

            // Program will sleep for 2 Seconds
            Thread.sleep(2000);

            // Print all elements in the growingArrayList
            System.out.println("The elements in the List : ");
            System.out.println(growingArrayList);
            System.out.println();

            // Program will sleep for 2 Seconds
            Thread.sleep(2000);

            // Add a new assignment in the List
            System.out.println("""
                    Adding a object in the List :
                    Project Name : Laboratory Activity 2
                    Date Assigned : 1:35 PM
                    Date Submitted :  12:59 PM""");
            growingArrayList.insert(new Assignments("Laboratory Activity 2", "1:35 PM", "12:59 PM"));
            System.out.println("Current Size of the List : " + growingArrayList.getSize());
            System.out.println(growingArrayList); //Print all elements to check if the new one is added
            System.out.println();

            // Program will sleep for 2 Seconds
            Thread.sleep(2000);

            //Get the element in the list by using the project name
            System.out.println("Getting a Element in the List : ");
            System.out.println("Project Name will be get is Laboratory Activity 2");
            String projectName = "Laboratory Activity 2";
            Assignments assign = new Assignments();
            assign.setProjectName(projectName);
            Assignments foundProjectName = growingArrayList.getElement(assign);
            System.out.println(foundProjectName);

            // Program will sleep for 2 Seconds
            Thread.sleep(2000);

            //Delete the element in the list
            System.out.println("Deleting a Element in the List : ");
            System.out.println("Project Name will be delete is Laboratory Activity 2");
            String deleteName = "Laboratory Activity 2";
            Assignments assignDelete = new Assignments();
            assignDelete.setProjectName(deleteName);
            System.out.println(growingArrayList.delete(assignDelete));
            System.out.println();

            // Program will sleep for 2 Seconds
            Thread.sleep(2000);

            // Search the index of the element in the list by using the Project Name
            System.out.println("Searching a Element in the List : ");
            System.out.println("Project Name will be delete is Prog 2 Project");
            String searchName = "Prog 2 Project";
            Assignments search = new Assignments();
            search.setProjectName(searchName);
            System.out.println("Index : " + growingArrayList.search(search));
            System.out.println();

            // Program will sleep for 2 Seconds
            Thread.sleep(2000);

            // Insert some new assignment to check if the ListOverFlowException is working
            // The Max will be 10 since it is twice the size of the MyFixedArrayList
            growingArrayList.insert(new Assignments("Laboratory Activity 2", "1:35 PM", "12:59 PM"));
            growingArrayList.insert(new Assignments("Laboratory Activity 2", "1:35 PM", "12:59 PM"));
            growingArrayList.insert(new Assignments("Laboratory Activity 2", "1:35 PM", "12:59 PM"));
            System.out.println();


        } catch (ListOverFlowException e) { // Catch clause for the ListOVerFlowException to catch if the list is greater than the 10
            e.printStackTrace();
        } catch (InterruptedException e) { // For the thread to check if it got interrupted
            e.printStackTrace();
        }

    }
}
