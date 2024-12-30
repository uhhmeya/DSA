//The following code was Authored by Ameya Mandhare
// The following code referneces Chatgpt.com
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<String, Node> nodes;
    private List<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new ArrayList<>();
    }

    public void addNode(String id, double latitude, double longitude) {
        nodes.put(id, new Node(id, latitude, longitude));
    }

    public void addEdge(String id, String node1Id, String node2Id) {
        Node node1 = nodes.get(node1Id);
        Node node2 = nodes.get(node2Id);

        if (node1 != null && node2 != null) {
            double distance = calculateDistance(node1, node2);
            Edge edge = new Edge(id, node1, node2, distance);
            edges.add(edge);
            node1.addEdge(edge);
            node2.addEdge(edge);
        }
    }

    private double calculateDistance(Node node1, Node node2) {
        double lat1 = node1.getLatitude();
        double lon1 = node1.getLongitude();
        double lat2 = node2.getLatitude();
        double lon2 = node2.getLongitude();


        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2));
    }

    public Map<String, Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
