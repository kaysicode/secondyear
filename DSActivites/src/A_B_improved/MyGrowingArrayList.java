package A_B_improved;

public class MyGrowingArrayList<E> extends MyFixedArrayList<E>{

    public MyGrowingArrayList () {
        super.setArray((E[]) new Object[getCapacity()]);
    }

@Override
public void insert(E data) {
    if (getSize() >= getCapacity()) {
        int newCapacity = getCapacity() * 2;
        E[] newArray = (E[]) new Object[newCapacity];

        for (int i = 0; i < getSize(); i++) {
            newArray[i] = getArray()[i];
        }
        setCapacity(newCapacity);
        setArray(newArray);
    }
    getArray()[getSize()] = data;
    setSize(getSize() + 1);
}
}
