package Huffman;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// Node class representing a node in the Huffman tree
class HuffmanNode {
    char character;
    int frequency;
    HuffmanNode left, right;

    // Constructor
    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
}

// Custom comparator for the priority queue to order by frequency
class FrequencyComparator implements java.util.Comparator<HuffmanNode> {
    public int compare(HuffmanNode node1, HuffmanNode node2) {
        return Integer.compare(node1.frequency, node2.frequency);
    }
}

class HuffmanCoding {
    // Main method
    public static void main(String[] args) {
        String text = "huffman coding in java";
        System.out.println("Original Text: " + text);

        // Step 1: Count frequency of each character
        Map<Character, Integer> frequencyMap = calculateFrequency(text);

        // Step 2: Build the Huffman Tree
        HuffmanNode root = buildHuffmanTree(frequencyMap);

        // Step 3: Generate and display Huffman Codes
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);
        System.out.println("\nCharacter with corresponding Huffman codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println("'" + entry.getKey() + "' : " + entry.getValue());
        }

        // Display the Huffman Tree
        System.out.println("\nHuffman Tree Structure:");
        displayTree(root, "");
    }

    // Calculate the frequency of each character in the text
    public static Map<Character, Integer> calculateFrequency(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap;
    }

    // Build the Huffman tree using the frequency map
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(new FrequencyComparator());

        // Add each character as a leaf node in the priority queue
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Combine nodes to create the Huffman tree
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode mergedNode = new HuffmanNode('-', left.frequency + right.frequency);
            mergedNode.left = left;
            mergedNode.right = right;
            priorityQueue.add(mergedNode);
        }

        // Root of the Huffman tree
        return priorityQueue.poll();
    }

    // Recursively generate the Huffman codes from the tree
    public static void generateCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }

        // Leaf node - add character and code to map
        if (root.left == null && root.right == null && root.character != '-') {
            huffmanCodes.put(root.character, code);
        }

        // Traverse left and right
        generateCodes(root.left, code + "0", huffmanCodes);
        generateCodes(root.right, code + "1", huffmanCodes);
    }

    // Display the Huffman tree structure
    public static void displayTree(HuffmanNode root, String indent) {
        if (root != null) {
            displayTree(root.right, indent + "   ");
            if (root.character == '-') {
                System.out.println(indent + root.frequency);
            } else {
                System.out.println(indent + root.character + ":" + root.frequency);
            }
            displayTree(root.left, indent + "   ");
        }
    }
}
