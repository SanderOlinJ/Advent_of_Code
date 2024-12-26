package no.sanderolin.day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class PerfectlySphericalHousesInAVacuum02 {

    public static void solve() {
        String file = readFromFile();
        System.out.println(countUniqueHouses(file));
    }

    private static int countUniqueHouses(String file) {
        HashSet<Position> set = new HashSet<>();
        int realSantaLastX = 0, realSantaLastY = 0;
        int roboSantaLastX = 0, roboSantaLastY = 0;
        set.add(new Position(realSantaLastX, realSantaLastY));
        for (int i = 0; i < file.length(); i++) {
            boolean isRealSantaTurn = i % 2 == 0;
            if (isRealSantaTurn) {
                switch (file.charAt(i)) {
                    case '^' -> realSantaLastY -= 1;
                    case 'v' -> realSantaLastY += 1;
                    case '>' -> realSantaLastX += 1;
                    case '<' -> realSantaLastX -= 1;
                }
                set.add(new Position(realSantaLastX, realSantaLastY));
            } else {
                switch (file.charAt(i)) {
                    case '^' -> roboSantaLastY -= 1;
                    case 'v' -> roboSantaLastY += 1;
                    case '>' -> roboSantaLastX += 1;
                    case '<' -> roboSantaLastX -= 1;
                }
                set.add(new Position(roboSantaLastX, roboSantaLastY));
            }
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
