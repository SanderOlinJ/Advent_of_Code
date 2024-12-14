package no.sanderolin.day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class RestroomRedoubt02 {

    public static void solve() {
        ArrayList<String> lines = readFromFile();
        ArrayList<RobotGuard> robotGuards = convertLinesToRobotGuards(lines);
        int limitX = 101;
        int limitY = 103;
        long seconds = 1;
        outer: while (seconds < limitX * limitY) { // limitX * limitY is one "rotation"
            String[][] christmasTree = new String[limitY][limitX];
            for (RobotGuard robotGuard : robotGuards) {
                robotGuard.move(1, limitX, limitY);
                christmasTree[robotGuard.getPosition().getY()][robotGuard.getPosition().getX()] = "#";
            }
            for (RobotGuard robotGuard : robotGuards) { // Using BFS (flood fill) to find number of adjacent robot guards
                int numberOfGroupedRobotGuards = bfs(christmasTree,
                        robotGuard.getPosition().getY(), // We don't know which guards make up the Christmas tree
                        robotGuard.getPosition().getX(), // so we just search through all of them.
                        limitX,
                        limitY);
                if (numberOfGroupedRobotGuards >= 80) { // Assuming a lot of the guards will be next to each other.
                    break outer;
                }
            }
            seconds++;
        }
        System.out.println(seconds);
    }

    private static int bfs(String[][] matrix, int y, int x, int width, int height) {
        Queue<Position> queue = new ArrayDeque<>();
        Set<Position> visited = new HashSet<>();
        queue.add(new Position(x, y));
        int numberOfGroupedRobotGuards = 0;
        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if (!visited.add(current)) continue;
            x = current.getX();
            y = current.getY();
            numberOfGroupedRobotGuards++; // Also assuming a lot of guards will not share the same position.
            if (y + 1 < height && Objects.equals(matrix[y + 1][x], "#")) {   // South
                queue.add(new Position(x, y + 1));
            } if (y - 1 >= 0 && Objects.equals(matrix[y - 1][x], "#")) {     // North
                queue.add(new Position(x, y - 1));
            } if (x + 1 < width && Objects.equals(matrix[y][x + 1], "#")) {  // East
                queue.add(new Position(x + 1, y));
            } if (x - 1 >= 0 && Objects.equals(matrix[y][x - 1], "#")) {     // West
                queue.add(new Position(x - 1, y));
            }
        }
        return numberOfGroupedRobotGuards;
    }


    private static ArrayList<RobotGuard> convertLinesToRobotGuards(ArrayList<String> lines) {
        ArrayList<RobotGuard> robotGuards = new ArrayList<>();
        for (String line : lines) {
            int startX, startY, velX, velY;
            startX = Integer.parseInt(line.substring(2, line.indexOf(",")));
            startY = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.indexOf(" ")));
            velX = Integer.parseInt(line.substring(line.indexOf("v") + 2, line.lastIndexOf(",")));
            velY = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1));
            robotGuards.add(new RobotGuard(startX, startY, velX, velY));
        }
        return robotGuards;
    }

    private static ArrayList<String> readFromFile() {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day14.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static class RobotGuard {
        private final Position position;
        private final int velX;
        private final int velY;

        public RobotGuard(int startX, int startY, int velX, int velY) {
            this.position = new Position(startX, startY);
            this.velX = velX;
            this.velY = velY;
        }

        public void move(int seconds, int limitX, int limitY) {
            for (int i = 0; i < seconds; i++) {
                int newX = position.getX() + velX;
                if (newX < 0) position.setX(newX + limitX);
                else position.setX(newX % limitX);

                int newY = position.getY() + velY;
                if (newY < 0) position.setY(newY + limitY);
                else position.setY(newY % limitY);
            }
        }

        public Position getPosition() {
            return position;
        }
    }

    private static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
