package no.sanderolin.day05;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PrintQueue02 {

    // Same as part 1
    public static void solve() {
        File file = new File("src/main/resources/day05.txt");
        try (Scanner scanner = new Scanner(file)) {
            HashMap<String, List<String>> rules = new HashMap<>();
            int middleValuesFixedLines = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isBlank() && line.charAt(2) == '|') {
                    String[] values = line.split("\\|");
                    rules.computeIfAbsent(values[0], k -> new ArrayList<>());
                    rules.get(values[0]).add(values[1]);
                } else if (!line.isBlank()) {
                    String[] values = line.split(",");
                    if (!isValidUpdate(values, rules)) {
                        middleValuesFixedLines += fixIncorrect(values, rules);
                    }
                }
            }
            System.out.println(middleValuesFixedLines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Same as part 1
    private static boolean isValidUpdate(String[] line, HashMap<String, List<String>> rules) {
        for (int i = 0; i < line.length; i++) {
            String page = line[i];
            for (int j = i + 1; j < line.length; j++) {
                String followingPage = line[j];
                if (!rules.get(page).contains(followingPage)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int fixIncorrect(String[] values, HashMap<String, List<String>> rules) {
        boolean isFixed = false;
        while (!isFixed) {
            isFixed = true;
            // Swap around pages until they follow the page ordering rules
            for (int i = 0; i < values.length - 1; i++) {
                if (!rules.get(values[i]).contains(values[i+1])) {
                    String temp = values[i];
                    values[i] = values[i + 1];
                    values[i + 1] = temp;
                    isFixed = false;
                }
            }
        }
        return Integer.parseInt(values[values.length/2]);
    }
}
