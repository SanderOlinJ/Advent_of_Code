package no.sanderolin.day08;

import java.io.File;
import java.util.*;

public class ResonantCollinearity01 {

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
            int dx = otherAntenna.x - antenna.x;
            int dy = otherAntenna.y - antenna.y;
            Coordinate antiNode1 = new Coordinate(otherAntenna.x + dx, otherAntenna.y + dy);
            Coordinate antiNode2 = new Coordinate(antenna.x - dx, antenna.y - dy);
            if (isWithinBounds(antiNode1, matrixSize)) positionsOfAntiNodes.add(antiNode1);
            if (isWithinBounds(antiNode2, matrixSize)) positionsOfAntiNodes.add(antiNode2);
        }
    }

    private static boolean isWithinBounds(Coordinate coordinate, int matrixSize) {
        return coordinate.x >= 0 && coordinate.x < matrixSize && coordinate.y >= 0 && coordinate.y < matrixSize;
    }

    private record Coordinate(int x, int y) {}
}
