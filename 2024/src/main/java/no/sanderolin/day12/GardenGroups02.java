package no.sanderolin.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class GardenGroups02 {

    public static void solve() {
        char[][] matrix = readFromFile();
        Set<Coordinate> visited = new HashSet<>();
        int totalPrice = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if (visited.contains(new Coordinate(x, y))) continue;
                Set<Coordinate> currentRegions = bfs(matrix, y, x);
                totalPrice += currentRegions.size() * findNumberOfSides(currentRegions);
                visited.addAll(currentRegions);
            }
        }
        System.out.println(totalPrice);
    }


    private static Set<Coordinate> bfs(char[][] matrix, int y, int x) {
        Set<Coordinate> currentRegion = new HashSet<>();
        char currentPlantType = matrix[y][x];
        Queue<Coordinate> queue = new ArrayDeque<>();
        queue.add(new Coordinate(x, y));

        while (!queue.isEmpty()) {
            Coordinate currentPlant = queue.poll();
            if (!currentRegion.add(currentPlant)) continue;
            x = currentPlant.x;
            y = currentPlant.y;
            if (y + 1 < matrix.length && matrix[y + 1][x] == currentPlantType) {     // South
                queue.add(new Coordinate(currentPlant.x, currentPlant.y + 1));
            } if (y - 1 >= 0 && matrix[y - 1][x] == currentPlantType) {              // North
                queue.add(new Coordinate(currentPlant.x, currentPlant.y - 1));
            } if (x + 1 < matrix.length && matrix[y][x + 1] == currentPlantType) {   // East
                queue.add(new Coordinate(currentPlant.x + 1, currentPlant.y));
            } if (x - 1 >= 0 && matrix[y][x - 1] == currentPlantType) {              // West
                queue.add(new Coordinate(currentPlant.x - 1, currentPlant.y));
            }
        }
        return currentRegion;
    }

    private static int findNumberOfSides(final Set<Coordinate> coordinates) {
        int total = 0;
        for (final Coordinate coordinate : coordinates) { // A little exhausting, but you know
            int corners = 0;
            if (!hasWestNeighbor(coordinate, coordinates)
                    && !hasNorthNeighbor(coordinate, coordinates)
                    || !hasNorthWestNeighbor(coordinate, coordinates)
                    && hasWestNeighbor(coordinate, coordinates)
                    && hasNorthNeighbor(coordinate, coordinates)) {
                corners++;
            }
            if (!hasEastNeighbor(coordinate, coordinates)
                    && !hasNorthNeighbor(coordinate, coordinates)
                    || !hasNorthEastNeighbor(coordinate, coordinates)
                    && hasEastNeighbor(coordinate, coordinates)
                    && hasNorthNeighbor(coordinate, coordinates)) {
                corners++;
            }
            if (!hasEastNeighbor(coordinate, coordinates)
                    && !hasSouthNeighbor(coordinate, coordinates)
                    || !hasSouthEastNeighbor(coordinate, coordinates)
                    && hasEastNeighbor(coordinate, coordinates)
                    && hasSouthNeighbor(coordinate, coordinates)) {
                corners++;
            }
            if (!hasWestNeighbor(coordinate, coordinates)
                    && !hasSouthNeighbor(coordinate, coordinates)
                    || !hasSouthWestNeighbor(coordinate, coordinates)
                    && hasWestNeighbor(coordinate, coordinates)
                    && hasSouthNeighbor(coordinate, coordinates)) {
                corners++;
            }
            total += corners;
        }
        return total;
    }

    private static boolean hasNorthWestNeighbor(Coordinate coordinate, Set<Coordinate> positions) {
        return positions.contains(new Coordinate(coordinate.x() - 1, coordinate.y() - 1));
    }

    private static boolean hasNorthEastNeighbor(Coordinate coordinate, Set<Coordinate> positions) {
        return positions.contains(new Coordinate(coordinate.x() + 1, coordinate.y() - 1));
    }

    private static boolean hasSouthEastNeighbor(Coordinate coordinate, Set<Coordinate> positions) {
        return positions.contains(new Coordinate(coordinate.x() + 1, coordinate.y() + 1));
    }

    private static boolean hasSouthWestNeighbor(Coordinate coordinate, Set<Coordinate> positions) {
        return positions.contains(new Coordinate(coordinate.x() - 1, coordinate.y() + 1));
    }

    private static boolean hasWestNeighbor(Coordinate coordinate, Set<Coordinate> positions) {
        return positions.contains(new Coordinate(coordinate.x() - 1, coordinate.y()));
    }

    private static boolean hasEastNeighbor(Coordinate coordinate, Set<Coordinate> positions) {
        return positions.contains(new Coordinate(coordinate.x() + 1, coordinate.y()));
    }

    private static boolean hasNorthNeighbor(Coordinate coordinate, Set<Coordinate> positions) {
        return positions.contains(new Coordinate(coordinate.x(), coordinate.y() - 1));
    }

    private static boolean hasSouthNeighbor(Coordinate coordinate, Set<Coordinate> positions) {
        return positions.contains(new Coordinate(coordinate.x(), coordinate.y() + 1));
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



