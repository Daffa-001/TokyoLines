import java.util.*;

public class TokyoLines<T> {
    private int size;
    private T[] data;
    private boolean[][] adjMatrix;

    @SuppressWarnings("unchecked")
    public TokyoLines(int size, T[] data) {
        this.size = size;
        this.data = data;
        adjMatrix = new boolean[size][size];
    }

    public void addEdge(int from, int to) {
        adjMatrix[from][to] = true;
        adjMatrix[to][from] = true; // undirected (Tokyo lines)
    }

    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) return i;
        }
        return -1;
    }

    // ===== DFS PATH SEARCH =====
    public List<T> dfsPath(int start, int goal) {
        boolean[] visited = new boolean[size];
        int[] parent = new int[size];
        Arrays.fill(parent, -1);

        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (!visited[curr]) {
                visited[curr] = true;

                if (curr == goal) break;

                for (int i = size - 1; i >= 0; i--) {
                    if (adjMatrix[curr][i] && !visited[i]) {
                        parent[i] = curr;
                        stack.push(i);
                    }
                }
            }
        }

        if (parent[goal] == -1 && start != goal) return null;
        return reconstructPath(start, goal, parent);
    }

    // ===== BFS PATH SEARCH =====
    public List<T> bfsPath(int start, int goal) {
        boolean[] visited = new boolean[size];
        int[] parent = new int[size];
        Arrays.fill(parent, -1);

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int curr = q.remove();

            if (curr == goal) break;

            for (int i = 0; i < size; i++) {
                if (adjMatrix[curr][i] && !visited[i]) {
                    visited[i] = true;
                    parent[i] = curr;
                    q.add(i);
                }
            }
        }

        if (parent[goal] == -1 && start != goal) return null;
        return reconstructPath(start, goal, parent);
    }

    private List<T> reconstructPath(int start, int goal, int[] parent) {
        List<T> path = new ArrayList<>();
        for (int at = goal; at != -1; at = parent[at]) {
            path.add(data[at]);
        }
        Collections.reverse(path);
        return path;
    }
}
