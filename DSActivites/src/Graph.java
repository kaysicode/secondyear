import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjacencyList;

    // Constructor
    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Add an edge to the graph (directed)
    public void addEdge(int source, int destination) {
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.putIfAbsent(destination, new ArrayList<>());
        adjacencyList.get(source).add(destination);
    }

    // Print the graph
    public void printGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    // Check if the graph contains a vertex
    public boolean containsVertex(int vertex) {
        return adjacencyList.containsKey(vertex);
    }

    // Check if the graph contains an edge
    public boolean containsEdge(int source, int destination) {
        return adjacencyList.containsKey(source) && adjacencyList.get(source).contains(destination);
    }

    // Get neighbors of a vertex
    public List<Integer> getNeighbors(int vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }
}

class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Add vertices
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        // Add edges
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(3, 4);
        graph.addEdge(4,3);
        graph.addEdge(2,4);

        // Print the graph
        graph.printGraph();

        // Check if a vertex exists
        System.out.println("Contains vertex 2: " + graph.containsVertex(2));

        // Check if an edge exists
        System.out.println("Contains edge 1->2: " + graph.containsEdge(1, 2));

        // Get neighbors of a vertex
        System.out.println("Neighbors of 2: " + graph.getNeighbors(2));
    }
}
