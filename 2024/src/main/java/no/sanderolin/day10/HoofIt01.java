package no.sanderolin.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HoofIt01 {

    public static void solve() {
        int[][] map = readFromFile();
        int totalTrailScore = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == 0) {
                    // For each trailhead, explore all paths, and count unique trail ends found.
                    Set<Coordinate> uniqueEndTrails = new HashSet<>();
                    exploreTrail(map, x, y, uniqueEndTrails);
                    totalTrailScore += uniqueEndTrails.size(); // Number of unique trail ends is waht we are after.
                }
            }
        }
        System.out.println(totalTrailScore);
    }

    private static void exploreTrail(int[][] map, int x, int y, Set<Coordinate> uniqueEndTrails) {
        int currentValue = map[y][x];
        // If trail end found, add to set (only uniques).
        if (currentValue == 9) {
            uniqueEndTrails.add(new Coordinate(x, y));
            return;
        }
        // Look for valid nodes that can be visited in the cardinal directions from the currently visited node.
        if (y + 1 < map.length && map[y + 1][x] == currentValue + 1) {     // South
            exploreTrail(map, x, y + 1, uniqueEndTrails);
        }
        if (y - 1 >= 0 && map[y - 1][x] == currentValue + 1) {             // North
            exploreTrail(map, x, y - 1, uniqueEndTrails);
        }
        if (x + 1 < map[0].length && map[y][x + 1] == currentValue + 1) {  // East
            exploreTrail(map, x + 1, y, uniqueEndTrails);
        }
        if (x - 1 >= 0 && map[y][x - 1] == currentValue + 1) {             // West
            exploreTrail(map, x - 1, y, uniqueEndTrails);
        }
    }

    private static int[][] readFromFile() {
        List<int[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day10.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.chars().map(c -> c - '0').toArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows.toArray(new int[0][0]);
    }

    private record Coordinate(int x, int y) {}
}
