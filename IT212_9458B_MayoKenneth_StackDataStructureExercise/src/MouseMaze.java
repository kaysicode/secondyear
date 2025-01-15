/**
 * Sample Mouse Maze Puzzle.
 * The mouse(M) is positioned in a cell of the maze.
 * The mouse sequentially moves to open cells until the mouse reaches the exit cell E.
 * An open cell is marked by 0.
 * A closed cell is marked by 1.
 * by : <MAYO Kenneth Charles P.>
 * Algorithm:
 * 1. Initialize the maze with a 2D array of characters, where '1' represents walls, '0' represents open cells,
 *    'M' is the starting position of the mouse, and 'E' is the exit.
 * 2. Search for the entry point 'M' (mouse position) and the exit point 'E' in the maze.
 *    Save their coordinates for later use.
 * 3. Display the initial maze and explain the rules (mouse has to find the exit through open cells marked by '0').
 * 4. Initialize a stack (mazeStack) to store cells to visit during exploration.
 * 5. Set the current cell to the entry point (mouse position).
 * 6. While the current cell is not equal to the exit:
 *    a. Mark the current cell as visited if it’s not the starting point by setting its value to '.'.
 *    b. Push neighboring open cells (up, down, left, right) onto the stack using the pushUnvisited method:
 *       - Only open cells ('0') or the exit ('E') are pushed onto the stack.
 *    c. If the stack is empty, display failure (no path to the exit) and stop.
 *    d. Otherwise, pop the next cell from the stack and set it as the current cell.
 *    e. Display the updated maze after each step, and wait for user input to continue.
 * 7. If the current cell matches the exit, display success and the final state of the maze.
 * 8. End the program.
 *
 * A output example of the program
 *
 * 111111
 * 1100E1
 * 100111
 * 100001
 * 1100M1
 * 101111
 *
 * The above figure shows a maze where a mouse M is in.
 * The Mouse M should move to exhaustively find the Exit cell E
 * A cell marked 0 is an open cell, a cell marked by 1 is a closed cell
 * Keep pressing the enter key until success or failure is reached.
 * Find the way out.
 *
 * 111111
 * 1100E1
 * 100111
 * 100001
 * 110.M1
 * 101111
 *
 * Find the way out.
 *
 * 111111
 * 1100E1
 * 100111
 * 100001
 * 11..M1
 * 101111
 *
 * Find the way out.
 *
 * 111111
 * 1100E1
 * 100111
 * 10.001
 * 11..M1
 * 101111
 *
 * Find the way out.
 *
 * 111111
 * 1100E1
 * 100111
 * 10..01
 * 11..M1
 * 101111
 *
 * Find the way out.
 *
 * 111111
 * 1100E1
 * 100111
 * 10...1
 * 11..M1
 * 101111
 *
 * Find the way out.
 *
 * 111111
 * 1100E1
 * 100111
 * 1....1
 * 11..M1
 * 101111
 *
 * Find the way out.
 *
 * 111111
 * 1100E1
 * 1.0111
 * 1....1
 * 11..M1
 * 101111
 *
 * Find the way out.
 *
 * 111111
 * 1100E1
 * 1..111
 * 1....1
 * 11..M1
 * 101111
 *
 * Find the way out.
 *
 * 111111
 * 11.0E1
 * 1..111
 * 1....1
 * 11..M1
 * 101111
 *
 * Find the way out.
 *
 * 111111
 * 11..E1
 * 1..111
 * 1....1
 * 11..M1
 * 101111
 *
 * Find the way out.
 *
 * 111111
 * 11..E1
 * 1..111
 * 1....1
 * 11..M1
 * 101111
 *
 * Success! Exit found
 *
 * Process finished with exit code 0
 *
 *
 *
 */
import java.io.*;
import java.lang.*;
import java.util.Stack;
import java.util.Scanner;

@SuppressWarnings("ALL") // Added this for the warnings that were not even needed
public class MouseMaze {
    private char[][] myMaze =
                    {{'1', '1', '1', '1', '1', '1'},
                    {'1', '1', '0', '0', 'E', '1'},
                    {'1', '0', '0', '1', '1', '1'},
                    {'1', '0', '0', '0', '0', '1'},
                    {'1', '1', '0', '0', 'M', '1'},
                    {'1', '0', '1', '1', '1', '1'}};
    private int rows = myMaze.length;
    private int cols = myMaze[0].length;
    private MazeCell currentCell = null;
    private MazeCell exitCell = new MazeCell();
    private MazeCell entryCell = new MazeCell();
    private final char EXIT_MARKER = 'E';
    private final char ENTRY_MARKER = 'M';
    private final char VISITED = '.';
    private final char PASSAGE = '0';
    private final char WALL = '1';
    private Stack<MazeCell> mazeStack = new Stack<MazeCell>();
    private FileReader fileReader;
    private BufferedReader bufferReader;
    private Scanner keyboard = new Scanner(System.in);

    public MouseMaze() {
        boolean foundEntryCell = false;
        boolean foundExitCell = false;
/**
 * Look for the entry cell, the initial location of the mouse
 * */
        for (int row = 0; row < myMaze.length && !foundEntryCell; row++)
            for (int col = 0; col < myMaze[row].length && !foundEntryCell; col++) {
                if (myMaze[row][col] == 'M') {
                    entryCell.setRow(row);
                    entryCell.setColumn(col);
                    foundEntryCell = true;
                }
            }
/**
 * Look for the exit cell, the cell where the mouse may jump out of the maze
 * */
        for (int row = 0; row < myMaze.length && !foundExitCell; row++)
            for (int col = 0; col < myMaze[row].length && !foundExitCell; col++) {

                if (myMaze[row][col] == 'E') {
                    exitCell.setRow(row);
                    exitCell.setColumn(col);
                    foundExitCell = true;
                }
            }
    }// end of Maze constructor

    /**
     * Show the maze with the current path followed by the mouse if any
     */
    private void display(char[][] myMaze) {
        for (int row = 0; row < myMaze.length; row++) {
            for (int col = 0; col < myMaze[row].length; col++)
                System.out.print(myMaze[row][col]);
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Puts a cell with the given row and col index into a stack of cells to be visited
     * if the cell is marked as an open cell or exit cell
     */
    private void pushUnvisited(int row, int col) {
        if (myMaze[row][col] == PASSAGE || myMaze[row][col] == EXIT_MARKER)
            mazeStack.push(new MazeCell(row, col));
    }

    /**
     * Let the mouse finds its way to the exit cell
     */
    public void findWayOut() throws IOException {
        int row = 0;
        int col = 0;
    // Start from the entry cell, the cell where the mouse is initially placed
        currentCell = entryCell;
        System.out.println();
        display(myMaze);
        System.out.println("The above figure shows a maze where a mouse M is in.");
        System.out.println("The Mouse M should move to exhaustively find the Exit cell E");
        System.out.println("A cell marked 0 is an open cell, a cell marked by 1 is a closed cell");
        System.out.println("Keep pressing the enter key until success or failure is reached.");
        System.out.println("Find the way out.");

        keyboard.nextLine();
        while (!currentCell.equals(exitCell)) {
            row = currentCell.getRow();
            col = currentCell.getColumn();
            if (currentCell.sameAs(exitCell)) {
                display(myMaze);
                System.out.println("Success! Exit found");
                break;
            }
            if (!currentCell.sameAs(entryCell)) {
                myMaze[row][col] = VISITED;
                display(myMaze);
                System.out.println("Find the way out.");
                keyboard.nextLine();
            }
/**
 * Create a Stack of the cells to be explored following the fixed order
 * up, down, left and right.
 *
 * A cell is included in the cell to be explored only if the cell is an open cell.
 * The pushUnvisited method is written such that it will only put the cell in the Stack if
 * the cell is open
 */
            pushUnvisited(row - 1, col); // note if cell up is open
            pushUnvisited(row + 1, col); // note if cell down is open
            pushUnvisited(row, col - 1); // note if cell at left is open
            pushUnvisited(row, col + 1); // note if cell at right is open

            if (mazeStack.isEmpty()) {
                display(myMaze);
                System.out.println("Failure: Exit cannot be reached");
                return;
            } else {
                currentCell = (MazeCell) mazeStack.pop(); // try to move to a reachable cell
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        MouseMaze solver;

        Stack<String> stack = new Stack<>();
        try {
            solver = new MouseMaze();
            solver.findWayOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    } // end of main
}// ebd of class
