package no.sanderolin.day05;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PrintQueue01 {

    public static void solve() {
        File file = new File("src/main/resources/day05.txt");
        try (Scanner scanner = new Scanner(file)) {
            //Map for storing all the page ordering rules related to a certain number
            HashMap<String, List<String>> rules = new HashMap<>();
            int numberOfCorrectLines = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // If the line is a rule
                if (!line.isBlank() && line.charAt(2) == '|') {
                    String[] values = line.split("\\|");
                    // Insert new list if no page ordering rules registered in the value yet.
                    rules.computeIfAbsent(values[0], k -> new ArrayList<>());
                    // Example rules: 47|53, 47|61. Now at 47, exists a list of page ordering rules {53, 61}
                    rules.get(values[0]).add(values[1]);
                } else if (!line.isBlank()) {
                    // if the line is an update
                    String[] values = line.split(",");
                    if (isValidUpdate(values, rules)) {
                        numberOfCorrectLines += Integer.parseInt(values[values.length/2]);
                    }
                }
            }
            System.out.println(numberOfCorrectLines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidUpdate(String[] line, HashMap<String, List<String>> rules) {
        // For each page in the update, check if the following pages are registered in the page ordering map
        for (int i = 0; i < line.length; i++) {
            String page = line[i];
            for (int j = i + 1; j < line.length; j++) {
                String followingPage = line[j];
                // If the following page(s) are not in the map, it shouldn't be following the current page
                if (!rules.get(page).contains(followingPage)) {
                    return false;
                }
            }
        }
        return true;
    }
}
