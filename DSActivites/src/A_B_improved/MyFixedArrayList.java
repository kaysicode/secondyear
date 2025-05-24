package A_B_improved;

import java.util.NoSuchElementException;

public class MyFixedArrayList <E> implements MyList<E>{

    private int capacity = 5;
    private int size;
    private E[] array;

    public MyFixedArrayList () {
        this.array = (E[]) new Object[capacity];
    }
    public MyFixedArrayList(int capacity) {
        this.capacity = capacity;
        this.array = (E[]) new Object[capacity];
    }

    // ******************************************************
    public E[] getArray() {
        return array;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setArray(E[] array) {
        this.array = array;
    }
    public void setSize(int size) {
        this.size = size;
    }

    // ******************************************************
    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void insert(E data) throws ListOverFlowException {
        if (size >= capacity) {
            throw new ListOverFlowException("Only Max of 5 is available, the Array is overflowing!!");
        }
        this.array[size] = data;
        this.size++;
    }

    @Override
    public E getElement(E data) throws NoSuchElementException {
        for (E element : array) {
            if (data.equals(element)) {
                return element;
            }
        }
        throw new NoSuchElementException("Element not found: " + data);
    }

    @Override
    public boolean delete(E data) {
        boolean isDeleted = false;

        for (int i = 0; i < size; i++) {
            if(array[i] == data) {
                for (int j = 0; j < (size - i - 1); j++) {
                    array[j + i] = array[j + i + 1];
                }
                array[size - 1] = null;
                size--;
                isDeleted = true;
                break;
            }
        }
        return isDeleted;
    }

    @Override
    public int search(E data) {
        for (int i = 0; i < size; i++) {
            if (array[i] == data) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String message = "";

        for (int i = 0; i< size; i++){
            message += array[i] + ", ";
        }
        if (message != "") {
            message = "[" + message.substring(0, message.length() - 2) + "]";
        } else {
            message = "[]";
        }
        return message;
    }
}
