/*
NAME : MAYO, Kenneth Charles P.
DATE : October 15, 2024
 */

/**
 * Interface of MyStack
 *
 * @param <T> the type of elements stored in the stack
 */
public interface MyStackInterface<T> {
    public void push (T item);
    public T pop() throws StackUnderflowException;
    public T peek() throws StackUnderflowException;
    public int size(); // return the size of the stack
    public boolean isEmpty(); // check if it's empty and then return a boolean
} // end of MyStackInterface
