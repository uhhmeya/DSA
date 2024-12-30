//The following code was Authored by Ameya Mandhare
// The following code referneces Chatgpt.com

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.JFrame;

public class StreetMap {
    private Map<String, Node> nodes;
    private static List<Edge> edges;

    public StreetMap() {
        nodes = new HashMap<>();
        edges = new ArrayList<Edge>();
    }

    public void loadGraph(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.startsWith("#") || line.isEmpty()) {
                continue;
            }
            String[] parts = line.split("\\s+");
            if (parts[0].equals("i")) {
                // Node definition
                String id = parts[1];
                double latitude = Double.parseDouble(parts[2]);
                double longitude = Double.parseDouble(parts[3]);
                nodes.put(id, new Node(id, latitude, longitude));
            } else if (parts[0].equals("r")) {
                String rnum = parts[1];
                String id1 = parts[2];
                String id2 = parts[3];
                double distance = 10;

                Node node1 = nodes.get(id1);
                Node node2 = nodes.get(id2);


                if (node1 != null && node2 != null) {
                    Edge edge = new Edge(rnum, node1, node2, distance);
                    edges.add(edge);
                    node1.addEdge(edge);
                    node2.addEdge(edge);
                }
            }
        }

        scanner.close();
    }
    public void printGraph() {
        for (Node node : nodes.values()) {
            System.out.println(node);
        }
        for (Edge e : edges) {
            System.out.println(e);
        }
    }
    public void findShortestPath(String startId, String endId) {
        Node start = nodes.get(startId);
        Node end = nodes.get(endId);

        if (start == null || end == null) {
            System.out.println("Invalid start or end node.");
            return;
        }

        Map<Node, Double> distances = new HashMap<>();
        Map<Node, Node> predecessors = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        for (Node node : nodes.values()) {
            distances.put(node, Double.POSITIVE_INFINITY);
        }

        distances.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (Edge edge : current.getEdges()) {
                Node neighbor = edge.getOtherNode(current);
                double newDist = distances.get(current) + edge.getDistance();
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        if (distances.get(end) == Double.POSITIVE_INFINITY) {
            System.out.println("No path found from " + startId + " to " + endId);
        } else {
            List<Node> path = new ArrayList<>();
            Node current = end;
            while (current != null) {
                path.add(0, current);
                current = predecessors.get(current);
            }
            System.out.println("Shortest path from " + startId + " to " + endId + ":");
            for (Node node : path) {
                System.out.print(node.getId() + " ");
            }
            System.out.println("\nDistance: " + distances.get(end));
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java StreetMap <map.txt> [--show] [--directions start end]");
            return;
        }

        String filename = args[0];
        boolean show = Arrays.asList(args).contains("--show");
        boolean directions = Arrays.asList(args).contains("--directions");

        StreetMap streetMap = new StreetMap();
        MapPanel mp = new MapPanel(edges);
        JFrame frame = new JFrame("Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mp);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        try {
            streetMap.loadGraph(filename);
            mp.edges = edges;
            if (show) {
                streetMap.printGraph();
                mp.repaint();
            }

            if (directions) {
                int startIdx = Arrays.asList(args).indexOf(" ") + 1;
                if (startIdx + 1 < args.length) {
                    String start = args[startIdx];
                    String end = args[startIdx + 1];
                    streetMap.findShortestPath(start, end);
                } else {
                    System.out.println("");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(" " + filename);
        }
    }
}
