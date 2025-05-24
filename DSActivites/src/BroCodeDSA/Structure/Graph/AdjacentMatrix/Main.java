package BroCodeDSA.Structure.Graph.AdjacentMatrix;

public class Main {
    public static void main(String[] args) {

        // Adjacency Matrix = A 2D Array to store 1's/0's to represent edges
        //                    # of rows =    # of unique nodes
        //                    # of columns = # of unique nodes

        //                    runtime complexity to check an Edge: O(1)
        //                    space complexity: O(n^2)

        Graph graph = new Graph(5);

        graph.addNode(new Node('A'));
        graph.addNode(new Node('B'));
        graph.addNode(new Node('C'));
        graph.addNode(new Node('D'));
        graph.addNode(new Node('E'));

        // A
        graph.addEdge(0, 1); // A to B

        // B
        graph.addEdge(1,2); // B to C
        graph.addEdge(1,4); // B to E

        // C
        graph.addEdge(2, 4); // C to E
        graph.addEdge(2, 3); // C to D

        // E
        graph.addEdge(4, 2); // E to C
        graph.addEdge(4, 0); // E to A

        graph.print();

        // Check if the Vertices A and B has Edge
        System.out.println(graph.checkEdge(0, 1));

        // Check if the Vertices D and C has Edge
        System.out.println(graph.checkEdge(3, 2));
    }
}
