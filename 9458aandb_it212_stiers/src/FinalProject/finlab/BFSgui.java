package finlab;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.util.List;

public class BFSgui extends JPanel {
    private final Map<Character, Point> nodePositions = new HashMap<>();
    private final Map<Character, List<Character>> graph = new HashMap<>();
    private final Set<Character> visited = new HashSet<>();
    private final Queue<Character> bfsQueue = new LinkedList<>();
    private final int radius = 30; // Node circle radius
    private JFrame frame;
    private String traversalResult = "";

    public BFSgui() {
        frame = new JFrame("BFS Animation");
        frame.add(this);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        String input = JOptionPane.showInputDialog(frame, "Enter starting vertex (a to z):");
        if (input != null && input.length() == 1) {
            char startVertex = input.charAt(0);
            setStartVertex(startVertex);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid input. USING DEFAULT VERTEX = 'a'");
        }

        // Important

        // Define the graph
        graph.put('a', Arrays.asList('b', 'c'));
        graph.put('b', Arrays.asList('a', 'd'));
        graph.put('c', Arrays.asList('a', 'e'));
        graph.put('d', Arrays.asList('b', 'e', 'f'));
        graph.put('e', Arrays.asList('c', 'd', 'g'));
        graph.put('f', Arrays.asList('d', 'z'));
        graph.put('g', Arrays.asList('e', 'z'));
        graph.put('z', Arrays.asList('f', 'g'));

        // Define node positions
        nodePositions.put('a', new Point(50, 200));
        nodePositions.put('b', new Point(150, 100));
        nodePositions.put('c', new Point(150, 300));
        nodePositions.put('d', new Point(300, 100));
        nodePositions.put('e', new Point(300, 300));
        nodePositions.put('f', new Point(450, 100));
        nodePositions.put('g', new Point(450, 300));
        nodePositions.put('z', new Point(600, 200));

        // Initialize DFS stack with the starting node if provided
        if (input != null && input.length() == 1 && graph.containsKey(input.charAt(0))) {
            char startVertex = input.charAt(0);
            bfsQueue.offer(startVertex);
        } else {
            bfsQueue.offer('a'); // Default to 'a' if no valid input
        }

        // Start BFS Animation using javax.swing.Timer
        new Timer(1000, e -> {
            if (!bfsQueue.isEmpty()) {
                char current = bfsQueue.poll();
                visited.add(current);
                traversalResult += current + " -> "; // Add current node to the result

                // Add neighbors to the queue (simulate BFS)
                for (char neighbor : graph.get(current)) {
                    if (!visited.contains(neighbor) && !bfsQueue.contains(neighbor)) {
                        bfsQueue.add(neighbor);
                    }
                }
                repaint(); // Redraw the graph with updated visited nodes
            }
        }).start();
    }

    // Method to set the starting vertex for BFS traversal
    public void setStartVertex(char startVertex) {
        if (graph.containsKey(startVertex)) {
            visited.clear(); // Clear previous visits
            bfsQueue.clear(); // Clear previous traversal
            bfsQueue.add(startVertex); // Set new start vertex
            traversalResult = ""; // Reset the traversal result
            repaint(); // Redraw the graph with updated starting node
        } else {
            JOptionPane.showMessageDialog(frame, "Valid Input");
        }
    }
    // Hanggang dito sa setStartVertex method

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw edges
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2)); // Thicker lines
        for (Map.Entry<Character, List<Character>> entry : graph.entrySet()) {
            char from = entry.getKey();
            Point fromPos = nodePositions.get(from);
            for (char to : entry.getValue()) {
                Point toPos = nodePositions.get(to);
                g2d.drawLine(fromPos.x, fromPos.y, toPos.x, toPos.y);
            }
        }

        // Draw nodes
        for (Map.Entry<Character, Point> entry : nodePositions.entrySet()) {
            char node = entry.getKey();
            Point position = entry.getValue();
            if (visited.contains(node)) {
                g.setColor(Color.GREEN); // Visited nodes are green
            } else {
                g.setColor(Color.LIGHT_GRAY); // Unvisited nodes are gray
            }
            g.fillOval(position.x - radius, position.y - radius, radius * 2, radius * 2);
            g.setColor(Color.BLACK);
            g.drawOval(position.x - radius, position.y - radius, radius * 2, radius * 2);
            g.drawString(String.valueOf(node), position.x - 5, position.y + 5); // Label the node
        }

        // Draw the DFS result at the bottom of the panel
        g.setColor(Color.BLACK);
        g.setFont(new Font("Calibri", Font.BOLD, 10));
        g.drawString("BFS Path: " + traversalResult, 50, getHeight() - 30);
    }
}

