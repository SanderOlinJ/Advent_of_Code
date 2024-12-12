package no.sanderolin.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class PlutonianPebbles02 {

    public static void solve() {
        // Rather store amount of number occurrences in hashmap, to save memory
        HashMap<String, Long> beforeRules = readFromFile();
        HashMap<String, Long> afterRules;
        int numberOfBlinks = 75;
        for (int i = 0; i < numberOfBlinks; i++) {
            afterRules = new HashMap<>();
            for (Map.Entry<String, Long> entry : beforeRules.entrySet()) {
                String key = entry.getKey();
                Long value = entry.getValue();
                if (Long.parseLong(key) == 0) {
                    afterRules.merge("1", value, Long::sum);
                } else if (key.length() % 2 == 0) {
                    String leftStone = key.substring(0, key.length() / 2);
                    String rightStone = key.substring(key.length() / 2);
                    rightStone = rightStone.replaceFirst("^0+(?!$)", "");
                    afterRules.merge(leftStone, value, Long::sum);
                    afterRules.merge(rightStone, value, Long::sum);
                } else {
                    long newStone = Long.parseLong(key) * 2024L;
                    afterRules.merge(String.valueOf(newStone), value, Long::sum);
                }
            }
            beforeRules = afterRules;
        }

        long totalAmountOfStones = 0;
        for (Long value : beforeRules.values()) {
           totalAmountOfStones += value;
        }
        System.out.println(totalAmountOfStones);
    }


    private static HashMap<String, Long> readFromFile() {
        String line = "";
        try {
            line = new BufferedReader(new FileReader("src/main/resources/day11.txt")).readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Long> map = new HashMap<>();
        String[] values = line.split(" ");
        for (String value : values) {
            map.merge(value, 1L, Long::sum);
        }
        return map;
    }
}
