package Prelim.Activity_B;

import Prelim.Activity_A.ListOverFlowException;
import Prelim.Activity_A.MyFixedSizeArrayList;

import java.util.NoSuchElementException;

/**
 * MyGrowingArrayList is a Dynamic Array too same with MyFixedArrayList
 * but this one, I just extended to the MyFixedArrayList
 * since some of the methods are same so it much more flexible to use the inheritance
 * than to use the interface. But in this collection it has twice of the size of the
 * MyFixedArrayList.
 * So, in each method you will make own algorithm to
 * insert, getSize, getElement, delete, search
 * the data
 *<br>
 *
 *
 * Submitted by <br>
 * NAME : MAYO, Kenneth Charles P. <br>
 * CODE : IT 212 9458B <br>
 * DATE : September 03, 2024
 *
 *
 * @param <E> generics as to for the user to input or insert any datatype he wanted.
 *           Since that generics can work with any type of object, without specifying the
 *           exact type in advance. This makes your code more flexible and reusable.
 */
public class MyGrowingArrayList<E> extends MyFixedSizeArrayList<E> {
    private final int NEW_CAPACITY = getMAXIMUM_CAPACITY() * 2; // multiply it by 2 to make the size twice of the MyFixedArrayList
    private E[] newArray; // New Array Object using generics

    // Default Constructor
    public MyGrowingArrayList() {
        this.newArray = (E[]) new Object[NEW_CAPACITY];
        System.arraycopy(getArray(), 0, newArray, 0, getSize());
        setSize(getSize());
    }

    /**
     * This is method from the implementation
     * of the MyList to override an insert method
     * just like what other collection do. The user will give an argument depend on what object
     * and then check if the size is greater than the MAXIMUM_CAPACITY(5)
     * If not, the data will be inserted in the last index.
     * @param data -> This is what user input or give argument in our main class
     * @throws ListOverFlowException -> This throw was user-defined to check if it's over 5
     */
    @Override
    public void insert(E data) throws ListOverFlowException {
        if (super.getSize() >= NEW_CAPACITY) {
            throw new ListOverFlowException("Only Max of 10 is available, the Array is overflowing!!");
        }
        this.newArray[getSize()] = data;
        setSize(getSize() + 1);
    }

    /**
     * This method used to get the Element of my Object which is the "Model" of the Car
     * as it's to check if there's a such model in my list and if there's, it will return the car associated
     * to the car model, then if not an Exception will throw.
     * @param data -> A user input for the model of the car to check if there's such model in the List.
     * @return -> It will return the Object associated.
     * @throws NoSuchElementException -> A exception, so if there's no such model inside the List. Stated "Element not found".
     */

    @Override
    public E getElement(E data) throws NoSuchElementException {
        for (E element : newArray) {
            if (data.equals(element)) {
                return element;
            }
        }
        throw new NoSuchElementException("Element not found: " + data);
    }

    /**
     * This method used for deleting the element in the List by iterating through the element
     * and then if data is found, the structure will go left to right, so the element that
     * was found will remove by replacing it by the next element.
     * @param data -> the parameter will be whatever the user input that he wants to delete.
     * @return isDeleted -> it will return a boolean either it was deleted or not.
     */

    @Override
    public boolean delete(E data) {
        boolean isDeleted = false;

        for (int i = 0; i < getSize(); i++) {
            if (newArray[i].equals(data)) {
                for(int j = i; j < getSize() - 1; j++) {
                    newArray[j] = newArray[j + 1];
                }
                newArray[getSize() - 1] = null;
                setSize(getSize() -1);
                isDeleted = true;
                break;
            }
        }
        return isDeleted  ;
    }

    /**
     * This method used to search the data through the List
     * so this method use a linear search algorithm to check id the element
     * is the same with the user's date.
     * @param data -> User input expected to input an element with any datatype since it's a generics
     * @return ->  return the index if it was found else, return -1
     */

    @Override
    public int search(E data) {
        for (int i = 0; i < getSize(); i++) {
            if (newArray[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * To make a String format for the List
     * iterating each element in the List
     * and then insert into one String which is the message
     * @return -> (message) return the String format of the list
     */
    @Override
    public String toString() {
        String message = "";

        for (int i = 0; i< super.getSize(); i++){
            message += newArray[i];
        }
        if (message != "") {
            message = "[" + message.substring(0, message.length() - 2) + "]";
        } else {
            message = "[]";
        }
        return message;
    }



}
