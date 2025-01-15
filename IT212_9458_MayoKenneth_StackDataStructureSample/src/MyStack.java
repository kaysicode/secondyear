/**
 * MyStack is a generic stack implementation that follows the LIFO (Last In, First Out) principle.
 * This class implements the MyStackInterface, providing basic stack operations such as push, pop, and peek.
 *
 * @param <T> the type of elements held in this stack
 */
/*
Name : MAYO, Kenneth Charles P.
Date : October 15, 2024
 */
public class MyStack<T> implements MyStackInterface<T> {
    /**
     * The top node of the stack.
     */
    private Node<T> top;

    /**
     * The count of elements in the stack.
     */
    private int count;

    /**
     * Constructs an empty MyStack.
     */
    public MyStack() {
        this.top = null;
        this.count = 0;
    }

    /**
     * Pushes an item onto the top of the stack.
     *
     * @param item the item to be pushed onto the stack
     */
    @Override
    public void push(T item) {
        Node<T> newNode = new Node<>(item, null);
        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.setNext(top);
            top = newNode;
        }
        count += 1;
    }

    /**
     * Removes and returns the item at the top of the stack.
     *
     * @return the item at the top of the stack
     * @throws StackUnderflowException if the stack is empty
     */
    @Override
    public T pop() throws StackUnderflowException{
        T topElement;
        if (isEmpty()) {
            throw new StackUnderflowException("Stack is empty");
        }
        else {
            topElement = top.getDatum();
            if (count == 1) {
                top = null;
            } else {
                top = top.getNext();
            }
            count -= 1;
        }
        return topElement;
    }

    /**
     * Returns the item at the top of the stack without removing it.
     *
     * @return the item at the top of the stack
     * @throws StackUnderflowException if the stack is empty
     */
    @Override
    public T peek() throws StackUnderflowException {
        T topElement;
        if (isEmpty()) {
            throw new StackUnderflowException("Stack is empty");
        } else {
            topElement = top.getDatum();
            if (count == 1) {
                this.top = null;
            }
            this.count -= 1;
        }
        return topElement;
    }

    /**
     * Returns the number of items in the stack.
     *
     * @return the size of the stack
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Returns whether the stack is empty.
     *
     * @return {@code true} if the stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }
}
