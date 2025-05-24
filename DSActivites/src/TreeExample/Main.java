package TreeExample;


public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();

        tree.insert(5);
        tree.insert(3);
        tree.insert(10);
        tree.insert(2);
        tree.insert(6);

        tree.traverse();

        System.out.println(tree.search(5));
        System.out.println(tree.search(10));
        System.out.println(tree.search(2));

        tree.delete(6);
        tree.traverse();
    }
}
