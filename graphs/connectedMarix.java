import java.util.*;

class Solution {
    // Function to count connected components in an undirected graph
    public int countComponents(int V, int[][] edges) {

        // Create adjacency list from edge list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        // Array to keep track of visited nodes
        boolean[] visited = new boolean[V];

        // Variable to count the number of connected components
        int components = 0;

        // Traverse all nodes in the graph
        for (int i = 0; i < V; i++) {

            // If the node is not visited, it's a new component
            if (!visited[i]) {
                components++;

                // Start BFS from this node
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                visited[i] = true;

                // Perform BFS traversal
                while (!q.isEmpty()) {
                    int node = q.poll();

                    // Visit all unvisited neighbors
                    for (int nbr : adj.get(node)) {
                        if (!visited[nbr]) {
                            visited[nbr] = true;
                            q.offer(nbr);
                        }
                    }
                }
            }
        }

        // Return the total number of connected components
        return components;
    }
}

public class Main {
    public static void main(String[] args) {
        // Number of vertices
        int V = 5;

        // List of undirected edges
        int[][] edges = { { 0, 1 }, { 1, 2 }, { 3, 4 } };

        // Create solution object
        Solution sol = new Solution();

        // Print the number of connected components
        System.out.println("Number of Connected Components: " + sol.countComponents(V, edges));
    }
}
