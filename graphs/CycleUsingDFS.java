#include<bits/stdc++.h>using namespace std;

class Solution {
public:
    // DFS function to detect cycle
    bool dfs(int node, int parent, vector<int> adj[], vector<int>& visited) {
        // Mark current node visited
        visited[node] = 1;

        // Traverse neighbors
        for (int neighbor : adj[node]) {

            // If neighbor not visited, recurse
            if (!visited[neighbor]) {
                if (dfs(neighbor, node, adj, visited)) return true;
            }

            // If neighbor visited and not parent, cycle exists
            else if (neighbor != parent) {
                return true;
            }
        }

        // No cycle found from this path
        return false;
    }

    // Function to check cycle in graph
    bool isCycle(int V, vector<int> adj[]) {
        vector<int> visited(V, 0);

        // Check all components
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(i, -1, adj, visited)) return true;
            }
        }
        return false;
    }
};

int main() {
    // Example: Graph with 5 nodes and a cycle
    int V = 5;
    vector<int> adj[V];

    // Add edges
    adj[0].push_back(1);
    adj[1].push_back(0);
    adj[1].push_back(2);
    adj[2].push_back(1);
    adj[2].push_back(3);
    adj[3].push_back(2);
    adj[3].push_back(4);
    adj[4].push_back(3);
    adj[4].push_back(1); 

    Solution sol;
    if (sol.isCycle(V, adj))
        cout << "Cycle detected\n";
    else
        cout << "No cycle found\n";

    return 0;
}
