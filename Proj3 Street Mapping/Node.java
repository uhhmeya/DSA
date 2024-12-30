//The following code was Authored by Ameya Mandhare
// The following code referneces Chatgpt.com
import java.util.ArrayList;
import java.util.List;

public class Node {
    private String id;
    private double latitude;
    private double longitude;
    private List<Edge> edges;

    public Node(String id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.edges = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", edges=" + edges +
                '}';
    }
}
