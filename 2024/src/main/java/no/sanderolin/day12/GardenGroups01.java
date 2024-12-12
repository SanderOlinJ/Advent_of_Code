package no.sanderolin.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class GardenGroups01 {

    public static void solve() {
        char[][] matrix = readFromFile();
        Set<Coordinate> visited = new HashSet<>();
        int totalPrice = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                // Do Breadth First Search
                totalPrice += bfs(matrix, y, x, visited);
            }
        }
        System.out.println(totalPrice);
    }

    private static int bfs(char[][] matrix, int y, int x, Set<Coordinate> visited) {
        char currentPlantType = matrix[y][x];
        Queue<Coordinate> q = new ArrayDeque<>();
        q.add(new Coordinate(x, y));

        Coordinate currentPlant;
        int totalPerimeter = 0;
        int area = 0;
        while (!q.isEmpty()) {
            currentPlant = q.poll();
            if (!visited.add(currentPlant)) continue;
            area++;
            int perimeter = 4; // Set perimeter = 4, and decrease for each neighbouring character that is the same value.
            x = currentPlant.x;
            y = currentPlant.y;
            if (y + 1 < matrix.length && matrix[y + 1][x] == currentPlantType) {
                q.add(new Coordinate(currentPlant.x, currentPlant.y + 1));
                perimeter--;
            } if (y - 1 >= 0 && matrix[y - 1][x] == currentPlantType) {
                q.add(new Coordinate(currentPlant.x, currentPlant.y - 1));
                perimeter--;
            } if (x + 1 < matrix.length && matrix[y][x + 1] == currentPlantType) {
                q.add(new Coordinate(currentPlant.x + 1, currentPlant.y));
                perimeter--;
            } if (x - 1 >= 0 && matrix[y][x - 1] == currentPlantType) {
                q.add(new Coordinate(currentPlant.x - 1, currentPlant.y));
                perimeter--;
            }
            totalPerimeter += perimeter;
        }

        return totalPerimeter * area;
    }


    private static char[][] readFromFile() {
        List<char[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day12.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.toCharArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows.toArray(new char[0][0]);
    }
    private record Coordinate(int x, int y) {}
}



