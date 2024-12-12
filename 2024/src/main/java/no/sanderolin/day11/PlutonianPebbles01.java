package no.sanderolin.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class PlutonianPebbles01 {

    public static void solve() {
        ArrayList<String> beforeRules = readFromFile();
        int numberOfBlinks = 25;
        ArrayList<String> afterRules;
        for (int i = 0; i < numberOfBlinks; i++) {
            afterRules = new ArrayList<>(); // Second list to avoid iterating over newly created stones
            for (String stone : beforeRules) {
                if (Long.parseLong(stone) == 0) afterRules.add("1");
                else if (stone.length() % 2 == 0) {
                    String leftStone = stone.substring(0, stone.length() / 2);
                    String rightStone = stone.substring(stone.length() / 2);
                    rightStone = rightStone.replaceFirst("^0+(?!$)", ""); // Regex magic to remove leading zeros.
                    afterRules.add(leftStone);
                    afterRules.add(rightStone);
                } else {
                    long newStone = Integer.parseInt(stone) * 2024L;
                    afterRules.add(String.valueOf(newStone));
                }
            }
            beforeRules = afterRules;
        }
        System.out.println(beforeRules.size());
    }


    private static ArrayList<String> readFromFile() {
        String line = "";
        try {
            line = new BufferedReader(new FileReader("src/main/resources/day11.txt")).readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(Arrays.asList(line.split(" ")));
    }
}
