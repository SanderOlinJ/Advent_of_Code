package no.sanderolin.day01;

import java.io.File;
import java.util.*;

public class HistorianHysteria01 {

    public static void solve() {
        File file = new File("src/main/resources/day01.txt");
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int totalDistance = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String leftString = line.substring(0, line.indexOf(" "));
                String rightString = line.substring(leftString.length()).trim();
                left.add(Integer.parseInt(leftString));
                right.add(Integer.parseInt(rightString));
            }
            Collections.sort(left);
            Collections.sort(right);

            for (int i = 0; i < left.size() && i < right.size(); i++) {
                totalDistance += Math.abs(right.get(i) - left.get(i));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(totalDistance);
    }
}