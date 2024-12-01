package no.sanderolin.day01;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
            Collections.sort(right);
            int rightNumber;
            for (Integer integer : left) {
                int firstIndex = right.indexOf(integer);
                int lastIndex = right.lastIndexOf(integer);
                if (firstIndex == -1) {
                    rightNumber = 0;
                } else {
                    rightNumber = lastIndex - firstIndex + 1;
                }
                similarityScore += (integer * rightNumber);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(similarityScore);
    }
}