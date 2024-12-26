package no.sanderolin.day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class PerfectlySphericalHousesInAVacuum01 {

    public static void solve() {
        String file = readFromFile();
        System.out.println(countUniqueHouses(file));
    }

    /**
     * Add unique positions to a set. Size = number of unique houses
     */
    private static int countUniqueHouses(String file) {
        HashSet<Position> set = new HashSet<>();
        int lastX = 0, lastY = 0;
        set.add(new Position(lastX, lastY));
        for (int i = 0; i < file.length(); i++) {
            switch (file.charAt(i)) {
                case '^' -> lastY -= 1;
                case 'v' -> lastY += 1;
                case '>' -> lastX += 1;
                case '<' -> lastX -= 1;
            }
            set.add(new Position(lastX, lastY));
        }
        return set.size();
    }

    private static String readFromFile() {
        String line = "";
        try {
            line = new BufferedReader(new FileReader("src/main/resources/day03.txt")).readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return line;
    }

    private record Position(int x, int y) {}
}
