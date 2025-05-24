package TreeExample;

public interface TreeInterface<T> {
    public void insert (T data);
    public void delete (T data);
    public void traverse (); // using in-order traversal
    public String search (T data);

}
