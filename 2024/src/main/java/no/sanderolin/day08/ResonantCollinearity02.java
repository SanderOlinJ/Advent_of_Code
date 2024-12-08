package no.sanderolin.day08;

import java.io.File;
import java.util.*;

public class ResonantCollinearity02 {

    public static void solve() {
        File file = new File("src/main/resources/day08.txt");
        try (Scanner scanner = new Scanner(file)) {
            int y = 0;
            HashMap<Character, List<Coordinate>> mapOfAntennas = new HashMap<>();
            Set<Coordinate> positionsOfAntiNodes = new HashSet<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int matrixSize = line.length();
                for (int x = 0; x < line.length(); x++) {
                    char c = line.charAt(x);
                    if (c != '.') {
                        Coordinate coordinate = new Coordinate(x, y);
                        mapOfAntennas.computeIfAbsent(c, k -> new ArrayList<>()).add(coordinate);
                        findAntiNodes(c, coordinate, mapOfAntennas, positionsOfAntiNodes, matrixSize);
                    }
                }
                y++;
            }
            System.out.println(positionsOfAntiNodes.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void findAntiNodes(char c, Coordinate antenna, HashMap<Character, List<Coordinate>> mapOfAntennas,
                                      Set<Coordinate> positionsOfAntiNodes, int matrixSize) {
        for (Coordinate otherAntenna : mapOfAntennas.get(c)) {
            if (otherAntenna.equals(antenna)) continue;
            positionsOfAntiNodes.add(antenna);
            int dx = otherAntenna.x - antenna.x;
            int dy = otherAntenna.y - antenna.y;
            int x = antenna.x;
            int y = antenna.y;
            while (isWithinBounds(x + dx, y + dy, matrixSize)) {
                Coordinate antiNode = new Coordinate(x + dx, y + dy);
                positionsOfAntiNodes.add(antiNode);
                x += dx;
                y += dy;
            }
            x = antenna.x;
            y = antenna.y;
            while (isWithinBounds(x - dx, y - dy, matrixSize)) {
                Coordinate antiNode2 = new Coordinate(x - dx, y - dy);
                positionsOfAntiNodes.add(antiNode2);
                x -= dx;
                y -= dy;
            }
        }
    }

    private static boolean isWithinBounds(int x, int y, int matrixSize) {
        return x >= 0 && x < matrixSize && y >= 0 && y < matrixSize;
    }

    private record Coordinate(int x, int y) {}
}
