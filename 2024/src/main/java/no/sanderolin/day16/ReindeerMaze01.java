package no.sanderolin.day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ReindeerMaze01 {

    private static Position startPosition;
    private static Position endPosition;
    private static char[][] map;

    public static void solve() {
        map = readFromFile();
        int result = findLowestScore();
        System.out.println("Lowest Score: " + result);
    }

    private static int findLowestScore() {
        // Prioritize paths with the lowest cost
        PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        // Mark if we have visited this position before and from the same direction.
        Set<StateKey> visited = new HashSet<>();

        queue.add(new State(startPosition, Direction.EAST, 0));

        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.pos.equals(endPosition)) return current.cost;
            StateKey key = new StateKey(current.pos, current.direction);
            if (!visited.add(key)) continue;

            for (Direction dir : Direction.values()) {
                int turnCost = (dir == current.direction) ? 0 : 1000;
                Position nextPos = dir.move(current.pos.x, current.pos.y);

                if (isInBounds(nextPos) && map[nextPos.y][nextPos.x] != '#') {
                    queue.add(new State(nextPos, dir, current.cost + 1 + turnCost));
                }
            }
        }
        return -1;
    }

    private static boolean isInBounds(Position pos) {
        return pos.x >= 0 && pos.x < map[0].length && pos.y >= 0 && pos.y < map.length;
    }

    private static char[][] readFromFile() {
        List<char[]> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/day16.txt"))) {
            int row = 0;
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.toCharArray());
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == 'S') startPosition = new Position(i, row);
                    if (line.charAt(i) == 'E') endPosition = new Position(i, row);
                }
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines.toArray(new char[0][]);
    }

    private record Position(int x, int y) {}

    private enum Direction {
        NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);
        final int dx, dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        Position move(int x, int y) {
            return new Position(x + dx, y + dy);
        }
    }

    private static class State {
        Position pos;
        Direction direction;
        int cost;

        State(Position pos, Direction direction, int cost) {
            this.pos = pos;
            this.direction = direction;
            this.cost = cost;
        }
    }

    private record StateKey(Position pos, Direction direction) {}
}
