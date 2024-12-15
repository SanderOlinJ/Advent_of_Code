package no.sanderolin.day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class WarehouseWoes01 {

    private static CrazyRobot crazyRobot;
    private static char[][] map;
    private static ArrayList<Character> instructions;

    public static void solve() {
        readFromFile();
        moveRobot();
        calculateSumOfBoxGPSCoordinates();
    }

    private static void calculateSumOfBoxGPSCoordinates() {
        long sumOfBoxGPSCoordinates = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'O') {
                    sumOfBoxGPSCoordinates += (100L * i) + j;
                }
            }
        }
        System.out.println(sumOfBoxGPSCoordinates);
    }

    private static void moveRobot() {
        for (Character instruction : instructions) {
            int nextX = 0, nextY = 0;
            if (instruction == '<') nextX = -1;
            else if (instruction == '>') nextX = 1;
            else if (instruction == '^') nextY = -1;
            else if (instruction == 'v') nextY = 1;
            moveThings(crazyRobot.getX(), crazyRobot.getY(), nextX, nextY);
        }
    }

    private static void moveThings(int currentX, int currentY, int nextX, int nextY) {
        if (map[currentY + nextY][currentX + nextX] == '#') return;
        if (map[currentY + nextY][currentX + nextX] == 'O') {
            moveThings(currentX + nextX, currentY + nextY, nextX, nextY);
        }
        if (map[currentY + nextY][currentX + nextX] == '.') {
            map[currentY + nextY][currentX + nextX] = map[currentY][currentX];
            map[currentY][currentX] = '.';
            if (map[currentY + nextY][currentX + nextX] == '@') {
                crazyRobot.setX(currentX + nextX);
                crazyRobot.setY(currentY + nextY);
            }
        }
    }


    private static void readFromFile() {
        instructions = new ArrayList<>();
        boolean mapComplete = false;
        boolean mapInitialized = false;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day15.txt"))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                if (!mapInitialized){
                    map = new char[line.length()][line.length()];
                    mapInitialized = true;
                }
                if (line.isBlank()) mapComplete = true;

                if (mapComplete) {
                    for (int i = 0; i < line.length(); i++) {
                        instructions.add(line.charAt(i));
                    }
                } else {
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == '@') crazyRobot = new CrazyRobot(i, row);
                        map[row][i] = line.charAt(i);
                    }
                }
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class CrazyRobot {
        private int x;
        private int y;

        public CrazyRobot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
