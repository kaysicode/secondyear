package prelim;

public class MyGrowingArrayList<E> extends MyFixedSizeArrayList<E>{
    private final int NEW_CAPACITY = getMAXIMUM_CAPACITY() * 2;
    private E[] newArray;
    public MyGrowingArrayList() {
        this.newArray = (E[]) new Object[NEW_CAPACITY];
        System.arraycopy(getArray(), 0, newArray, 0, getSize());
        setSize(getSize());
    }

    @Override
    public void insert(E data) throws ListOverFlowException{
        if (super.getSize() >= NEW_CAPACITY) {
            throw new ListOverFlowException("Only Max of 5 is available, the Array is overflowing!!");
        }
        this.newArray[getSize()] = data;
        setSize(getSize() + 1);
    }

    @Override
    public String toString() {
        String message = "";

        for (int i = 0; i< super.getSize(); i++){
            message += newArray[i] + ", ";
        }
        if (message != "") {
            message = "[" + message.substring(0, message.length() - 2) + "]";
        } else {
            message = "[]";
        }
        return message;
    }



}
