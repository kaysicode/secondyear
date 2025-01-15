package finlab;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.Timer;

public class DijkstraGUI extends JPanel {
    private final Map<Character, Point> nodePositions = new HashMap<>();
    private final Map<Character, List<Edge>> graph = new HashMap<>();
    private final Set<Character> visited = new HashSet<>();
    private final Map<Character, Integer> distances = new HashMap<>();
    private final Map<Character, Character> predecessors = new HashMap<>();
    private final PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getDistance));
    private final int radius = 30; // Node circle radius
    private String traversalResult = "";
    private String shortestPath = "";
    private int shortestPathDistance = Integer.MAX_VALUE;
    private char startVertex;
    private char destinationVertex;
    private JFrame frame;

    public DijkstraGUI() {
        frame = new JFrame("Dijkstra's Algorithm Animation");
        frame.add(this);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Get user input for the starting vertex
        String input = JOptionPane.showInputDialog(frame, "Enter starting vertex (a to z):");
        if (input != null && input.length() == 1) {
            startVertex = input.charAt(0);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a single letter from 'a' to 'z'.");
            System.exit(0);
        }

        // Get user input for the destination vertex
        String destinationInput = JOptionPane.showInputDialog(frame, "Enter destination vertex (a to z):");
        if (destinationInput != null && destinationInput.length() == 1) {
            destinationVertex = destinationInput.charAt(0);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a single letter from 'a' to 'z'.");
            System.exit(0);
        }

        // Define the graph with weighted edges
        graph.put('a', Arrays.asList(new Edge('b', 1), new Edge('c', 4)));
        graph.put('b', Arrays.asList(new Edge('a', 1), new Edge('d', 2)));
        graph.put('c', Arrays.asList(new Edge('a', 4), new Edge('e', 3)));
        graph.put('d', Arrays.asList(new Edge('b', 2), new Edge('e', 5), new Edge('f', 1)));
        graph.put('e', Arrays.asList(new Edge('c', 3), new Edge('d', 5), new Edge('g', 2)));
        graph.put('f', Arrays.asList(new Edge('d', 1), new Edge('z', 7)));
        graph.put('g', Arrays.asList(new Edge('e', 2), new Edge('z', 3)));
        graph.put('z', Arrays.asList(new Edge('f', 7), new Edge('g', 3)));

        // Define node positions
        nodePositions.put('a', new Point(50, 200));
        nodePositions.put('b', new Point(150, 100));
        nodePositions.put('c', new Point(150, 300));
        nodePositions.put('d', new Point(300, 100));
        nodePositions.put('e', new Point(300, 300));
        nodePositions.put('f', new Point(450, 100));
        nodePositions.put('g', new Point(450, 300));
        nodePositions.put('z', new Point(600, 200));

        // Initialize distances and add starting node to the priority queue
        for (char node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);
        pq.add(new Node(startVertex, 0, null));

        // Start Dijkstra's Algorithm Animation using javax.swing.Timer
        new Timer(1000, e -> {
            if (!pq.isEmpty()) {
                Node currentNode = pq.poll();
                char current = currentNode.vertex;

                // Skip already visited nodes
                if (visited.contains(current)) return;

                visited.add(current);
                traversalResult += current + " -> ";

                // Process neighbors
                for (Edge edge : graph.get(current)) {
                    char neighbor = edge.target;
                    int newDist = distances.get(current) + edge.weight;

                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        predecessors.put(neighbor, current);
                        pq.add(new Node(neighbor, newDist, null));
                    }
                }

                if (current == destinationVertex) {
                    buildShortestPath();
                    ((Timer) e.getSource()).stop();
                }

                repaint(); // Redraw the graph with updated distances
            }
        }).start();
    }

    private void buildShortestPath() {
        // Create a list to store the path by tracing back from the destination vertex
        List<Character> path = new ArrayList<>();

        // Traverse backwards from the destination vertex to the start vertex using the predecessors map
        for (Character at = destinationVertex; at != null; at = predecessors.get(at)) {
            path.add(at); // Add each vertex in the path
        }

        // Reverse the path list since we traversed it from destination to start
        Collections.reverse(path);

        // Convert the list of characters (path) to a list of strings for joining with a delimiter
        List<String> pathAsString = new ArrayList<>();
        for (Character node : path) {
            pathAsString.add(String.valueOf(node)); // Convert each character to a string
        }

        // Join the list of strings with " -> " to represent the path visually
        shortestPath = String.join(" -> ", pathAsString);

        // Retrieve and store the total distance for the shortest path
        shortestPathDistance = distances.get(destinationVertex);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw edges with weights
        g.setColor(Color.BLACK);
        for (Map.Entry<Character, List<Edge>> entry : graph.entrySet()) {
            char from = entry.getKey();
            Point fromPos = nodePositions.get(from);
            for (Edge edge : entry.getValue()) {
                char to = edge.target;
                Point toPos = nodePositions.get(to);
                g.drawLine(fromPos.x, fromPos.y, toPos.x, toPos.y);
                g.drawString(String.valueOf(edge.weight), (fromPos.x + toPos.x) / 2, (fromPos.y + toPos.y) / 2);
            }
        }

        // Highlight the shortest path nodes
        g.setColor(Color.YELLOW);
        if (!shortestPath.isEmpty()) {
            for (char node : shortestPath.replaceAll(" -> ", "").toCharArray()) {
                Point position = nodePositions.get(node);
                g.fillOval(position.x - radius, position.y - radius, radius * 2, radius * 2);
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(node), position.x - 5, position.y + 5);
            }
        }

        // Draw nodes
        for (Map.Entry<Character, Point> entry : nodePositions.entrySet()) {
            char node = entry.getKey();
            Point position = entry.getValue();
            if (visited.contains(node)) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            g.fillOval(position.x - radius, position.y - radius, radius * 2, radius * 2);
            g.setColor(Color.BLACK);
            g.drawOval(position.x - radius, position.y - radius, radius * 2, radius * 2);
            g.drawString(String.valueOf(node), position.x - 5, position.y + 5);
        }

        // Display traversal order and shortest path
        g.setColor(Color.BLACK);
        g.setFont(new Font("Calibri", Font.BOLD, 30));
        g.drawString("Traversal Order: " + traversalResult, 50, 500);
        g.drawString("Shortest Path: " + shortestPath + " (Distance: " + shortestPathDistance + ")", 50, 530);
    }

    private static class Edge {
        char target;
        int weight;

        Edge(char target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    private static class Node {
        char vertex;
        int distance;
        Node previous;

        Node(char vertex, int distance, Node previous) {
            this.vertex = vertex;
            this.distance = distance;
            this.previous = previous;
        }

        public int getDistance() {
            return distance;
        }
    }
}
