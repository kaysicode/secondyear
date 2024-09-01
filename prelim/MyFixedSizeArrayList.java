package prelim;

import java.util.NoSuchElementException;

public class MyFixedSizeArrayList<E> implements MyList<E>{

    private int size;
    private int MAXIMUM_CAPACITY = 5;
    private E[] array;


    public MyFixedSizeArrayList() {
        this.array = (E[]) new Object[MAXIMUM_CAPACITY];
    }
    public MyFixedSizeArrayList(int userCapacity) {
        this.MAXIMUM_CAPACITY = userCapacity;
        this.array = (E[]) new Object[MAXIMUM_CAPACITY];
    }
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * This is method from the implementation
     * of the MyList to override a insert method
     * just like what other collection do.
     * @param data -> This is what user input or give argument in our main class
     * @throws ListOverFlowException -> This throw was user-defined to check if it's over 5
     */
    @Override
    public void insert(E data) throws ListOverFlowException{
        if (size >= MAXIMUM_CAPACITY) {
            throw new ListOverFlowException("Array is overflowing!!");
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
            if (array[i].equals(data)) {
                for(int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[size - 1] = null;
                size --;
                isDeleted = true;
                break;
            }
        }
        return isDeleted  ;
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
