//Network using Prims
import java.util.*;
public class Prims{
    static class Edge {
        String u, v;
        int weight;
        Edge(String u, String v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        public String toString() {
            return u + "-" + v + "(" + weight + ")";
        }
    }
    static class Pair {
        String node;
        int weight;
        String parent;
        Pair(String node, int weight, String parent) {
            this.node = node;
            this.weight = weight;
            this.parent = parent;
        }
    }

    static class Graph {
        Map<String, List<Pair>> adj = new HashMap<>();
        Set<String> visited = new HashSet<>();

        void addEdge(String u, String v, int w) {
            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(u).add(new Pair(v, w, u));
            adj.get(v).add(new Pair(u, w, v));
        }

        void findMSTComponents() {
            int component = 1;
            for (String node : adj.keySet()) {
                if (!visited.contains(node)) {
                    System.out.println("Component " + component + ":");
                    List<Edge> mst = new ArrayList<>();
                    int cost = runPrim(node, mst);
                    for (Edge e : mst) {
                        System.out.println("MST Edge: " + e);
                    }
                    System.out.println("Total Cost: " + cost + "\n");
                    component++;
                }
            }
        }

        int runPrim(String start, List<Edge> mst) {
            PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.weight));
            pq.offer(new Pair(start, 0, null));
            int totalCost = 0;

            while (!pq.isEmpty()) {
                Pair curr = pq.poll();
                if (visited.contains(curr.node)) continue;
                visited.add(curr.node);

                if (curr.parent != null) {
                    mst.add(new Edge(curr.parent, curr.node, curr.weight));
                    totalCost += curr.weight;
                }

                for (Pair neighbor : adj.getOrDefault(curr.node, new ArrayList<>())) {
                    if (!visited.contains(neighbor.node)) {
                        pq.offer(new Pair(neighbor.node, neighbor.weight, curr.node));
                    }
                }
            }
            return totalCost;
        }
    }

    public static void main(String[] args) {
        // Test Case 1 – Fully Connected Network
        System.out.println("Test Case 1:");
        Graph graph1 = new Graph();
        graph1.addEdge("A", "B", 1);
        graph1.addEdge("A", "C", 2);
        graph1.addEdge("B", "C", 2);
        graph1.addEdge("C", "D", 3);
        graph1.findMSTComponents();

        // Test Case 2 – Disconnected Network
        System.out.println("Test Case 2:");
        Graph graph2 = new Graph();
        graph2.addEdge("P", "Q", 4);
        graph2.addEdge("Q", "R", 1);
        graph2.addEdge("S", "T", 3);
        graph2.addEdge("T", "U", 2);
        graph2.findMSTComponents();
    }
}
