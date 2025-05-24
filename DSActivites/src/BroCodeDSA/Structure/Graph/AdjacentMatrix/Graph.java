package BroCodeDSA.Structure.Graph.AdjacentMatrix;

import java.util.ArrayList;

public class Graph {

    ArrayList<Node> node;

    int[][] matrix;

    public Graph(int size) {
        matrix = new int[size][size];
        node = new ArrayList<>();
    }

    public void addNode(Node node) {
        this.node.add(node);

    }

    public void addEdge(int src, int dst) {
        matrix[src][dst] = 1;
    }

    public boolean checkEdge(int src, int dst) {
        return matrix[src][dst] == 1;
    }

    public void print() {
        System.out.print("  ");
        for(Node nodes : node) {
            System.out.print(nodes.getData() + " ");
        }
        System.out.println();

        for(int i = 0; i < matrix.length; i++) {
            System.out.print(node.get(i).getData()+ " ");
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
