package TreeExample;
// Class implementing the tree structure using the TreeInterface
public class Tree<T extends Comparable<T>> implements TreeInterface<T> {
    private TreeNode<T> root;

    // Insert method to add a new node to the tree
    @Override
    public void insert(T data) {
        root = insertRec(root, data);
    }

    // Recursive insert helper method
    private TreeNode<T> insertRec(TreeNode<T> root, T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            return root;
        }

        if (data.compareTo(root.getData()) < 0) {
            root.setLeft(insertRec(root.getLeft(), data));
        } else if (data.compareTo(root.getData()) > 0) {
            root.setRight(insertRec(root.getRight(), data));
        }

        return root;
    }

    // Delete method to remove a node from the tree
    @Override
    public void delete(T data) {
        root = deleteRec(root, data);
    }

    // Recursive delete helper method
    private TreeNode<T> deleteRec(TreeNode<T> root, T data) {
        if (root == null) {
            return root;
        }

        if (data.compareTo(root.getData()) < 0) {
            root.setLeft(deleteRec(root.getLeft(), data));
        } else if (data.compareTo(root.getData()) > 0) {
            root.setRight(deleteRec(root.getRight(), data));
        } else {
            // Node with only one child or no child
            if (root.getLeft() == null)
                return root.getRight();
            else if (root.getRight() == null)
                return root.getLeft();

            // Node with two children: get the inorder successor (smallest in the right subtree)
            root.setData(minValue(root.getRight()));

            // Delete the inorder successor
            root.setRight(deleteRec(root.getRight(), root.getData()));
        }

        return root;
    }

    // Helper method to find the smallest value in a subtree
    private T minValue(TreeNode<T> root) {
        T minValue = root.getData();
        while (root.getLeft() != null) {
            minValue = root.getLeft().getData();
            root = root.getLeft();
        }
        return minValue;
    }

    // Search method to determine if the data is in the left or right subtree of the root
    @Override
    public String search(T data) {
        if (root == null) {
            return "The tree is empty.";  // Check if the tree is empty
        }

        // Check if the data is the root
        if (root.getData().equals(data)) {
            return "Node is the root.";  // Node is the root
        }

        // Compare with root to determine direction
        if (data.compareTo(root.getData()) < 0) {
            return ("Node " + data + " is in the left subtree of the root.");  // Data is less than root
        } else {
            return ("Node " + data + " is in the right subtree of the root."); // Data is greater than root
        }
    }



    // Traverse method using in-order traversal to display the tree nodes
    @Override
    public void traverse() {
        traverseInOrder(root);
        System.out.println();
        traversePreOrder(root);
        System.out.println();
        traversePostOrder(root);
        System.out.println();
    }

    // In-order traversal: left -> root -> right
    private void traverseInOrder(TreeNode<T> root) {
        if (root != null) {
            traverseInOrder(root.getLeft());
            System.out.print(root.getData() + " ");
            traverseInOrder(root.getRight());
        }
    }

    // Pre-order traversal: root -> left -> right
    private void traversePreOrder(TreeNode<T> root) {
        if (root != null) {
            System.out.print(root.getData() + " ");
            traversePreOrder(root.getLeft());
            traversePreOrder(root.getRight());
        }
    }

    // Post-order traversal: left -> right -> root
    private void traversePostOrder(TreeNode<T> root) {
        if (root != null) {
            traversePostOrder(root.getLeft());
            traversePostOrder(root.getRight());
            System.out.print(root.getData() + " ");
        }
    }
}
