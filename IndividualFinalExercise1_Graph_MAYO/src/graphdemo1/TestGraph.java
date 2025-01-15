package graphdemo1;
/**
 *  <pre>
 *      This is the main method for running the graph
 *      <li>
 *          first - instantiate the graph to be able to make a graph by making a node with the method @code createNode();
 *       </li>
 *      <li>
 *          second - instantiate the Node with constructor of 1,2 and 3 specifically
 *       </li>
 *      <li>
 *         third - connect node 1 and node 2 by instantiate a edge between them
 *       </li>
 *      <li>
 *          four - check for the availability so you could connect the nodes that aren't have edges yet (2 and 3)
 *       </li>
 *  </pre>
 *
 *  <b> OUTPUT : </b>
 *  <pre>
 *      Successfully added (1,2)
 * Successfully added (1,2)
 * List of all edges that node 1has :
 * =================================================
 * ID of Edge.1
 * ID of the first node: 1
 * ID of the second node: 2
 *
 * ID of Edge.2
 * ID of the first node: 1
 * ID of the second node: 2
 *
 * [(1,2), (1,2)]
 *  </pre>
 * @author Kenneth Mayo
 * @since November 22, 2024
 * @code TestGraph.java
 */
public class TestGraph {
    public static void main(String[] args) {

        Graph graph = new Graph();
        Node node1 = new Node(1); // create a new node that contains id of 1
        Node node2 = new Node(2); // create a new node that contains id of 2
        Node node3 = new Node(3); // create a new node that contains id of 3

        graph.createNode(node1); // numberOfNodes should increment by 1
        graph.createNode(node2); // numberOfNodes should increment by 1
        graph.createNode(node3); // numberOfNodes should increment by 1

        Edge e12 = new Edge(node1, node2, 5, 1); // create an edge that connects node1 to node2 and contains weight of 5
        Edge e13 = new Edge(node1, node2, 10, 2); // create an edge that connects node1 to node3 and contains weight of 10

        if (graph.checkForAvailability()) {

            // two nodes can be connected via edge

            node1.addNeighbour(e12); // connect 1 and 2 (nodes)
            node1.addNeighbour(e13); // connect 1 and 3 (nodes)

            node1.getNeighbours();
        } else {
            System.out.println("There are less than 2 nodes. Add more to connect");
        }
    }
}
