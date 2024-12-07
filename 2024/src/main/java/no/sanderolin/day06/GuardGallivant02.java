package no.sanderolin.day06;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class GuardGallivant02 {
    private static char[][] map;
    private static int startX;
    private static int startY;

    public static void solve() {
        readFromFile();
        firstTraversal(); // First find out all positions the guard visits, mark them with an 'X'.
        int numberOfLoops = 0;
        for (int k = 0; k < map.length; k++) {
            for (int l = 0; l < map[k].length; l++) {
                // Only need to place obstacles where the guard would normally go, and not where she spawns.
                if (map[k][l] != 'X' || (k == startY && l == startX)) continue;
                map[k][l] = '#'; // For each position, place an '#' (obstacle).
                if (checkIfMapWithNewObstacleCausesLoop()) numberOfLoops++;
                map[k][l] = 'X'; // Then reset
            }
        }
        System.out.println(numberOfLoops);
    }

    private static boolean checkIfMapWithNewObstacleCausesLoop() {
        Guard guard = new Guard();
        int y = startY;
        int x = startX;
        // Map for tracking obstacles, and if we have met the same obstacle from the same direction (loop).
        HashMap<Coordinate, Integer> obstacleMap = new HashMap<>();
        while (true) {
            int nextY = y + guard.y;
            int nextX = x + guard.x;
            // Check if next position is out of bounds, meaning the guard got away.
            if (nextY < 0 || nextY >= map.length || nextX < 0 || nextX >= map.length) return false;
            if (map[nextY][nextX] == '#') { // Make the guard turn right if an obstacle is ahead.
                Coordinate obstacle = new Coordinate(nextY, nextX);
                // If meeting an obstacle twice with the same direction as last time, it is a loop.
                if (obstacleMap.getOrDefault(obstacle, -1) == guard.directionIndex) return true;
                obstacleMap.put(obstacle, guard.directionIndex); // Else store the last
                guard.turnRight();
            } else { // Else keep moving the direction the guard is heading.
                y = nextY;
                x = nextX;
            }
        }
    }

    // Same as for part 1
    private static void firstTraversal() {
        Guard guard = new Guard();
        int y = startY;
        int x = startX;
        while(true) {
            map[y][x] = 'X'; //Place marker on current position
            int nextY = y + guard.y;
            int nextX = x + guard.x;
            // Check if next position is out of bounds
            if (nextY < 0 || nextY >= map.length || nextX < 0 || nextX >= map.length) break;
            if (map[nextY][nextX] == '#') { // Make the guard turn right if an obstacle is ahead.
                guard.turnRight();
            } else { // Else keep moving the direction the guard is heading.
                y = nextY;
                x = nextX;
            }
        }
    }


    private static void readFromFile() {
        File file = new File("src/main/resources/day06.txt");
        boolean matrixInitialized = false;
        try (Scanner scanner = new Scanner(file)) {
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!matrixInitialized) {
                    map = new char[line.length()][line.length()];
                    matrixInitialized = true;
                }
                for (int j = 0; j < line.length(); j++) {
                    map[i][j] = line.charAt(j);
                    if (line.charAt(j) == '^') {
                        startY = i;
                        startX = j;
                    }
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Class for the guard, she can only turn right :´(
    private static class Guard {
        private int[][] directions = {
                {-1, 0},  // Up
                {0, 1},   // Right
                {1, 0},   // Down
                {0, -1}   // Left
        };
        private int directionIndex = 0;
        private int y;
        private int x;

        public Guard() {
            this.y = directions[directionIndex][0];
            this.x = directions[directionIndex][1];
        }

        private void turnRight() {
            directionIndex = (directionIndex + 1) % directions.length;
            y = directions[directionIndex][0];
            x = directions[directionIndex][1];
        }
    }

    private record Coordinate(int y, int x){}
}
