package no.sanderolin.day06;

import java.io.File;
import java.util.Scanner;

public class GuardGallivant01 {

    private static char[][] map;
    private static int currentX;
    private static int currentY;

    public static void solve() {
        readFromFile();
        traverseTheMap();
        int uniquePositions = findMarkedPositions();
        System.out.println(uniquePositions);
    }

    private static int findMarkedPositions() {
        int markedPositions = 0;
        for (char[] rows : map) {
            for (char ch : rows) {
                if (ch == 'X') {
                    markedPositions++; // Count all marked positions
                }
            }
        }
        return markedPositions;
    }

    private static void traverseTheMap() {
        Guard guard = new Guard();
        while(true) {
            map[currentY][currentX] = 'X'; //Place marker on current position
            int nextY = currentY + guard.y;
            int nextX = currentX + guard.x;
            // Check if next position is out of bounds
            if (nextY < 0 || nextY >= map.length || nextX < 0 || nextX >= map.length) break;
            if (map[nextY][nextX] == '#') { // Make the guard turn right if an obstacle is ahead.
                guard.turnRight();
            } else { // Else keep moving the direction the guard is heading.
                currentY = nextY;
                currentX = nextX;
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
                        currentY = i;
                        currentX = j;
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
}
