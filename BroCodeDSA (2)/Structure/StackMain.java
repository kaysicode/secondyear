package BroCodeDSA.Structure;

public class StackMain {
    @SuppressWarnings("ALL")
    public static void main(String[] args) {

        // stack = LIFO data structure, Last-In First-Out.
        //         stores objects into a sort of "vertical tower"
        //         push() to add to the top
        //         pop() to remove from the top

        // uses of STACKS?
        // 1. undo/redo features in text
        // 2. moving b through browser history
        // 3. backtracking algorithm (maze, file directories)
        // 4. calling functions (call stack)

        java.util.Stack<String> stack = new java.util.Stack<>();

        stack.push("Minecraft");
        stack.push("Roblox");
        stack.push("PixelWorlds");
        stack.push("Elden Ring");
        stack.push("Mobile Legends");

        //System.out.println(stack.empty()); //It will check if the stack is empty and return a boolean

        String latestRemoved = stack.pop(); //it will remove the latest object in the stack

        System.out.println("Latest Object Added :");
        System.out.println(stack.peek()); //Check the latest added object
        System.out.println();

        System.out.println("List of Object in the Stack :");
        System.out.println(stack); //It will list all the object within the list
        System.out.println();

        System.out.println("Latest Removed in the List :");
        System.out.println(latestRemoved); //It will print what object is removed
        System.out.println();

        System.out.println(stack.search("Roblox"));  //It will search in the stack after that, return the position (1) not index (0).
                                                        //If it doesn't find anything inside of stack, it will return -1.

    /*
    This code will make an exception which is OutOfMemory
    since stack will run of memory which is 1 billion
       for (int i = 0; i < 100000000; i++) {
           stack.push("The Finals");
       }
    */
    }
}
