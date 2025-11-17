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
        tokyo.addEdge(2, 4); // Shibuya - Akihabara
        tokyo.addEdge(4, 3); // Akihabara - Ueno
        tokyo.addEdge(3, 5); // Ueno - Asakusa

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter origin station: ");
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
}
