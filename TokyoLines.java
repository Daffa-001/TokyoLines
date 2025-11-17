import java.util.*;

public class TokyoLines<T> {

    private int nVertices;
    private LinkedList<Integer>[] adjacencyList;
    private T[] vertices;

    @SuppressWarnings("unchecked")
    public TokyoLines(int nVertices, T[] vertices) {
        this.nVertices = nVertices;
        this.adjacencyList = new LinkedList[nVertices];
        for (int i = 0; i < nVertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
        this.vertices = vertices;
    }

    public void addEdge(int v, int w) {
        adjacencyList[v].add(w);
        adjacencyList[w].add(v); // graph dua arah
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nVertices; i++) {
            sb.append(i + ": " + vertices[i] + " -> " + adjacencyList[i] + "\n");
        }
        return sb.toString();
    }

    public int findIndex(T name) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /* DFS PATH */
    public void findPathDFS(T origin, T destination) {
        int start = findIndex(origin);
        int end = findIndex(destination);

        if (start == -1) {
            System.out.println("Origin station not found.");
            return;
        }
        if (end == -1) {
            System.out.println("Destination station not found.");
            return;
        }

        boolean[] visited = new boolean[nVertices];
        int[] parent = new int[nVertices];
        Arrays.fill(parent, -1);

        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (!visited[current]) {
                visited[current] = true;

                if (current == end) break;

                for (int neighbor : adjacencyList[current]) {
                    if (!visited[neighbor]) {
                        parent[neighbor] = current;
                        stack.push(neighbor);
                    }
                }
            }
        }

        if (parent[end] == -1 && start != end) {
            System.out.println("No path found (DFS).");
            return;
        }

        List<T> path = new ArrayList<>();
        int node = end;
        while (node != -1) {
            path.add(0, vertices[node]);
            node = parent[node];
        }

        System.out.println("DFS Path: " + path);
    }

    /* BFS PATH */
    public void findPathBFS(T origin, T destination) {
        int start = findIndex(origin);
        int end = findIndex(destination);

        if (start == -1) {
            System.out.println("Origin station not found.");
            return;
        }
        if (end == -1) {
            System.out.println("Destination station not found.");
            return;
        }

        boolean[] visited = new boolean[nVertices];
        int[] parent = new int[nVertices];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == end) break;

            for (int neighbor : adjacencyList[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = current;
                    queue.add(neighbor);
                }
            }
        }

        if (parent[end] == -1 && start != end) {
            System.out.println("No path found (BFS).");
            return;
        }

        List<T> path = new ArrayList<>();
        int node = end;
        while (node != -1) {
            path.add(0, vertices[node]);
            node = parent[node];
        }

        System.out.println("BFS Path: " + path);
    }
}
