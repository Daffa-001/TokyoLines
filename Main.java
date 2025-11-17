import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] stations = {
                "Shinjuku", "Shibuya", "Meguro", "Shinagawa", "Ikebukuro", "Gotanda"
        };

        TokyoLines<String> g = new TokyoLines<>(stations.length, stations);

        // Tambah edge (tetap sama)
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(0, 4);
        g.addEdge(2, 5);

        System.out.println(g);

        System.out.print("Masukkan origin   : ");
        String origin = input.nextLine();

        System.out.print("Masukkan destination : ");
        String destination = input.nextLine();

        System.out.println();
        g.findPathBFS(origin, destination);
        g.findPathDFS(origin, destination);

        input.close();
    }
}
