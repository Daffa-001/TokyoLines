import java.util.*;

public class Main {
    public static void main(String[] args) {

        String[] stations = {
                "Shinjuku", "Harajuku", "Shibuya",
                "Ueno", "Akihabara", "Asakusa"
        };

        TokyoLines<String> tokyo = new TokyoLines<>(6, stations);

        // Connections (simplified Tokyo network)
        tokyo.addEdge(0, 1); // Shinjuku - Harajuku
        tokyo.addEdge(1, 2); // Harajuku - Shibuya

        tokyo.addEdge(2, 3); // Shibuya - Ueno (NEW shortcut)
        tokyo.addEdge(2, 4); // Shibuya - Akihabara

        tokyo.addEdge(4, 3); // Akihabara - Ueno
        tokyo.addEdge(3, 5); // Ueno - Asakusa

        // ===== PRINT ALL LINES =====
        System.out.println("Tokyo Line Connections:");
        printLines(tokyo, stations);

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter origin station: ");
        String origin = sc.nextLine();

        System.out.print("Enter destination station: ");
        String dest = sc.nextLine();

        int start = tokyo.indexOf(origin);
        int goal = tokyo.indexOf(dest);

        // Validation
        if (start == -1) {
            System.out.println("Origin station not found.");
            return;
        }
        if (goal == -1) {
            System.out.println("Destination station not found.");
            return;
        }

        // DFS Search
        List<String> dfsPath = tokyo.dfsPath(start, goal);
        if (dfsPath == null) {
            System.out.println("DFS: Destination cannot be reached from origin.");
        } else {
            System.out.println("DFS Path: " + dfsPath);
        }

        // BFS Search
        List<String> bfsPath = tokyo.bfsPath(start, goal);
        if (bfsPath == null) {
            System.out.println("BFS: Destination cannot be reached from origin.");
        } else {
            System.out.println("BFS Path: " + bfsPath);
        }
    }

    // ===== HELPER TO PRINT ALL LINES =====
    private static void printLines(TokyoLines<String> graph, String[] stations) {
        for (int i = 0; i < stations.length; i++) {
            for (int j = i + 1; j < stations.length; j++) {
                try {
                    // Access adjacency matrix using reflection (same structure as original)
                    var field = graph.getClass().getDeclaredField("adjMatrix");
                    field.setAccessible(true);
                    boolean[][] adj = (boolean[][]) field.get(graph);

                    if (adj[i][j]) {
                        System.out.println(stations[i] + " -> " + stations[j]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
