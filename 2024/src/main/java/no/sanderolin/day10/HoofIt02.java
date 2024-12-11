package no.sanderolin.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class HoofIt02 {

    public static void solve() {
        int[][] map = readFromFile();
        int totalTrailScore = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == 0) {
                    // Same solution as part 1, but we can use a list instead (no longer uniques)
                    List<Coordinate> trailEnds = new ArrayList<>();
                    exploreTrail(map, x, y, trailEnds);
                    totalTrailScore += trailEnds.size(); // Number of trail ends we met with distinct paths.
                }
            }
        }
        System.out.println(totalTrailScore);
    }

    private static void exploreTrail(int[][] map, int x, int y, List<Coordinate> trailEnds) {
        int currentValue = map[y][x];
        // If trail end found, add to list.
        if (currentValue == 9) {
            trailEnds.add(new Coordinate(x, y));
            return;
        }
        // Look for valid nodes that can be visited in the cardinal directions from the currently visited node.
        if (y + 1 < map.length && map[y + 1][x] == currentValue + 1) {     // South
            exploreTrail(map, x, y + 1, trailEnds);
        }
        if (y - 1 >= 0 && map[y - 1][x] == currentValue + 1) {             // North
            exploreTrail(map, x, y - 1, trailEnds);
        }
        if (x + 1 < map[0].length && map[y][x + 1] == currentValue + 1) {  // East
            exploreTrail(map, x + 1, y, trailEnds);
        }
        if (x - 1 >= 0 && map[y][x - 1] == currentValue + 1) {             // West
            exploreTrail(map, x - 1, y, trailEnds);
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
