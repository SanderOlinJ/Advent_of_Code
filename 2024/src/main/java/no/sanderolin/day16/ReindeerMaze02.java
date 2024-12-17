package no.sanderolin.day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ReindeerMaze02 {

    private static Position startPosition;
    private static Position endPosition;
    private static char[][] map;
    private static int minimumCost = Integer.MAX_VALUE;
    private static final Set<Position> uniqueSeatTiles = new HashSet<>();

    public static void solve() {
        map = readFromFile();
        findLowestScore();
        System.out.println("Number of seats: " + uniqueSeatTiles.size());
    }

    private static void findLowestScore() {
        PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        Map<StateKey, Integer> visited = new HashMap<>();

        List<Position> initialPath = new ArrayList<>();
        initialPath.add(startPosition);
        queue.add(new State(startPosition, Direction.EAST, 0, initialPath));

        while (!queue.isEmpty()) {
            State current = queue.poll();
            // Add unique paths to the uniqueSeatTiles set.
            if (current.pos.equals(endPosition)) {
                if (current.cost < minimumCost) {
                    minimumCost = current.cost;
                    uniqueSeatTiles.clear();
                    uniqueSeatTiles.addAll(current.path);
                }
                if (current.cost == minimumCost) {
                    uniqueSeatTiles.addAll(current.path);
                }
                continue;
            }

            StateKey key = new StateKey(current.pos, current.direction);

            if (visited.getOrDefault(key, Integer.MAX_VALUE) < current.cost) {
                continue;
            }
            visited.put(key, current.cost);

            for (Direction dir : Direction.values()) {
                int turnCost = (dir == current.direction) ? 0 : 1000;
                Position nextPos = dir.move(current.pos.x, current.pos.y);

                if (isInBounds(nextPos) && map[nextPos.y][nextPos.x] != '#') {
                    List<Position> newPath =  new ArrayList<>(current.path);
                    newPath.add(nextPos);
                    queue.add(new State(nextPos, dir, current.cost + 1 + turnCost, newPath));
                }
            }
        }
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
        List<Position> path;

        State(Position pos, Direction direction, int cost, List<Position> path) {
            this.pos = pos;
            this.direction = direction;
            this.cost = cost;
            this.path = path;
        }
    }

    private record StateKey(Position pos, Direction direction) {}
}
