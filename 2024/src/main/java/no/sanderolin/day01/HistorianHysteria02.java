package no.sanderolin.day01;

import java.io.File;
import java.util.*;

public class HistorianHysteria02 {

    public static void solve() {
        File file = new File("src/main/resources/day01.txt");
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int similarityScore = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String leftString = line.substring(0, line.indexOf(" "));
                String rightString = line.substring(leftString.length()).trim();
                left.add(Integer.parseInt(leftString));
                right.add(Integer.parseInt(rightString));
            }
            HashMap<Integer, Integer> frequency = new HashMap<>();
            for (Integer num : right) {
                frequency.put(num, frequency.getOrDefault(num, 0) + 1);
            }

            for (Integer num : left) {
                similarityScore += num * frequency.getOrDefault(num, 0);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(similarityScore);
    }
}